import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Nicholas Kowalski
 * 02Mar2025
 * Class: UFCAppTest
 * This class tests the methods found within UFCApp to ensure that they properly handle exceptions, allow for smooth
 * application operation, and that data is handled and input properly.
 */

class UFCAppTest {
    private UFCApp app;

    @org.junit.jupiter.api.BeforeEach
    void setUp(){
        app = new UFCApp();
        System.setIn(System.in);
    }

    /*
     * Method: loadFighterData_test
     * Parameters: none
     * Return: none
     * Purpose: this test siumlates loading fighter data from a text file, this method creates a fake text file
     * to simulate the user loading it manually.
     */
    @org.junit.jupiter.api.Test
    @DisplayName("Load Fighters from file")
    void loadFighterData_test() throws FileNotFoundException {
        String fileContent = "1234-Jon Jones-Bones-Heavyweight-Orthodox-76-84-28-1-0-1";
        File tempFile = new File("test_fighters.txt");
        try (PrintWriter writer = new PrintWriter(tempFile)) {
            writer.write(fileContent);
        }

        String input = "test_fighters.txt";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        app.scanner = new Scanner(System.in);

        boolean loaded = app.loadFighterData();
        assertTrue(loaded, "Fighter loaded successfully.");
        assertEquals(1, app.fighters.size(), "1 fighter loaded.");
        assertEquals(1234, app.fighters.get(0).getFighterID(), "Fighter ID should be 1234.");
        assertEquals("Jon Jones", app.fighters.get(0).getFighterName(), "Jon Jones");
        assertEquals("Bones", app.fighters.get(0).getAlias(), "Bones");
        assertEquals("Heavyweight", app.fighters.get(0).getWeightClass(), "Heavyweight");
        assertEquals("Orthodox", app.fighters.get(0).getFightingStance(), "Orthodox");
        assertEquals(76.0, app.fighters.get(0).getFighterHeight(),"Fighter is 76 inches tall");
        assertEquals(84.0, app.fighters.get(0).getFighterReach(), "Fighter's reach is 84 inches");
        assertEquals(28, app.fighters.get(0).getWins(), "Fighter should have 28 wins");
        assertEquals(1, app.fighters.get(0).getLosses(), "Fighter should have 1 losses");
        assertEquals(0, app.fighters.get(0).getDraws(), "Fighter should have 0 draws");
        assertEquals(1, app.fighters.get(0).getNoContest(), "Fighter should have 1 no contest");

        tempFile.delete();
    }

    /*
     * Method: addFighter_test
     * Parameters: none
     * Return: none
     * purpose: This method simulates mannual entry of a fighter. This method also verifies that each simulated
     * field entered matches.
     */
    @org.junit.jupiter.api.Test
    @DisplayName("Test adding a fighter manually")
    void addFighter_test() {
        String input = "1234\nJon Jones\nBones\nHeavyweight\nOrthodox\n76\n84\n28\n1\n0\n1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        app.scanner = new Scanner(System.in);

        app.addFighter();
        //tests individual fields
        ArrayList<FighterData> fighters = app.displayFighters();
        assertEquals(1, fighters.size(), "One fighter should be added to the list");
        assertEquals(1234, fighters.get(0).getFighterID(), "Fighter ID should be a 4-digit number");
        assertEquals("Jon Jones", fighters.get(0).getFighterName(), "Jon Jones should be added to the list");
        assertEquals("Bones", fighters.get(0).getAlias(), "Bones");
        assertEquals("Heavyweight", fighters.get(0).getWeightClass(), "Heavyweight");
        assertEquals("Orthodox", fighters.get(0).getFightingStance(), "Orthodox");
        assertEquals(76.0, fighters.get(0).getFighterHeight(),"Fighter is 76 inches tall");
        assertEquals(84.0, fighters.get(0).getFighterReach(), "Fighter's reach is 84 inches");
        assertEquals(28, fighters.get(0).getWins(), "Fighter should have 28 wins");
        assertEquals(1, fighters.get(0).getLosses(), "Fighter should have 1 losses");
        assertEquals(0, fighters.get(0).getDraws(), "Fighter should have 0 draws");
        assertEquals(1, fighters.get(0).getNoContest(), "Fighter should have 1 no contest");
    }

    /*
     * Method: displayFighters_test
     * Parameters: none
     * Return: none
     * purpose: This method tests if the application can display all fighters currently held
     * within the list. Simulated by manually entering information of a fighter
     */
    @org.junit.jupiter.api.Test
    void displayFighters_test() {
        app.fighters.add(new FighterData(1234, "Jon Jones", "Bones","Heavyweight", "Orthodox",
                76,84,28,1,0,1));
        ArrayList<FighterData> fighters = app.displayFighters();
        assertEquals(1, fighters.size(), "One Fighter should be displayed");
        assertEquals("Jon Jones", fighters.get(0).getFighterName(), "Jon Jones should be displayed");
    }

    /*
     * Method: updateFighter_test
     * Parameters: none
     * Return: none
     * purpose: This method manually adds a fighter and tests that a user can successfully update that fighter
     * object. Specifically wins, losses, draws, and no contests
     */
    @org.junit.jupiter.api.Test
    void updateFighter_test() {
        app.fighters.add(new FighterData(1234, "Jon Jones", "Bones", "Heavyweight"
        ,"Orthodox",76,84,28,1,0,1));

        //simulated user input
        String validInput = "1234\n29\n1\n0\n1\n";
        System.setIn(new ByteArrayInputStream(validInput.getBytes()));
        app.scanner = new Scanner(System.in);

        boolean updated = app.updateFighter();
        assertTrue(updated, "Fighter updated successfully.");
        FighterData fighter = app.fighters.get(0);
        assertEquals(29, fighter.getWins(), "Fighter should have 29 wins");
        assertEquals(1, fighter.getLosses(), "Fighter should still have 1 loss");

    }


    /*
     * Method: removeFighter_test
     * Parameters: none
     * Return: none
     * purpose: This method tests if the application can successfully remove a fighter from the list using a manual
     * entry
     */
    @org.junit.jupiter.api.Test
    void removeFighter_test() {
        app.fighters.add(new FighterData(1234, "Jon Jones", "Bones", "Heavyweight"
                ,"Orthodox",76,84,28,1,0,1));

        String input = "1234\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        app.scanner = new Scanner(System.in);

        boolean removed = app.removeFighter();
        assertTrue(removed, "Fighter removed successfully.");
        assertEquals (0, app.fighters.size(), "The list should be empty");
    }

    /*
     * Method: sortFighters_test
     * Parameters: none
     * Return: none
     * purpose: This method tests the custom sort feature, ensuring that the fighter with the highest percentage is displayed
     * first and that a fighter with no fights is calculated to avoid division by 0
     */
    @org.junit.jupiter.api.Test
    void sortFighters_test() {
        app.fighters.add(new FighterData(1234, "Fighter A", "Bones", "Heavyweight"
                ,"Orthodox",76,84,28,1,0,1));
        app.fighters.add(new FighterData(1236, "Fighter B", "The Great", "Featherweight",
                "Orthodox", 66,71.5,26,4,0,0));
        app.fighters.add(new FighterData(2006,"Fighter I", "The Betrayer","Heavyweight"
        ,"Orthodox", 109, 162, 0,0,0,0));

        app.sortFighters();
        assertEquals ("Fighter A", app.fighters.get(0).getFighterName(), "Highest win % should be first");
        assertEquals ("Fighter B", app.fighters.get(1).getFighterName(), "Lowest win % should be last");
        assertEquals ("Fighter I", app.fighters.get(2).getFighterName(), "Fighter with 0 total fights should " +
                "display no fights recorded");

    }

}