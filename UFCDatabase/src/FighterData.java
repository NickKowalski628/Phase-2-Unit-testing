/*
 *Nicholas Kowalski
 * CEN3024C
 * 22Feb2025
 * Class: FighterData
 * This class stores the information of ufc fighters in the UFC Fighter Application. In this class
 * each fighter has a unique ID, Name, Weight class, stance, Wins, Losses, draws, and no contests.
 */

public class FighterData {
    private int fighterID;
    private String fighterName;
    private String alias;
    private String weightClass;
    private String fightingStance;
    private double fighterHeight;
    private double fighterReach;
    private int wins;
    private int losses;
    private int draws;
    private int noContest;

    public FighterData (int fighterID, String fighterName, String alias, String weightClass,
                       String fightingStance, double fighterHeight, double fighterReach, int wins, int losses, int draws, int noContest) {
        this.fighterID = fighterID;
        this.fighterName = fighterName;
        this.alias = alias;
        this.weightClass = weightClass;
        this.fightingStance = fightingStance;
        this.fighterHeight = fighterHeight;
        this.fighterReach = fighterReach;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
        this.noContest = noContest;

    }
    //getters and setters
    public int getFighterID() {
        return fighterID;
    }

    public void setFighterID(int fighterID) {
        this.fighterID = fighterID;
    }

    public String getFighterName() {
        return fighterName;
    }

    public void setFighterName(String fighterName) {
        this.fighterName = fighterName;
    }

    /*
     * Method: getWinPercentage
     * Parameters: none
     * Return: double
     * This method calculates the fighters win percentage by adding wins, losses, and draws and then
     * divides the fighter's wins by totalFights.
     */
    public double getWinPercentage() {
        int totalFights = wins + losses + draws;
        if (totalFights == 0) {
            return 0.0;

        }
        return ((double) wins / totalFights) * 100;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getWeightClass() {
        return weightClass;
    }

    public void setWeightClass(String weightClass) {
        this.weightClass = weightClass;
    }

    public String getFightingStance() {
        return fightingStance;
    }

    public void setFightingStance(String fightingStance) {
        this.fightingStance = fightingStance;
    }

    public double getFighterHeight() {
        return fighterHeight;
    }

    public void setFighterHeight(double fighterHeight) {
        this.fighterHeight = fighterHeight;
    }

    public double getFighterReach() {
        return fighterReach;
    }

    public void setFighterReach(double fighterReach) {
        this.fighterReach = fighterReach;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getNoContest() {
        return noContest;
    }

    public void setNoContest(int noContest) {
        this.noContest = noContest;
    }

    /*
     * Method: readFromFile
     * Parameters: String line
     * Return: none
     * This method reads data from a file and populates information to the respective fields.
     */
    public void readFromFile(String line) {
        String[] fields = line.split("-");
        this.fighterID = Integer.parseInt(fields[0]);
        this.fighterName = fields[1];
        this.alias = fields[2];
        this.weightClass = fields[3];
        this.fightingStance = fields[4];
        this.fighterHeight = Double.parseDouble(fields[5]);
        this.fighterReach = Double.parseDouble(fields[6]);
        this.wins = Integer.parseInt(fields[7]);
        this.losses = Integer.parseInt(fields[8]);
        this.draws = Integer.parseInt(fields[9]);
        this.noContest = Integer.parseInt(fields[10]);
    }


    /*
     * Method: toString
     * Parameters: none
     * Return: String
     * Purpose: This method reads data from the file into a format that is presentable
     * to the user, as well as handling proper formatting when the user inputs data manually.
     * toString also handles formatting and outputting the correct message when a fighter has
     * 0 fights to avoid divison by zero errors.
     */
    @Override
    public String toString() {
        double winPercentage = getWinPercentage();
        String winPercentageStr = (winPercentage == 0.0) ? "No fights recorded" : String.format("%.2f%%", winPercentage);

        return String.format("FighterID: %04d Name: %s Alias: \"%s\" Weightclass: %s Stance: %s Height: %.0fin Reach: %.0fin Record: %dW-%dL-%dD-%dNC Win Percentage: %s",
                fighterID, fighterName, alias, weightClass, fightingStance, fighterHeight,
                fighterReach, wins, losses, draws, noContest, winPercentageStr);


    }
}
