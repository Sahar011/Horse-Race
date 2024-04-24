import java.util.concurrent.TimeUnit;
import java.lang.Math;

/**
 * Represents a race between three horses
 */
public class Race{
        private final int raceLength;
        private Horse lane1Horse;
        private Horse lane2Horse;
        private Horse lane3Horse;
        private static final double FALL_PROBABILITY = 0.02;
        private static final int WAIT_TIME_MILLIS = 150;


    public Race(int distance) {
            raceLength = distance;
            lane1Horse = null;
            lane2Horse = null;
            lane3Horse = null;
        }

        public int getRaceLength() {
            return raceLength;
        }

        public Horse getHorseInLane(int laneNumber) {
            return switch (laneNumber) {
                case 1 -> lane1Horse;
                case 2 -> lane2Horse;
                case 3 -> lane3Horse;
                default -> null;
            };
        }


    /**
     * Adds a horse to the race in a given lane
     */
    public void addHorse(Horse theHorse, int laneNumber)
    {
        if (laneNumber >= 1 && laneNumber <= 3)
        {
            switch (laneNumber) {
                case 1:
                    lane1Horse = theHorse;
                    break;
                case 2:
                    lane2Horse = theHorse;
                    break;
                case 3:
                    lane3Horse = theHorse;
                    break;
            }
        }
        else
        {
            System.out.println("Cannot add horse to lane " + laneNumber + " because there is no such lane");
        }
    }
    /**
     * Starts the race and simulates the horses moving forward
     */
    public void startRace()
    {
        // Declare a local variable to tell us when the race is finished
        boolean finished = false;

        // Reset all the lanes (all horses not fallen and back to 0).
        lane1Horse.goBackToStart();
        lane2Horse.goBackToStart();
        lane3Horse.goBackToStart();

        while (!finished)
        {
            // Move each horse
            moveHorse(lane1Horse);
            moveHorse(lane2Horse);
            moveHorse(lane3Horse);

            // Print the race positions
            printRace();

            // Check if any of the three horses has won the race
            if (raceWonBy(lane1Horse) || raceWonBy(lane2Horse) || raceWonBy(lane3Horse))
            {
                finished = true;
                // Determine the winner
                Horse winner = lane1Horse;
                if (raceWonBy(lane2Horse)) winner = lane2Horse;
                if (raceWonBy(lane3Horse)) winner = lane3Horse;
                // Print the winner's name
                System.out.println("The winner is: " + winner.getName());
            }

            // Wait for 100 milliseconds
            try {
                TimeUnit.MILLISECONDS.sleep(WAIT_TIME_MILLIS);
            } catch (Exception e) {}
            //raceTrackPanel.repaint();
        }
    }

    /**
     * Moves a horse forward in the race
     */
    private void moveHorse(Horse theHorse)
    {
        if (!theHorse.hasFallen())
        {
            // The probability that the horse will move forward depends on the confidence
            if (Math.random() < theHorse.getConfidence())
            {
                theHorse.moveForward();
            }

            // The probability that the horse will fall is very small (max is 0.1)
            // but will also depend exponentially on confidence
            if (Math.random() < (FALL_PROBABILITY*theHorse.getConfidence()*theHorse.getConfidence()))
            {
                theHorse.fall();
            }
        }
    }

    /**
     * Determines if a horse has won the race
     */
    private boolean raceWonBy(Horse theHorse)
    {

        return theHorse.getDistanceTravelled() == raceLength;
    }

    /**
     * Prints the race on the terminal
     */
    private void printRace()
    {
        System.out.print('\u000C');  // Clear the terminal window

        multiplePrint('=', raceLength + 3); // Top edge of track
        System.out.println();

        printLane(lane1Horse);
        System.out.println();

        printLane(lane2Horse);
        System.out.println();

        printLane(lane3Horse);
        System.out.println();

        multiplePrint('=', raceLength + 3); // Bottom edge of track
        System.out.println();

        // Print horse names and confidence ratings
        System.out.println("Horse 1: " + lane1Horse.getName() + " (Confidence: " + lane1Horse.getConfidence() + ")");
        System.out.println("Horse 2: " + lane2Horse.getName() + " (Confidence: " + lane2Horse.getConfidence() + ")");
        System.out.println("Horse 3: " + lane3Horse.getName() + " (Confidence: " + lane3Horse.getConfidence() + ")");
    }

    /**
     * Prints a horse's lane during the race
     */
    private void printLane(Horse theHorse)
    {
        // Calculate how many spaces are needed before and after the horse
        int spacesBefore = theHorse.getDistanceTravelled();
        int spacesAfter = raceLength - theHorse.getDistanceTravelled();

        // Print a | for the beginning of the lane
        System.out.print('|');

        // Print the spaces before the horse
        multiplePrint(' ', spacesBefore);

        // If the horse has fallen then print dead, else print the horse's symbol
        if(theHorse.hasFallen())
        {
            System.out.print('\u2322');
        }
        else
        {
            System.out.print(theHorse.getSymbol());
        }

        // Print the spaces after the horse
        multiplePrint(' ', spacesAfter);

        // Print the | for the end of the track
        System.out.print('|');
    }

    /**
     * Prints a character a given number of times.
     */
    private void multiplePrint(char aChar, int times)
    {
        int i = 0;
        while (i < times)
        {
            System.out.print(aChar);
            i = i + 1;
        }
    }


    public static void main(String[] args)
    {
        // Create three horses for the race
        Horse horse1 = new Horse('♘', "Ava", 0.8);
        Horse horse2 = new Horse('♞', "bo", 0.7);
        Horse horse3 = new Horse('♔', "mo", 0.9);

        // Create a race with a distance of 20
        Race race = new Race(20);

        // Add horses to the race in different lanes
        race.addHorse(horse1, 1);
        race.addHorse(horse2, 2);
        race.addHorse(horse3, 3);

        // Start the race
        race.startRace();
    }
}