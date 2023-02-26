package ui;

import model.Map;
import model.VisitedUnvisitedLists;

public class Main {

    public static void main(String[] args) {
        String cityName = "cityName";
        Map map = new Map(cityName);
        MapFunctions mapFunc = new MapFunctions(map);
        mapFunc.printIntro();
        mapFunc.handleUserInput();

    }
}
