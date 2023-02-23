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
        System.out.println("Enter " + ADD_LOCATION_TO_VISITED + "to add a new location to unvisited places.");
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
                    // TODO: PRINT NAME OF LOCATIONS RATHER THAN POINTER
                    System.out.println("Here are the locations on your unvisited list: ");
                    travelMap.printUnvisitedLocations();
                    printInstructions();
                    break;

                case CHECK_VISITED:
                    // TODO: PRINT NAME OF LOCATIONS RATHER THAN POINTER
                    System.out.println("Here are the locations on your visited list: ");
                    travelMap.printVisitedLocations();
                    printInstructions();
                    break;


                case FIND_DISTANCE:
                    // TODO: OPTION TO INPUT TWO NEW LOCATIONS OR USE TWO LOCATIONS FROM VISITED/UNVISITED LIST
                    // TODO: IF CHOOSING USE LOCATIONS FROM LIST, BE ABLE TO IDENTIFY THE LOCATION BY NAME
                    System.out.println(
                            travelMap.distanceTwoPoints(userInputNewLocation(), userInputNewLocationSecond()));
                    printInstructions();
                    break;

                case QUIT:
                    System.out.println("Goodbye.");
                    userInput.close();
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