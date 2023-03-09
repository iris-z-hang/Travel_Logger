package ui;

import model.Location;

import java.util.ArrayList;
import static ui.MapFunctions.*;

import model.Map;

// ListFunctions class helps reduce duplications in the MapFunctions class
public class ListFunctions {

    private static final String BACK = "BACK";
    private static final String INFO = "INFO";


    // MODIFIES: list (this in subclasses)
    // EFFECTS: asks the user to input the location they want to add to a list and adds that location to the list
    protected static void addLocation(ArrayList<Location> list) {
        System.out.println("Please input the following information about the location: ");
        Map.addLocation(list, userInputNewLocation());
        System.out.println("Location successfully added.");
        System.out.println("Enter " + BACK + " to return to the original screen.");
    }


    // MODIFIES: list (this in subclasses)
    // EFFECTS: asks the user to input the location they want to remove from the list
    //          if that location exists, removes location from list, else prints "location not found"
    protected static void removeLocation(ArrayList<Location> list) {
        System.out.println("Enter the name of the location you want removed.");
        String removeName = getUserInputString();
        if (travelMap.removeLocation(list, travelMap.findLocationByName(list, removeName))) {
            System.out.println(removeName + " successfully removed.");
        } else {
            System.out.println("Location not found.");
        }
        System.out.println("Enter " + BACK + " to return to the original screen.");
    }

    // MODIFIES: list1 (this in subclasses), list2 (this in subclasses)
    // EFFECTS: asks the user for name of location to move and moves location from list1 to list2 if found,
    //          else prints "location not found"
    protected static void move(ArrayList<Location> list1, ArrayList<Location> list2) {
        System.out.println("Enter the name of the location you want to move.");
        String moveNameU = getUserInputString();
        if (travelMap.moveLocation(list1, list2, moveNameU)) {
            System.out.println(moveNameU + " successfully moved.");
        } else {
            System.out.println("Location not found.");
        }
        System.out.println("Enter " + BACK + " to return to the original screen.");
    }

    // EFFECTS: prints the locations on a list for the user
    //          gives option for further information on a specified location
    protected static void parseInputInfo(ArrayList<Location> list, String str) {
        System.out.println("Here are the locations: ");

        for (Location location: travelMap.getLocations(list)) {
            System.out.println(location.getName());
        }

        System.out.println("Enter " + INFO + " to get information about a location");
        userInputInfo(list);
    }

    // EFFECTS: provides further information on a location in a list for the user if they type INFO
    //          information provided is name, address, latitude, longitude
    protected static void userInputInfo(ArrayList<Location> list) {
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
                    MapFunctions.parseInput(str);
                    break;
            }
        }
    }

    // EFFECTS: finds the distance between two existing lists using the haversine formula
    protected static void existingLocations(ArrayList<Location> list1, ArrayList<Location> list2) {
        System.out.println("Enter the name of the first location.");
        String nameOne = getUserInputString();
        Location locationOne = travelMap.findLocationByName(list1, nameOne);

        System.out.println("Enter the name of the second location.");
        String nameTwo = getUserInputString();
        Location locationTwo = travelMap.findLocationByName(list2, nameTwo);

        System.out.println(travelMap.distanceTwoPoints(locationOne, locationTwo));
        System.out.println("Enter " + BACK + " to return to the original screen.");
    }


}
