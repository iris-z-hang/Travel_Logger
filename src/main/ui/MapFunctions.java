package ui;

import model.Location;
import model.Map;
import model.VisitedUnvisitedLists;

import java.util.ArrayList;
import java.util.Scanner;

// ui class that contains most of the user interaction

public class MapFunctions extends ListFunctions {
    private static final String ADD_LOCATION_TO_VISITED = "ADD V";
    private static final String ADD_LOCATION_TO_UNVISITED = "ADD U";
    private static final String REMOVE_LOCATION_FROM_UNVISITED = "REMOVE U";
    private static final String REMOVE_LOCATION_FROM_VISITED = "REMOVE V";
    private static final String CHECK_VISITED = "CHECK V";
    private static final String CHECK_UNVISITED = "CHECK U";
    private static final String FIND_DISTANCE = "DISTANCE";
    private static final String QUIT = "QUIT";
    private static final String BACK = "BACK";
    private static final String NEW_LOCATIONS = "NEW";
    private static final String EXISTING_LOCATIONS_UNVISITED = "EXISTING U";
    private static final String EXISTING_LOCATIONS_VISITED = "EXISTING V";
    private static final String EXISTING_UNVISITED_VISITED = "EXISTING UV";

    private static final String UNVISITED = "UNVISITED";
    private static final String VISITED = "VISITED";
    private static final String MOVE_TO_UNVISITED = "MOVE TO U";
    private static final String MOVE_TO_VISITED = "MOVE TO V";
    private static final String INFO = "INFO";

    protected static Map travelMap;
    protected String cityName;
    static Scanner userInput;

    public static ArrayList<Location> unvisited;
    public static ArrayList<Location> visited;


    // EFFECTS: constructor for MapFunctions class, sets up userInput, travelMap, and sets tripFinished to false
    //          initializes two arraylists for abstraction
    public MapFunctions() {
        userInput = new Scanner(System.in);
        travelMap = new VisitedUnvisitedLists();
        travelMap.setTripFinished(false);

        unvisited = new ArrayList<>();
        visited = new ArrayList<>();
    }

    // EFFECTS: accepts user input while TripFinished is false
    public void handleUserInput() {
        printInstructions();
        String str;

        while (!travelMap.getTripFinished()) {
            str = getUserInputString();
            parseInput(str);
        }
    }

    // EFFECTS: the introductory lines of the program
    void printIntro() {
        System.out.println("Hello, welcome to Travel Logger.");
        System.out.println("Please input the name of the city you are visiting.");
        this.cityName = userInput.nextLine();
        System.out.println("Creating new map for " + cityName + "\n");

    }

    // EFFECTS: prints the initial menu of commands
    public static void printInstructions() {
        System.out.println("What action would you like to perform?\n");

        System.out.println("Enter " + UNVISITED + " to view/edit the list of unvisited locations");
        System.out.println("Enter " + VISITED + " to view/edit the list of visited locations");

        System.out.println("Enter " + FIND_DISTANCE + " to find the distance between two locations.");
        System.out.println("Enter " + QUIT + " to exit this application.");

    }

    // MODIFIES: this
    // EFFECTS: takes user input and performs appropriate actions which are: enter unvisited menu, enter visited menu,
    //          enter distance menu, return to original screen, and quit the program
    protected static void parseInput(String str) {
        if (str.length() > 0) {
            switch (str) {
                case UNVISITED:
                    parseInputEditUnvisited(str);
                    break;
                case VISITED:
                    parseInputEditVisited(str);
                    break;
                case FIND_DISTANCE:
                    parseInputFindDistance();
                    break;
                case BACK:
                    printInstructions();
                    break;
                case QUIT:
                    quit();
                    break;
                default:
                    System.out.println("Invalid command.");
                    break;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: quits the program
    private static void quit() {
        System.out.println("Goodbye.");
        travelMap.setTripFinished(true);
        userInput.close();
    }

    // EFFECTS: prints instructions for editing unvisited list and calls for user input and user input results
    private static void parseInputEditUnvisited(String str) {
        System.out.println("Enter " + ADD_LOCATION_TO_UNVISITED + " to add a new location to unvisited places.");
        System.out.println("Enter " + REMOVE_LOCATION_FROM_UNVISITED + " to remove a location from unvisited places.");
        System.out.println("Enter " + MOVE_TO_VISITED + " to move a location from unvisited to visited list.");
        System.out.println("Enter " + CHECK_UNVISITED + " to check the list of unvisited locations.");
        System.out.println("Enter " + BACK + " to return to the original screen.");

        userInputEditUnvisited();
    }

    // MODIFIES: this
    // EFFECTS: takes user input for unvisited list and performs appropriate actions which are: add location, remove
    //          location, move location to visited, check unvisited list
    private static void userInputEditUnvisited() {
        String str = getUserInputString();
        if (str.length() > 0) {
            switch (str) {
                case ADD_LOCATION_TO_UNVISITED:
                    addLocation(unvisited);
                    break;

                case REMOVE_LOCATION_FROM_UNVISITED:
                    removeLocation(unvisited);
                    break;

                case MOVE_TO_VISITED:
                    move(unvisited, visited);
                    break;

                case CHECK_UNVISITED:
                    parseInputInfo(unvisited, str);
                    break;

                default:
                    parseInput(str);
                    break;
            }
        }
    }

    // EFFECTS: prints instructions for editing unvisited list and calls for user input and user input results
    private static void parseInputEditVisited(String str) {
        System.out.println("Enter " + ADD_LOCATION_TO_VISITED + " to add a new location to visited places.");
        System.out.println("Enter " + REMOVE_LOCATION_FROM_VISITED + " to remove a location from visited places.");
        System.out.println("Enter " + MOVE_TO_UNVISITED + " to move a location from visited to unvisited list.");
        System.out.println("Enter " + CHECK_VISITED + " to check the list of visited locations.");
        System.out.println("Enter " + BACK + " to return to the original screen.");

        userInputEditVisited();
    }

    // MODIFIES: this
    // EFFECTS: takes user input for visited list and performs appropriate actions which are: add location, remove
    //          location, move location to unvisited, check visited list
    private static void userInputEditVisited() {
        String str = getUserInputString();
        if (str.length() > 0) {
            switch (str) {
                case ADD_LOCATION_TO_VISITED:
                    addLocation(visited);
                    break;

                case REMOVE_LOCATION_FROM_VISITED:
                    removeLocation(visited);
                    break;

                case MOVE_TO_UNVISITED:
                    move(visited, unvisited);
                    break;

                case CHECK_VISITED:
                    parseInputInfo(visited, str);
                    break;

                default:
                    parseInput(str);
                    break;
            }
        }
    }

    // EFFECTS: prints instructions for distance and calls for user input and user input results
    private static void parseInputFindDistance() {
        System.out.println("Enter " + NEW_LOCATIONS + " to find the distance between two new locations");
        System.out.println("Enter " + EXISTING_LOCATIONS_UNVISITED + " to find the distance between two existing "
                + "unvisited " + "locations");
        System.out.println("Enter " + EXISTING_LOCATIONS_VISITED + " to find the distance between two existing visited "
                + "locations");
        System.out.println("Enter " + BACK + " to return to the original screen.");

        userInputFindDistance();
    }

    // EFFECTS: takes user input and performs appropriate actions which are: find distance between two new locations,
    //          find distance between two unvisited locations, find distance between two visited locations, find
    //          distance between an unvisited and visited location
    private static void userInputFindDistance() {
        String str = getUserInputString();
        if (str.length() > 0) {
            switch (str) {
                case NEW_LOCATIONS:
                    newLocations();
                    break;
                case EXISTING_LOCATIONS_UNVISITED:
                    existingLocations(unvisited, unvisited);
                    break;
                case EXISTING_LOCATIONS_VISITED:
                    existingLocations(visited, visited);
                    break;
                case EXISTING_UNVISITED_VISITED:
                    existingLocations(unvisited, visited);
                    break;
                default:
                    parseInput(str);
                    break;
            }
        }
    }

    // EFFECTS: takes user input for latitude and longitude of two locations and returns the distance between them
    private static void newLocations() {
        System.out.println("Enter the latitude for the first location.");
        double latOne = Double.parseDouble(getUserInputString());
        System.out.println("Enter the longitude for the first location.");
        double longOne = Double.parseDouble(getUserInputString());
        Location tempOne = new Location("NULL", "NULL", latOne, longOne);

        System.out.println("Enter the latitude for the second location.");
        double latTwo = Double.parseDouble(getUserInputString());
        System.out.println("Enter the longitude for the second location.");
        double longTwo = Double.parseDouble(getUserInputString());
        Location tempTwo = new Location("NULL", "NULL", latTwo, longTwo);

        System.out.println("The distance between these two locations is: "
                + travelMap.distanceTwoPoints(tempOne, tempTwo) + "KM");
        System.out.println("Enter " + BACK + " to return to the original screen.");
    }

    // EFFECTS: takes user input for name, address, latitude, longitude, to create a new Location class
    protected static Location userInputNewLocation() {
        System.out.println("Please input the name of the location.");
        String locationName = getUserInputString();
        System.out.println("Please input the address of the location.");
        String locationAddress = getUserInputString();
        System.out.println("Please input the latitude of the location.");
        double locationLat = Double.parseDouble(getUserInputString());
        System.out.println("Please input the longitude of the location.");
        double locationLong = Double.parseDouble(getUserInputString());

        return new Location(locationName, locationAddress, locationLat, locationLong);
    }

    // EFFECTS: takes in user input
    protected static String getUserInputString() {
        String str = "";
        if (userInput.hasNext()) {
            str = userInput.nextLine();
        }
        return str;
    }


}