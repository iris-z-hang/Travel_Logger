package model;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private boolean tripFinished;
    protected String city;

    public Map(String city) {
        this.city = city;
        tripFinished = false;
    }

    public void setTripFinished(boolean tripFinished) {
        this.tripFinished = tripFinished;
    }

}
