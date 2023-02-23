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
    private static final String NEW_LOCATIONS = "NEW";
    private static final String EXISTING_LOCATIONS_UNVISITED = "EXISTING UNVISITED";
    private static final String EXISTING_LOCATIONS_VISITED = "EXISTING VISITED";

    private static Map travelMap;
    private String cityName;
    Scanner userInput;

    // EFFECTS: constructs a new map with an empty visited and unvisited locations list
    //          prints intro on the console
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
        System.out.println("Please input the name of the city you are visiting. \n");
        this.cityName = userInput.nextLine();
        System.out.println("Creating new map for " + cityName);

    }

    public void printInstructions() {
        System.out.println("What action would you like to perform?");
        System.out.println("Enter " + ADD_LOCATION_TO_UNVISITED + " to add a new location to unvisited places.");
        System.out.println("Enter " + ADD_LOCATION_TO_VISITED + " to add a new location to visited places.");
        System.out.println("Enter " + REMOVE_LOCATION_FROM_UNVISITED + " to remove a location from unvisited places.");
        System.out.println("Enter " + REMOVE_LOCATION_FROM_VISITED + " to remove a location from visited places.");
        System.out.println("Enter " + CHECK_VISITED + " to check the list of visited locations.");
        System.out.println("Enter " + CHECK_UNVISITED + " to check the list of unvisited locations.");
        System.out.println("Enter " + FIND_DISTANCE + " to find the distance between two locations.");
        System.out.println("Enter " + QUIT + " to exit this application.");
    }

    private void parseInput (String str) {
        if (str.length() > 0) {
            switch (str) {
                case ADD_LOCATION_TO_UNVISITED:
                    System.out.println("Please input the following information about the unvisited location: ");
                    travelMap.addUnvisitedLocation(userInputNewLocation());
                    printInstructions();
                    break;

                case ADD_LOCATION_TO_VISITED:
                    System.out.println("Please input the following information about the visited location: ");
                    travelMap.addVisitedLocation(userInputNewLocation());
                    printInstructions();
                    break;

                case REMOVE_LOCATION_FROM_UNVISITED:
                    if (travelMap.removeUnvisitedLocation(userInputNewLocation())) {
                        travelMap.removeUnvisitedLocation(userInputNewLocation());
                        System.out.println("Location removed from unvisited list: ");
                    } else {
                        System.out.println("Location not found.");
                        printInstructions();
                    }
                    break;

                case REMOVE_LOCATION_FROM_VISITED:
                    if (travelMap.removeVisitedLocation(userInputNewLocation())) {
                        travelMap.removeVisitedLocation(userInputNewLocation());
                        System.out.println("Location removed from visited list: ");
                    } else {
                        System.out.println("Location not found.");
                        printInstructions();
                    }
                    break;

                case CHECK_UNVISITED:
                    System.out.println("Here are the locations on your unvisited list: ");
                    travelMap.printUnvisitedLocations();
                    printInstructions();
                    break;

                case CHECK_VISITED:
                    System.out.println("Here are the locations on your visited list: ");
                    travelMap.printVisitedLocations();
                    printInstructions();
                    break;


                case FIND_DISTANCE:
                    userInputFindDistance(str);
                    printInstructions();
                    break;

                case QUIT:
                    System.out.println("Goodbye.");
                    userInput.close();

                default:
                    System.out.println("Invalid command.");
                    printInstructions();
                    break;
            }
        }
    }

    private void userInputFindDistance(String str) {
        System.out.println("Enter " + NEW_LOCATIONS + " to find the distance between two new locations");
        System.out.println("Enter " + EXISTING_LOCATIONS_UNVISITED + " to find the distance between two existing " +
                "unvisited " + "locations");
        System.out.println("Enter " + EXISTING_LOCATIONS_VISITED + " to find the distance between two existing visited "
                + "locations");

        if (str.length() > 0) {
            switch (str) {
                case NEW_LOCATIONS:
                    System.out.println(
                            travelMap.distanceTwoPoints(userInputNewLocation(), userInputNewLocationSecond()));
                    printInstructions();
                    break;

                case EXISTING_LOCATIONS_UNVISITED:
                    // TODO: FIND LOCATIONS BY NAME AND ACQUIRE LONG LAT
                    System.out.println("Enter the name of the first location.");
                    String unvisitedNameOne = getUserInputString();
                    System.out.println("Enter the name of the second location.");
                    String unvisitedNameTwo = getUserInputString();
                    travelMap.distanceTwoPoints(travelMap.findLocationByNameUnvisited(unvisitedNameOne),
                            travelMap.findLocationByNameUnvisited(unvisitedNameTwo));
                    printInstructions();
                    break;

                case EXISTING_LOCATIONS_VISITED:
                    // TODO: FIND LOCATIONS BY NAME AND ACQUIRE LONG LAT
                    System.out.println("Enter the name of the first location.");
                    String visitedNameOne = getUserInputString();
                    System.out.println("Enter the name of the second location.");
                    String visitedNameTwo = getUserInputString();
                    travelMap.distanceTwoPoints(travelMap.findLocationByNameVisited(visitedNameOne),
                            travelMap.findLocationByNameVisited(visitedNameTwo));
                    printInstructions();
                    break;

                default:
                    System.out.println("Invalid command.");
                    printInstructions();
                    break;
            }
        }
    }

    private Location userInputNewLocation() {
        System.out.println("Please input the name of the location.");
        String locationName = getUserInputString();
        System.out.println("Please input the address of the location.");
        String locationAddress = getUserInputString();
        System.out.println("Please input the city name of the location.");
        String locationCity = getUserInputString();
        System.out.println("Please input the longitude of the location.");
        double locationLong = Double.parseDouble(getUserInputString());
        System.out.println("Please input the latitude of the location.");
        double locationLat = Double.parseDouble(getUserInputString());

        return new Location(locationName, locationAddress, locationCity, locationLat, locationLong);
    }

    private Location userInputNewLocationSecond() {
        System.out.println("Please input the name of the location.");
        String locationName = getUserInputString();
        System.out.println("Please input the address of the location.");
        String locationAddress = getUserInputString();
        System.out.println("Please input the city name of the location.");
        String locationCity = getUserInputString();
        System.out.println("Please input the longitude of the location.");
        double locationLong = Double.parseDouble(getUserInputString());
        System.out.println("Please input the latitude of the location.");
        double locationLat = Double.parseDouble(getUserInputString());

        return new Location(locationName, locationAddress, locationCity, locationLat, locationLong);
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