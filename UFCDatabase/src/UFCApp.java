import java.io.*;
import java.util.*;

/*
 * Nicholas Kowalski
 * CEN3024C
 * 22Feb2025
 * Class: UFCApp
 * This class handles the main method, menu method, and other methods that the user interacts with via the CLI menu.
 * This program allows users to load and store Fighter data, display all fighters, adding, removing, and updating
 * fighters. As well as custom sorting by win percentage.
 */

public class UFCApp {
    ArrayList<FighterData> fighters = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    /*
     * Method: loadFighterData
     * Parameters: none
     * Return: boolean
     * Purpose: This method allows users to locate a file to import fighter information to store within the ArrayList
     * This method returns true if a valid file and file location are found and will return false if invalid file and file
     * location are not found
     */
    public boolean loadFighterData() {
        System.out.print("Enter UFC Fighter file location: ");
        String filepath = scanner.nextLine();

        File file = new File(filepath);
        if (!file.exists() || !file.isFile()) {
            System.out.println("File unable to be located, or invalid file format. Please try again.");
            return false;
        }

        if (!filepath.endsWith(".txt") && !filepath.endsWith(".csv")) {
            System.out.println("Invalid file format, please try again.");
            return false;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                FighterData fighter = new FighterData(0, "", "", "",
                        "", 0, 0, 0, 0, 0, 0);
                fighter.readFromFile(line);
                fighters.add(fighter);
            }
            System.out.println("Fighter(s) loaded successfully.");
            return true;
        } catch (IOException e) {
            System.out.println("Error while reading file.");
            return false;
        }
    }



    /*
     * Method: addFighter
     * Parameters:
     * Return: FighterData
     * This method allows the user to manually input a fighter's information, while providing input validation where
     * it is needed.
     */
    public FighterData addFighter() {
        System.out.print("Enter fighter ID (4 Digits): ");
        int fighterID = 0;
        boolean validFighterID = false;
        while (!validFighterID) {
            String idInput = scanner.nextLine();

            //Verifies ID entered is 4 digits and numerical.
            if (idInput.length() == 4 && idInput.chars().allMatch(Character::isDigit)) {
                fighterID = Integer.parseInt(idInput);

                //Duplicate ID validation
                boolean duplicateFighter = false;
                for (FighterData fighter : fighters) {
                    if (fighter.getFighterID() == fighterID) {
                        duplicateFighter = true;
                        break;
                    }
                }
                if (duplicateFighter) {
                    System.out.println("This ID already exists, please try again. ");
                } else {
                    validFighterID = true;
                }
            } else {
                System.out.println("Invalid fighter ID. Please try again.");
            }

        }

        System.out.print("Enter Fighter name: ");
        String fighterName = scanner.nextLine();
        while (fighterName.isEmpty()) {
            System.out.println("Fighter name cannot be blank. Please try again.");
            fighterName = scanner.nextLine();
        }

        System.out.print("Enter fighter alias: ");
        String fighterAlias = scanner.nextLine();
        while (fighterAlias.isEmpty()) {
            System.out.println("Fighter alias cannot be blank. Please try again.");
            fighterAlias = scanner.nextLine();
        }

        System.out.print("Enter weight class: ");
        String weightClass = scanner.nextLine();
        while (weightClass.isEmpty()) {
            System.out.println("Weight class cannot be blank. Please try again.");
            weightClass = scanner.nextLine();
        }

        System.out.print("Enter fighting stance: ");
        String fightingStance = scanner.nextLine();
        while (fightingStance.isEmpty()) {
            System.out.println("Fighting stance cannot be blank. Please try again.");
            fightingStance = scanner.nextLine();
        }

        double fighterHeight = 0;
        boolean validHeight = false;
        while (!validHeight) {
            System.out.print("Enter fighter height (in inches): ");
            try {
                fighterHeight = scanner.nextDouble();
                scanner.nextLine();
                if (fighterHeight > 0) {
                    validHeight = true;
                } else {
                    System.out.println("Invalid entry, please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry, please enter a number");
                scanner.nextLine();
            }
        }

        double fighterReach = 0;
        boolean validReach = false;
        while (!validReach) {
            System.out.print("Enter fighter reach (in inches): ");
            try {
                fighterReach = scanner.nextDouble();
                scanner.nextLine();
                if (fighterReach > 0) {
                    validReach = true;
                } else {
                    System.out.println("Invalid entry, please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry, please enter a number");
                scanner.nextLine();
            }
        }
        int wins = 0;
        boolean validWins = false;
        while (!validWins) {
            System.out.print("Enter fighter wins: ");
            try {
                wins = scanner.nextInt();
                scanner.nextLine();
                if (wins >= 0) {
                    validWins = true;
                } else {
                    System.out.print("Invalid entry, please try again.");

                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry, please enter a number");
                scanner.nextLine();
            }
        }
        int losses = 0;
        boolean validLosses = false;
        while (!validLosses) {
            System.out.print("Enter fighter losses: ");
            try {
                losses = scanner.nextInt();
                scanner.nextLine();
                if (losses >= 0) {
                    validLosses = true;
                } else {
                    System.out.println("Invalid entry, please try again.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid entry, please enter a number");
            }
        }
        int draws = 0;
        boolean validDraws = false;
        while (!validDraws) {
            System.out.print("Enter total draws: ");
            try {
                draws = scanner.nextInt();
                scanner.nextLine();

                if (draws >= 0) {
                    validDraws = true;
                } else {
                    System.out.println("Invalid entry, please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry, please enter a number");
            }
        }

        int noContests = 0;
        boolean validNoContests = false;
        while (!validNoContests) {
            System.out.print("Enter total no contests: ");
            try {
                noContests = scanner.nextInt();
                scanner.nextLine();
                if (noContests >= 0) {
                    validNoContests = true;
                } else {
                    System.out.println("Invalid entry, please try again.");

                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry, please enter a number");
            }
        }

        FighterData newFighter = new FighterData(fighterID, fighterName, fighterAlias, weightClass, fightingStance,
                fighterHeight, fighterReach, wins, losses, draws, noContests);
        fighters.add(newFighter);
        System.out.println("Fighter added successfully.");
        return newFighter;
    }

    /*
     * Method: displayFighters
     * Parmeters: none
     * Return: fighters
     * This method displays the current list of fighters stored within the ArrayList while the user is
     * running it. Handles empty list error
     */
    public ArrayList<FighterData> displayFighters() {
        if (fighters.isEmpty()) {
            System.out.println("No fighters currently stored.");
            return fighters;
        }
        System.out.println("\nCurrent Fighters");
        for (FighterData fighter : fighters) {
            System.out.println(fighter);
        }
        return fighters;
    }

    /*
     * Method: updateFighter
     * Parameters: none
     * Returns: boolean
     * This method allows the user to update a fighter's wins, losses, draws, or no contest fight results
     *
     */
    public boolean updateFighter() {
        System.out.print("Enter 4-digit ID of fighter to be updated: ");
        int fighterID = Integer.parseInt(scanner.nextLine());

        for (FighterData fighter : fighters)
            if (fighter.getFighterID() == fighterID) {
                //input validation for wins
                int wins = 0;
                boolean validWins = false;
                while (!validWins) {
                    System.out.print("Enter updated wins: ");
                    try {
                        wins = scanner.nextInt();
                        scanner.nextLine();
                        if (wins >= 0) {
                            validWins = true;
                        } else {
                            System.out.println("Invalid entry, please try again.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid entry, please enter a number");
                        scanner.nextLine();
                    }

                }

                //input validation for losses
                int losses = 0;
                boolean validLosses = false;
                while (!validLosses) {
                    System.out.print("Enter updated losses: ");
                    try {
                        losses = scanner.nextInt();
                        scanner.nextLine();
                        if (losses >= 0) {
                            validLosses = true;
                        } else {
                            System.out.println("Invalid entry, please try again.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid entry, please enter a number.");
                        scanner.nextLine(); // Clear invalid input
                    }
                }

                //input validation for draws
                int draws = 0;
                boolean validDraws = false;
                while (!validDraws) {
                    System.out.print("Enter updated draws: ");
                    try {
                        draws = scanner.nextInt();
                        scanner.nextLine();
                        if (draws >= 0) {
                            validDraws = true;
                        } else {
                            System.out.println("Invalid entry, please try again.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid entry, please enter a number.");
                        scanner.nextLine();
                    }
                }

                //input validation for no contests
                int noContests = 0;
                boolean validNoContests = false;
                while (!validNoContests) {
                    System.out.print("Enter updated no contests: ");
                    try {
                        noContests = scanner.nextInt();
                        scanner.nextLine();
                        if (noContests >= 0) {
                            validNoContests = true;
                        } else {
                            System.out.println("Invalid entry, please try again.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid entry, please enter a number.");
                        scanner.nextLine();
                    }
                }


                fighter.setWins(wins);
                fighter.setLosses(losses);
                fighter.setDraws(draws);
                fighter.setNoContest(noContests);
                System.out.println("Fighter updated successfully.");
                displayFighters();
                return true;
            }
        System.out.println("FighterID not found, please import fighter data or add manually.");
        return false;
    }
    /*
     * Method: removeFighter
     * Parameters none
     * Return: boolean
     * Purpose: Allows the user to remove a fighter from the ArrayList by entering their fighterID. This method also
     * ensures that the fighter ID can be located before attempting to remove it.
     *
     */
        public boolean removeFighter() {
            while(true){
                System.out.print("Enter fighter ID to remove: ");

                try{
                    int fighterID = Integer.parseInt(scanner.nextLine());
                    FighterData deleteFighter = null;
                    for (FighterData fighter : fighters) {
                        if (fighter.getFighterID() == fighterID) {
                            deleteFighter = fighter;
                            break;
                        }
                    }
                    if (deleteFighter != null) {
                        fighters.remove(deleteFighter);
                        System.out.println("Fighter removed successfully.");
                        displayFighters();
                        return true;
                    } else {
                        System.out.println("FighterID not found, please import fighter data or add manually.");
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid entry, please try again.");
                }



            }
            return false;
        }


    /*
     * Method: sortFighters
     * Parameters: None
     * Return: fighters
     * This method implements the custom feature within this database, it calculates and sorts the fights win %
     * from highest to lowest utilizing comparator and collections.
     */
    public ArrayList<FighterData> sortFighters() {
        Collections.sort(fighters, new Comparator<FighterData>() {
            @Override
            public int compare(FighterData fighter1, FighterData fighter2) {
                double winPercentage1 = fighter1.getWinPercentage();
                double winPercentage2 = fighter2.getWinPercentage();
                return Double.compare(winPercentage2, winPercentage1);
            }});
        displayFighters();
        return fighters;
    }

        /*
         * Method: mainMenu
         * Parameters: none
         * Return: none
         * Purpose: This method holds the structure for the main menu as well as the method calls to execute
         * when a user enters in a choice.
         */
        public boolean mainMenu () {
            while (true) {
                try {
                    System.out.println("\nUFC Fighter Database");
                    System.out.println("1. Add Fighter(s) From File");
                    System.out.println("2. Add Fighter Manually");
                    System.out.println("3. Display Current Fighters");
                    System.out.println("4. Update Fighter");
                    System.out.println("5. Remove Fighter");
                    System.out.println("6. Sort Fighters by Win Percentage");
                    System.out.println("0. Exit");
                    System.out.print("Enter your choice: ");

                    String input = scanner.nextLine();
                    int choice = Integer.parseInt(input);

                    switch (choice) {
                        case 1:
                            loadFighterData();
                            break;
                        case 2:
                            addFighter();
                            break;
                        case 3:
                            displayFighters();
                            break;
                        case 4:
                            updateFighter();
                            break;
                        case 5:
                            removeFighter();
                            break;
                        case 6:
                            sortFighters();
                            break;
                        case 0:
                            System.out.println("Thank you for using UFC Database\n Goodbye!");
                            return false;
                        default:
                            System.out.println("Error, please enter a number between 0-6");

                    }

                } catch (NumberFormatException e) {
                    System.out.println("Error, please enter a number.");
                    scanner.nextLine();
                }
            }
        }

        /*
         * Method: main
         * Parameters: none
         * Return: none
         * Purpose: This method holds the main that allows the menu to execute, allowing for user interfacing.
         */
        public static void main (String[]args){
            UFCApp app = new UFCApp();
            app.mainMenu();
        }
    }






