package ui;

import model.Location;
import model.Map;

import java.util.Scanner;

public class MapFunctions {
    private static final String ADD_LOCATION_TO_VISITED = "ADD VISITED";
    private static final String ADD_LOCATION_TO_UNVISITED = "ADD UNVISITED";
    private static final String REMOVE_LOCATION_FROM_UNVISITED = "REMOVE UNVISITED";
    private static final String REMOVE_LOCATION_FROM_VISITED = "REMOVE VISITED";
    private static final String CHECK_VISITED = "CHECK VISITED";
    private static final String CHECK_UNVISITED = "CHECK UNVISITED";
    private static final String FIND_DISTANCE = "DISTANCE";
    private static final String QUIT = "QUIT";
    private static final String BACK = "BACK";
    private static final String NEW_LOCATIONS = "NEW";
    private static final String EXISTING_LOCATIONS_UNVISITED = "EXISTING UNVISITED";
    private static final String EXISTING_LOCATIONS_VISITED = "EXISTING VISITED";

    private static final String EDIT_UNVISITED = "EDIT UNVISITED";
    private static final String EDIT_VISITED = "EDIT VISITED";
    private static final String MOVE_TO_UNVISITED = "MOVE TO UNVISITED";
    private static final String MOVE_TO_VISITED = "MOVE TO VISITED";
    private static final String INFO = "INFO";

    private static Map travelMap;
    private String cityName;
    Scanner userInput;

    public MapFunctions(Map map) {
        userInput = new Scanner(System.in);
        travelMap = new Map(cityName);
        travelMap.setTripFinished(false);
    }

    public void handleUserInput() {
        printInstructions();
        String str;

        while (!travelMap.tripFinished) {
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

    public void printInstructions() {
        System.out.println("What action would you like to perform?\n");

        System.out.println("Enter " + CHECK_UNVISITED + " to check the list of unvisited locations.");
        System.out.println("Enter " + CHECK_VISITED + " to check the list of visited locations.");

        System.out.println("Enter " + EDIT_UNVISITED + " to edit the list of unvisited locations");
        System.out.println("Enter " + EDIT_VISITED + " to edit the list of visited locations");

        System.out.println("Enter " + FIND_DISTANCE + " to find the distance between two locations.");
        System.out.println("Enter " + QUIT + " to exit this application.");

    }

    private void parseInput (String str) {
        if (str.length() > 0) {
            switch (str) {
                case EDIT_UNVISITED:
                    parseInputEditUnvisited(str);
                    break;

                case EDIT_VISITED:
                    parseInputEditVisited(str);
                    break;

                case CHECK_UNVISITED:
                    parseInputInfoUnvisited(str);
                    System.out.println("Enter " + BACK + " to return to the original screen.");
                    break;

                case CHECK_VISITED:
                    parseInputInfoVisited(str);
                    System.out.println("Enter " + BACK + " to return to the original screen.");
                    break;


                case FIND_DISTANCE:
                    parseInputFindDistance(str);
                    System.out.println("Enter " + BACK + " to return to the original screen.");
                    break;

                case BACK:
                    printInstructions();
                    break;

                case QUIT:
                    System.out.println("Goodbye.");
                    travelMap.setTripFinished(true);
                    userInput.close();
                    break;

                default:
                    System.out.println("Invalid command.");
                    System.out.println("Enter " + BACK + " to return to the original screen.");
                    break;
            }
        }
    }

    private void parseInputEditUnvisited(String str) {
        System.out.println("Enter " + ADD_LOCATION_TO_UNVISITED + " to add a new location to unvisited places.");
        System.out.println("Enter " + REMOVE_LOCATION_FROM_UNVISITED + " to remove a location from unvisited places.");
        System.out.println("Enter " + MOVE_TO_VISITED + " to move a location from unvisited to visited list.");
        System.out.println("Enter " + CHECK_UNVISITED + " to check the list of unvisited locations.");
        System.out.println("Enter " + BACK + " to return to the original screen.");

        userInputEditUnvisited();
    }

    private void userInputEditUnvisited() {
        String str = getUserInputString();
        if (str.length() > 0) {
            switch (str) {
                case ADD_LOCATION_TO_UNVISITED:
                    System.out.println("Please input the following information about the unvisited location: ");
                    travelMap.addUnvisitedLocation(userInputNewLocation());
                    System.out.println("Location successfully added.");
                    System.out.println("Enter " + BACK + " to return to the original screen.");
                    break;

                case REMOVE_LOCATION_FROM_UNVISITED:
                    System.out.println("Enter the name of the location you want removed.");
                    String removeNameU = getUserInputString();
                    if (travelMap.removeUnvisitedLocation(travelMap.findLocationByNameUnvisited(removeNameU))) {
                        System.out.println(removeNameU + " successfully removed.");
                    } else {
                        System.out.println("Location not found.");
                    }
                    System.out.println("Enter " + BACK + " to return to the original screen.");
                    break;

                case MOVE_TO_VISITED:
                    System.out.println("Enter the name of the location you want to move.");
                    String moveNameU = getUserInputString();
                    if (travelMap.moveUnvisitedToVisited(moveNameU)) {
                        System.out.println(moveNameU + " successfully moved.");
                    } else {
                        System.out.println("Location not found.");
                    }
                    System.out.println("Enter " + BACK + " to return to the original screen.");
                    break;

                default:
                    parseInput(str);
                    break;
            }
        }
    }

    private void parseInputEditVisited(String str) {
        System.out.println("Enter " + ADD_LOCATION_TO_VISITED + " to add a new location to visited places.");
        System.out.println("Enter " + REMOVE_LOCATION_FROM_VISITED + " to remove a location from visited places.");
        System.out.println("Enter " + MOVE_TO_UNVISITED + " to move a location from visited to unvisited list.");
        System.out.println("Enter " + CHECK_VISITED + " to check the list of visited locations.");
        System.out.println("Enter " + BACK + " to return to the original screen.");

        userInputEditVisited();
    }

    private void userInputEditVisited() {
        String str = getUserInputString();
        if (str.length() > 0) {
            switch (str) {
                case ADD_LOCATION_TO_VISITED:
                    System.out.println("Please input the following information about the visited location: ");
                    travelMap.addVisitedLocation(userInputNewLocation());
                    System.out.println("Location successfully added.");
                    System.out.println("Enter " + BACK + " to return to the original screen.");
                    break;

                case REMOVE_LOCATION_FROM_VISITED:
                    System.out.println("Enter the name of the location you want removed.");
                    String removeNameV = getUserInputString();
                    if (travelMap.removeVisitedLocation(travelMap.findLocationByNameVisited(removeNameV))) {
                        System.out.println(removeNameV + " successfully removed.");
                    } else {
                        System.out.println("Location not found.");
                    }
                    break;

                case MOVE_TO_UNVISITED:
                    System.out.println("Enter the name of the location you want to move.");
                    String moveNameV = getUserInputString();
                    if (travelMap.moveVisitedToUnvisited(moveNameV)) {
                        System.out.println(moveNameV + " successfully moved.");
                    } else {
                        System.out.println("Location not found.");
                    }
                    break;

                default:
                    parseInput(str);
                    break;
            }
        }
    }

    private void parseInputInfoUnvisited(String str) {
        System.out.println("Here are the locations on your unvisited list: ");
        travelMap.printUnvisitedLocations();
        System.out.println("Enter " + INFO + " to get information about a location");
        userInputInfoUnvisited();
    }

    private void userInputInfoUnvisited() {
        String str = getUserInputString();

        if (str.length() > 0) {
            switch (str) {
                case INFO:
                    System.out.println("Enter the name of the location.");
                    String locationName = getUserInputString();
                    System.out.println(travelMap.getInformationUnvisited(locationName));
                    break;

                case BACK:
                    printInstructions();

                default:
                    parseInput(str);
                    break;
            }
        }
    }

    private void parseInputInfoVisited(String str) {
        System.out.println("Here are the locations on your visited list: ");
        travelMap.printVisitedLocations();
        System.out.println("Enter " + INFO + " to get information about a location");
        userInputInfoVisited();
    }

    private void userInputInfoVisited() {
        String str = getUserInputString();

        if (str.length() > 0) {
            switch (str) {
                case INFO:
                    System.out.println("Enter the name of the location.");
                    String locationName = getUserInputString();
                    System.out.println(travelMap.getInformationVisited(locationName));
                    break;

                case BACK:
                    printInstructions();

                default:
                    parseInput(str);
                    break;
            }
        }
    }

    private void parseInputFindDistance(String str) {
        System.out.println("Enter " + NEW_LOCATIONS + " to find the distance between two new locations");
        System.out.println("Enter " + EXISTING_LOCATIONS_UNVISITED + " to find the distance between two existing " +
                "unvisited " + "locations");
        System.out.println("Enter " + EXISTING_LOCATIONS_VISITED + " to find the distance between two existing visited "
                + "locations");
        System.out.println("Enter " + BACK + " to return to the original screen.");

        userInputFindDistance();
    }

    private void userInputFindDistance() {
        String str = getUserInputString();

        if (str.length() > 0) {
            switch (str) {
                case NEW_LOCATIONS:
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

                    System.out.println("The distance between these two locations is: " +
                            travelMap.distanceTwoPoints(tempOne, tempTwo) + "KM");
                    System.out.println("Enter " + BACK + " to return to the original screen.");
                    break;

                case EXISTING_LOCATIONS_UNVISITED:
                    System.out.println("Enter the name of the first location.");
                    String unvisitedNameOne = getUserInputString();
                    Location locationOne = travelMap.findLocationByNameUnvisited(unvisitedNameOne);

                    System.out.println("Enter the name of the second location.");
                    String unvisitedNameTwo = getUserInputString();
                    Location locationTwo = travelMap.findLocationByNameUnvisited(unvisitedNameTwo);

                    System.out.println(travelMap.distanceTwoPoints(locationOne, locationTwo));
                    System.out.println("Enter " + BACK + " to return to the original screen.");
                    break;

                case EXISTING_LOCATIONS_VISITED:
                    System.out.println("Enter the name of the first location.");
                    String visitedNameOne = getUserInputString();
                    System.out.println("Enter the name of the second location.");
                    String visitedNameTwo = getUserInputString();
                    travelMap.distanceTwoPoints(travelMap.findLocationByNameVisited(visitedNameOne),
                            travelMap.findLocationByNameVisited(visitedNameTwo));
                    System.out.println("Enter " + BACK + " to return to the original screen.");
                    break;

                default:
                    parseInput(str);
                    break;
            }
        }
    }

    private Location userInputNewLocation() {
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


    private String getUserInputString() {
        String str = "";
        if (userInput.hasNext()) {
            str = userInput.nextLine();
        }
        return str;
    }


}

// REFERENCE: FITLIFEGYM FROM BASICS EDX