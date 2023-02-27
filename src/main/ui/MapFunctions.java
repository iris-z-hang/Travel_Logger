package ui;

import model.Location;
import model.Map;
import model.VisitedUnvisitedLists;

import java.util.ArrayList;
import java.util.Scanner;

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
    private static final String EXISTING_UNVISITED_TO_VISITED = "EXISTING U TO V";
    private static final String EXISTING_VISITED_TO_UNVISITED = "EXISTING V TO U";

    private static final String UNVISITED = "UNVISITED";
    private static final String VISITED = "VISITED";
    private static final String MOVE_TO_UNVISITED = "MOVE TO U";
    private static final String MOVE_TO_VISITED = "MOVE TO V";
    private static final String INFO = "INFO";

    protected static Map travelMap;
    protected String cityName;
    static Scanner userInput;

    static ArrayList<Location> unvisited;
    static ArrayList<Location> visited;

    public MapFunctions() {
        userInput = new Scanner(System.in);
        travelMap = new VisitedUnvisitedLists();
        travelMap.setTripFinished(false);

        unvisited = new ArrayList<>();
        visited = new ArrayList<>();
    }

    public void handleUserInput() {
        printInstructions();
        String str;

        while (!travelMap.getTripFinished()) {
            str = getUserInputString();
            parseInput(str);
        }
    }

    void printIntro() {
        System.out.println("Hello, welcome to Travel Logger.");
        System.out.println("Please input the name of the city you are visiting.");
        this.cityName = userInput.nextLine();
        System.out.println("Creating new map for " + cityName + "\n");

    }

    public static void printInstructions() {
        System.out.println("What action would you like to perform?\n");

        System.out.println("Enter " + UNVISITED + " to view/edit the list of unvisited locations");
        System.out.println("Enter " + VISITED + " to view/edit the list of visited locations");

        System.out.println("Enter " + FIND_DISTANCE + " to find the distance between two locations.");
        System.out.println("Enter " + QUIT + " to exit this application.");

    }

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
                    parseInputFindDistance(str);
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

    private static void quit() {
        System.out.println("Goodbye.");
        travelMap.setTripFinished(true);
        userInput.close();
    }

    private static void parseInputEditUnvisited(String str) {
        System.out.println("Enter " + ADD_LOCATION_TO_UNVISITED + " to add a new location to unvisited places.");
        System.out.println("Enter " + REMOVE_LOCATION_FROM_UNVISITED + " to remove a location from unvisited places.");
        System.out.println("Enter " + MOVE_TO_VISITED + " to move a location from unvisited to visited list.");
        System.out.println("Enter " + CHECK_UNVISITED + " to check the list of unvisited locations.");
        System.out.println("Enter " + BACK + " to return to the original screen.");

        userInputEditUnvisited();
    }

    private static void userInputEditUnvisited() {
        String str = getUserInputString();
        if (str.length() > 0) {
            switch (str) {
                case ADD_LOCATION_TO_UNVISITED:
                    addLocationToUnvisited();
                    break;

                case REMOVE_LOCATION_FROM_UNVISITED:
                    removeLocationFromUnvisited();
                    break;

                case MOVE_TO_VISITED:
                    moveToVisited();
                    break;

                case CHECK_UNVISITED:
                    parseInputInfoUnvisited(str);
                    System.out.println("Enter " + BACK + " to return to the original screen.");
                    break;

                default:
                    parseInput(str);
                    break;
            }
        }
    }

    private static void addLocationToUnvisited() {
        addLocation(unvisited);
    }

    private static void removeLocationFromUnvisited() {
        removeLocation(unvisited);
    }

    private static void moveToVisited() {
        move(unvisited, visited);
    }

    private static void parseInputEditVisited(String str) {
        System.out.println("Enter " + ADD_LOCATION_TO_VISITED + " to add a new location to visited places.");
        System.out.println("Enter " + REMOVE_LOCATION_FROM_VISITED + " to remove a location from visited places.");
        System.out.println("Enter " + MOVE_TO_UNVISITED + " to move a location from visited to unvisited list.");
        System.out.println("Enter " + CHECK_VISITED + " to check the list of visited locations.");
        System.out.println("Enter " + BACK + " to return to the original screen.");

        userInputEditVisited();
    }

    private static void userInputEditVisited() {
        String str = getUserInputString();
        if (str.length() > 0) {
            switch (str) {
                case ADD_LOCATION_TO_VISITED:
                    addLocationToVisited();
                    break;

                case REMOVE_LOCATION_FROM_VISITED:
                    removeLocationFromVisited();
                    break;

                case MOVE_TO_UNVISITED:
                    moveToUnvisited();
                    break;

                case CHECK_VISITED:
                    parseInputInfoVisited(str);
                    System.out.println("Enter " + BACK + " to return to the original screen.");
                    break;

                default:
                    parseInput(str);
                    break;
            }
        }
    }

    private static void addLocationToVisited() {
        addLocation(visited);
    }

    private static void removeLocationFromVisited() {
        removeLocation(visited);
    }

    private static void moveToUnvisited() {
        move(visited, unvisited);
    }

    private static void parseInputInfoUnvisited(String str) {
        parseInputInfo(unvisited, str);
    }

    private static void parseInputInfoVisited(String str) {
        parseInputInfo(visited, str);
    }

    private static void parseInputFindDistance(String str) {
        System.out.println("Enter " + NEW_LOCATIONS + " to find the distance between two new locations");
        System.out.println("Enter " + EXISTING_LOCATIONS_UNVISITED + " to find the distance between two existing "
                + "unvisited " + "locations");
        System.out.println("Enter " + EXISTING_LOCATIONS_VISITED + " to find the distance between two existing visited "
                + "locations");
        System.out.println("Enter " + BACK + " to return to the original screen.");

        userInputFindDistance();
    }

    private static void userInputFindDistance() {
        String str = getUserInputString();
        if (str.length() > 0) {
            switch (str) {
                case NEW_LOCATIONS:
                    newLocations();
                    break;
                case EXISTING_LOCATIONS_UNVISITED:
                    existingLocationsUnvisited();
                    break;
                case EXISTING_LOCATIONS_VISITED:
                    existingLocationsVisited();
                    break;
                case EXISTING_UNVISITED_TO_VISITED:
                    existingUnvisitedToVisited();
                    break;
                case EXISTING_VISITED_TO_UNVISITED:
                    existingVisitedToUnvisited();
                    break;
                default:
                    parseInput(str);
                    break;
            }
        }
    }

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

    private static void existingLocationsUnvisited() {
        existingLocations(unvisited, unvisited);
    }

    private static void existingLocationsVisited() {
        existingLocations(visited, visited);
    }

    private static void existingUnvisitedToVisited() {
        existingLocations(unvisited, visited);
    }

    private static void existingVisitedToUnvisited() {
        existingLocations(visited, unvisited);
    }



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


    protected static String getUserInputString() {
        String str = "";
        if (userInput.hasNext()) {
            str = userInput.nextLine();
        }
        return str;
    }


}

// REFERENCE: FITLIFEGYM FROM BASICS EDX