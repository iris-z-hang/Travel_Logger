package ui;

import model.Map;
import model.Location.*;
import model.Distance.*;

import java.util.Scanner;

public class Main {
    private Map travelMap;
    private String cityName;
    Scanner userInput = new Scanner(System.in);

    // EFFECTS: constructs a new map with an empty visited and unvisited locations list
    //          prints intro on the console
    public Main() {
        travelMap = new Map(cityName);
        travelMap.setTripFinished(false);

        printIntro();
    }

    private void printIntro() {
        System.out.println("Hello, welcome to Travel Logger.");
        System.out.println("Please input the name of the city you are visiting. \n");
        String cityNameInput = userInput.nextLine();
        System.out.println("Creating new map for " + cityNameInput);

    }

    public static void main(String[] args) {
        new Main();
    }


}
