package ui;

import model.Map;

public class Main {

    static String cityName;

    public static void main(String[] args) {
        Map travelMap = new Map(cityName);
        MapFunctions mapFunc = new MapFunctions(travelMap);
        System.out.println("Welcome.");
        mapFunc.printIntro();
        mapFunc.handleUserInput();

    }
}
