package ui;

import model.Location;
import model.Map;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// ui class that contains most of the user interaction

public class MapFunctions {
    static final String JSON_STORE = "./data/map.json";

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

    private static final String SAVE = "SAVE";
    private static final String LOAD = "LOAD";

    protected Map travelMap;
    private String cityName;
    static Scanner userInput;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: constructor for MapFunctions class, sets up userInput, travelMap, and sets tripFinished to false
    //          initializes two arraylists for abstraction
    public MapFunctions() throws FileNotFoundException {
        userInput = new Scanner(System.in);
        travelMap = new Map("City");
        travelMap.setTripFinished(false);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
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
        travelMap.setCityName(cityName);
    }

    // EFFECTS: prints the initial menu of commands
    public void printInstructions() {
        System.out.println("What action would you like to perform?\n");

        System.out.println("Enter " + UNVISITED + " to view/edit the list of unvisited locations");
        System.out.println("Enter " + VISITED + " to view/edit the list of visited locations");

        System.out.println("Enter " + FIND_DISTANCE + " to find the distance between two locations.");

        System.out.println("Enter " + SAVE + " to save a list.");
        System.out.println("Enter " + LOAD + " to load a list.");

        System.out.println("Enter " + QUIT + " to exit this application.");

    }

    // MODIFIES: this
    // EFFECTS: takes user input and performs appropriate actions which are: enter unvisited menu, enter visited menu,
    //          enter distance menu, return to original screen, and quit the program
    @SuppressWarnings("methodlength")
    private void parseInput(String str) {
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
                case SAVE:
                    saveMap();
                    break;
                case LOAD:
                    loadMap();
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

    // EFFECTS: saves current map to JSON file
    public void saveMap() {
        try {
            jsonWriter.open();
            jsonWriter.write(travelMap);
            jsonWriter.close();
            System.out.println("Saved map to" + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads saved JSON file to current map
    public void loadMap() {
        try {
            travelMap = jsonReader.read();
            System.out.println("Loaded previous map from" + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

    }

    // MODIFIES: this
    // EFFECTS: quits the program
    private void quit() {
        System.out.println("Goodbye.");
        travelMap.setTripFinished(true);
        userInput.close();
    }

    // EFFECTS: prints the locations on a list for the user
    //          gives option for further information on a specified location
    private void parseInputInfo(ArrayList<Location> list, String str) {
        System.out.println("Here are the locations: ");

        for (Location location: travelMap.getLocations(list)) {
            System.out.println(location.getName());
        }

        System.out.println("Enter " + INFO + " to get information about a location");
        userInputInfo(list);
    }

    // EFFECTS: provides further information on a location in a list for the user if they type INFO
    //          information provided is name, address, latitude, longitude
    private void userInputInfo(ArrayList<Location> list) {
        String str = getUserInputString();

        if (str.length() > 0) {
            switch (str) {
                case INFO:
                    System.out.println("Enter the name of the location.");
                    String locationName = getUserInputString();
                    System.out.println(travelMap.getInformation(list, locationName));
                    break;

                case BACK:
                    printInstructions();

                default:
                    parseInput(str);
                    break;
            }
        }
    }

    // EFFECTS: prints instructions for editing unvisited list and calls for user input and user input results
    private void parseInputEditUnvisited(String str) {
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
    private void userInputEditUnvisited() {
        String str = getUserInputString();
        if (str.length() > 0) {
            switch (str) {
                case ADD_LOCATION_TO_UNVISITED:
                    addLocation(travelMap.getUnvisited());
                    break;

                case REMOVE_LOCATION_FROM_UNVISITED:
                    removeLocation(travelMap.getUnvisited());
                    break;

                case MOVE_TO_VISITED:
                    move(travelMap.getUnvisited(), travelMap.getVisited());
                    break;

                case CHECK_UNVISITED:
                    parseInputInfo(travelMap.getUnvisited(), str);
                    break;

                default:
                    parseInput(str);
                    break;
            }
        }
    }

    // MODIFIES: list (this in subclasses)
    // EFFECTS: asks the user to input the location they want to add to a list and adds that location to the list
    private void addLocation(ArrayList<Location> list) {
        System.out.println("Please input the following information about the location: ");
        travelMap.addLocation(list, userInputNewLocation());
        System.out.println("Location successfully added.");
        System.out.println("Enter " + BACK + " to return to the original screen.");
    }

    // EFFECTS: prints instructions for editing unvisited list and calls for user input and user input results
    private void parseInputEditVisited(String str) {
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
    private void userInputEditVisited() {
        String str = getUserInputString();
        if (str.length() > 0) {
            switch (str) {
                case ADD_LOCATION_TO_VISITED:
                    addLocation(travelMap.getVisited());
                    break;

                case REMOVE_LOCATION_FROM_VISITED:
                    removeLocation(travelMap.getVisited());
                    break;

                case MOVE_TO_UNVISITED:
                    move(travelMap.getVisited(), travelMap.getUnvisited());
                    break;

                case CHECK_VISITED:
                    parseInputInfo(travelMap.getVisited(), str);
                    break;

                default:
                    parseInput(str);
                    break;
            }
        }
    }

    // MODIFIES: list (this in subclasses)
    // EFFECTS: asks the user to input the location they want to remove from the list
    //          if that location exists, removes location from list, else prints "location not found"
    private void removeLocation(ArrayList<Location> list) {
        System.out.println("Enter the name of the location you want removed.");
        String removeName = getUserInputString();
        if (travelMap.removeLocation(list, travelMap.findLocationByName(list, removeName))) {
            System.out.println(removeName + " successfully removed.");
        } else {
            System.out.println("Location not found.");
        }
        System.out.println("Enter " + BACK + " to return to the original screen.");
    }

    // EFFECTS: prints instructions for distance and calls for user input and user input results
    private void parseInputFindDistance() {
        System.out.println("Enter " + NEW_LOCATIONS + " to find the distance between two new locations");
        System.out.println("Enter " + EXISTING_LOCATIONS_UNVISITED + " to find the distance between two existing "
                + "unvisited " + "locations");
        System.out.println("Enter " + EXISTING_LOCATIONS_VISITED + " to find the distance between two existing visited "
                + "locations");
        System.out.println("Enter " + EXISTING_UNVISITED_VISITED + " to find the distance between two existing visited "
                + "locations");
        System.out.println("Enter " + BACK + " to return to the original screen.");

        userInputFindDistance();
    }

    // MODIFIES: this
    // EFFECTS: moves location from one list to another
    private void move(ArrayList<Location> list1, ArrayList<Location> list2) {
        System.out.println("Enter the name of the location you want to move.");
        String moveNameU = getUserInputString();
        if (travelMap.moveLocation(list1, list2, moveNameU)) {
            System.out.println(moveNameU + " successfully moved.");
        } else {
            System.out.println("Location not found.");
        }
        System.out.println("Enter " + BACK + " to return to the original screen.");
    }

    // EFFECTS: takes user input and performs appropriate actions which are: find distance between two new locations,
    //          find distance between two unvisited locations, find distance between two visited locations, find
    //          distance between an unvisited and visited location
    private void userInputFindDistance() {
        String str = getUserInputString();
        if (str.length() > 0) {
            switch (str) {
                case NEW_LOCATIONS:
                    newLocations();
                    break;
                case EXISTING_LOCATIONS_UNVISITED:
                    existingLocations(travelMap.getUnvisited(), travelMap.getUnvisited());
                    break;
                case EXISTING_LOCATIONS_VISITED:
                    existingLocations(travelMap.getVisited(), travelMap.getVisited());
                    break;
                case EXISTING_UNVISITED_VISITED:
                    existingLocations(travelMap.getUnvisited(), travelMap.getVisited());
                    break;
                default:
                    parseInput(str);
                    break;
            }
        }
    }

    // EFFECTS: takes user input for latitude and longitude of two locations and returns the distance between them
    private void newLocations() {
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

    // EFFECTS: finds the distance between two existing lists using the haversine formula
    private void existingLocations(ArrayList<Location> list1, ArrayList<Location> list2) {
        System.out.println("Enter the name of the first location.");
        String nameOne = getUserInputString();
        Location locationOne = travelMap.findLocationByName(list1, nameOne);

        System.out.println("Enter the name of the second location.");
        String nameTwo = getUserInputString();
        Location locationTwo = travelMap.findLocationByName(list2, nameTwo);

        System.out.println(travelMap.distanceTwoPoints(locationOne, locationTwo));
        System.out.println("Enter " + BACK + " to return to the original screen.");
    }

    // EFFECTS: takes in user input
    private String getUserInputString() {
        String str = "";
        if (userInput.hasNext()) {
            str = userInput.nextLine();
        }
        return str;
    }


}