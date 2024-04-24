/**
 * Represents a horse in a race
 * Each horse has a symbol, a name, and a confidence rating
 */
public class Horse {
    private String horseName;
    private char horseSymbol;
    private int distanceTravelled;
    private boolean fallen;
    private double horseConfidence;

    /**
     * Constructor for objects of class Horse
     */
    public Horse(char horseSymbol, String horseName, double horseConfidence) {
        this.horseSymbol = horseSymbol;
        this.horseName = horseName;
        this.horseConfidence = horseConfidence;
        this.distanceTravelled = 0;
        this.fallen = false;
    }

    /**
     * Simulates the horse falling during the race
     */
    public void fall() {
        this.fallen = true;
    }

    /**
     * Gets the confidence rating of the horse
     */
    public double getConfidence() {
        return horseConfidence;
    }

    /**
     * Gets the distance travelled by the horse
     */
    public int getDistanceTravelled() {
        return distanceTravelled;
    }

    /**
     * Gets the name of the horse
     */
    public String getName() {
        return horseName;
    }

    /**
     * Gets the symbol representing the horse
     */
    public char getSymbol() {
        return horseSymbol;
    }

    /**
     * Moves the horse forward in the race
     */
    public void moveForward() {
        if (!fallen) {
            this.distanceTravelled++;
        }
    }

    /**
     * Resets the horse's position and fallen status
     */
    public void goBackToStart() {
        this.distanceTravelled = 0;
        this.fallen = false;
    }

    /**
     * Checks if the horse has fallen during the race
     */
    public boolean hasFallen() {
        return fallen;
    }

    /**
     * Sets the confidence rating of the horse
     */
    public void setConfidence(double newConfidence) {
        this.horseConfidence = newConfidence;
    }

    /**
     * Sets the symbol representing the horse
     */
    public void setSymbol(char newSymbol) {
        this.horseSymbol = newSymbol;
    }

}
