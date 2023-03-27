package ui;

import model.Location;
import model.Map;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

// represents application's main window frame
class MapGUI extends JFrame implements ActionListener {

    static final String JSON_STORE = "./data/mapTest.json";

    private JFrame mainFrame;
    private JPanel panel;

    private JButton unvisitedButton;
    private JButton visitedButton;
    private JButton distanceButton;
    private JButton loadButton;
    private JButton saveButton;

    private JPanel unvisitedPanel;
    private JPanel visitedPanel;
    private JPanel distancePanel;

    private Map travelMap;
    private String cityName;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    public MapGUI() {
        super("Map App");

        travelMap = new Map(cityName);

        initializeMainFrame();
        initializeMainButtons();
        initializeListButtons();
        initializeDistanceButtons();
        buttonActions();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

    }

    public void initializeMainFrame() {
        panel = new JPanel();
        mainFrame = new JFrame();

        unvisitedPanel = new JPanel();
        visitedPanel = new JPanel();
        distancePanel = new JPanel();

        panel.setBorder(BorderFactory.createBevelBorder(2));
        panel.setLayout(new GridLayout(0, 1));

        mainFrame.add(panel, BorderLayout.CENTER);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("Travel Logger");
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setMinimumSize(new Dimension(600, 400));
    }

    public void initializeMainButtons() {
        unvisitedButton = new JButton("Unvisited Locations");
        visitedButton = new JButton("Visited Locations");
        distanceButton = new JButton("Calculate Distance");
        loadButton = new JButton("Load Map");
        saveButton = new JButton("Save Map");

        panel.add(unvisitedButton);
        panel.add(visitedButton);
        panel.add(distanceButton);
        panel.add(loadButton);
        panel.add(saveButton);
        panel.add(quitButton());
    }

    public void initializeListButtons() {
        unvisitedPanel.add(addLocationButton(travelMap.getUnvisited()));
        unvisitedPanel.add(removeLocationButton(travelMap.getUnvisited()));
        unvisitedPanel.add(moveLocationButton(travelMap.getUnvisited(), travelMap.getVisited()));
        unvisitedPanel.add(checkLocationButton(travelMap.getUnvisited()));
        unvisitedPanel.add(backButton());

        visitedPanel.add(addLocationButton(travelMap.getVisited()));
        visitedPanel.add(removeLocationButton(travelMap.getVisited()));
        visitedPanel.add(moveLocationButton(travelMap.getVisited(), travelMap.getUnvisited()));
        visitedPanel.add(checkLocationButton(travelMap.getVisited()));
        visitedPanel.add(backButton());
    }

    public void initializeDistanceButtons() {
        distancePanel.add(newDistanceButton());
        distancePanel.add(existingDistanceButton());
        distancePanel.add(backButton());
    }

    public void buttonActions() {
        unvisitedButton.addActionListener(this);
        unvisitedButton.setActionCommand("Unvisited");

        visitedButton.addActionListener(this);
        visitedButton.setActionCommand("Visited");

        distanceButton.addActionListener(this);
        distanceButton.setActionCommand("Distance");

        loadButton.addActionListener(this);
        loadButton.setActionCommand("Load");

        saveButton.addActionListener(this);
        saveButton.setActionCommand("Save");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Unvisited")) {
            loadListPanel(unvisitedPanel, travelMap.getUnvisited());
        } else if (e.getActionCommand().equals("Visited")) {
            loadListPanel(visitedPanel, travelMap.getVisited());
        } else if (e.getActionCommand().equals("Move")) {
            loadMovePanel(unvisitedPanel, travelMap.getUnvisited(), travelMap.getVisited());
        } else if (e.getActionCommand().equals("Move")) {
            loadMovePanel(visitedPanel, travelMap.getVisited(), travelMap.getUnvisited());
        } else if (e.getActionCommand().equals("Distance")) {
            loadDistancePanel();
        } else if (e.getActionCommand().equals("Save")) {
            saveMap();
        } else if (e.getActionCommand().equals("Load")) {
            loadMap();
        }
    }

    public JButton backButton() {
        JButton showBackButton = new JButton("Go Back");
        showBackButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(true);
                unvisitedPanel.setVisible(false);
                visitedPanel.setVisible(false);
                distancePanel.setVisible(false);
            }
        });

        return showBackButton;
    }

    public JButton addLocationButton(ArrayList<Location> locationList) {
        JButton showAddLocationButton = new JButton("Add Location");
        showAddLocationButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameResponse;
                String addressResponse;
                double longitudeResponse;
                double latitudeResponse;

                nameResponse = JOptionPane.showInputDialog("Name of Location");
                addressResponse = JOptionPane.showInputDialog("Address of Location");
                longitudeResponse = Double.parseDouble(JOptionPane.showInputDialog("Longitude of Location"));
                latitudeResponse = Double.parseDouble(JOptionPane.showInputDialog("Latitude of Location"));

                Location userLocation = new Location(nameResponse, addressResponse, longitudeResponse,
                        latitudeResponse);
                travelMap.addLocation(travelMap.getLocations(locationList), userLocation);
                JOptionPane.showMessageDialog(panel,nameResponse + " successfully added.");
            }
        });

        return showAddLocationButton;
    }

    public JButton removeLocationButton(ArrayList<Location> locationList) {
        JButton showRemoveLocationButton = new JButton("Remove Location");
        showRemoveLocationButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String removeName;
                removeName = JOptionPane.showInputDialog("Name of Location to Remove");

                if (travelMap.removeLocation(travelMap.getLocations(locationList),
                        travelMap.findLocationByName(travelMap.getLocations(locationList), removeName))) {
                    JOptionPane.showMessageDialog(panel,removeName + " successfully removed.");
                } else {
                    JOptionPane.showMessageDialog(panel,"Location not found.");
                }
            }
        });

        return showRemoveLocationButton;
    }

    public JButton moveLocationButton(ArrayList<Location> locationList1, ArrayList<Location> locationList2) {
        JButton showMoveLocationButton = new JButton("Move Location");
        showMoveLocationButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String moveName;
                moveName = JOptionPane.showInputDialog("Name of Location to Move");

                if (travelMap.moveLocation(travelMap.getLocations(locationList1),
                        travelMap.getLocations(locationList2), moveName)) {
                    JOptionPane.showMessageDialog(panel,moveName + " successfully moved.");
                } else {
                    JOptionPane.showMessageDialog(panel,"Location not found.");
                }
            }
        });

        return showMoveLocationButton;
    }

    public JButton checkLocationButton(ArrayList<Location> locationList) {
        JButton showCheckLocationButton = new JButton("View Location List");
        showCheckLocationButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] locationArray = travelMap.getLocations(locationList).toArray();
                JOptionPane.showMessageDialog(panel,new JList(locationArray));
            }
        });

        return showCheckLocationButton;
    }

    public void loadListPanel(JPanel p, ArrayList<Location> locationList) {
        mainFrame.add(p, BorderLayout.CENTER);

        p.setBorder(BorderFactory.createBevelBorder(2));
        p.setLayout(new GridLayout(0, 1));

        p.setVisible(true);
        panel.setVisible(false);
    }

    public void loadMovePanel(JPanel p, ArrayList<Location> locationList1, ArrayList<Location> locationList2) {
        mainFrame.add(p, BorderLayout.CENTER);

        p.setBorder(BorderFactory.createBevelBorder(2));
        p.setLayout(new GridLayout(0, 1));

        p.setVisible(true);
        panel.setVisible(false);

    }

    public void saveMap() {
        try {
            jsonWriter.open();
            jsonWriter.write(travelMap);
            jsonWriter.close();
            JOptionPane.showMessageDialog(panel,"Saved map to" + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(panel,"Unable to write to file: " + JSON_STORE);
        }
    }

    public void loadMap() {
        try {
            travelMap = jsonReader.read();
            JOptionPane.showMessageDialog(panel,"Loaded previous map from" + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(panel,"Unable to read from file: " + JSON_STORE);
        }
    }

    public JButton quitButton() {
        JButton showQuitButton = new JButton("Quit");
        showQuitButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                travelMap.setTripFinished(true);
                JOptionPane.showMessageDialog(panel,"Goodbye.");
                System.exit(0);
            }
        });

        return showQuitButton;

    }

    // TODO: load and save do not work anymore

// --------------------------------------------------------------------------------------------------------------------

    public void loadDistancePanel() {
        mainFrame.add(distancePanel, BorderLayout.CENTER);

        distancePanel.setBorder(BorderFactory.createBevelBorder(2));
        distancePanel.setLayout(new GridLayout(0, 1));

        distancePanel.setVisible(true);
        panel.setVisible(false);
        unvisitedPanel.setVisible(false);
        visitedPanel.setVisible(false);
    }

    public JButton newDistanceButton() {
        JButton showNewDistanceButton = new JButton("New Locations");
        showNewDistanceButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(panel, "Please input information for your first location.");
                String nameResponse1 = JOptionPane.showInputDialog("Name of Location");
                String addressResponse1 = JOptionPane.showInputDialog("Address of Location");
                double longitudeResponse1 = Double.parseDouble(JOptionPane.showInputDialog("Longitude of Location"));
                double latitudeResponse1 = Double.parseDouble(JOptionPane.showInputDialog("Latitude of Location"));

                JOptionPane.showMessageDialog(panel, "Please input information for your second location.");
                String nameResponse2 = JOptionPane.showInputDialog("Name of Location");
                String addressResponse2 = JOptionPane.showInputDialog("Address of Location");
                double longitudeResponse2 = Double.parseDouble(JOptionPane.showInputDialog("Longitude of Location"));
                double latitudeResponse2 = Double.parseDouble(JOptionPane.showInputDialog("Latitude of Location"));

                Location userLocation1 = new Location(nameResponse1, addressResponse1, longitudeResponse1,
                        latitudeResponse1);
                Location userLocation2 = new Location(nameResponse2, addressResponse2, longitudeResponse2,
                        latitudeResponse2);
                JOptionPane.showMessageDialog(panel,
                        travelMap.distanceTwoPoints(userLocation1, userLocation2) + " KM");
            }
        });

        return showNewDistanceButton;
    }

    public JButton existingDistanceButton() {
        JButton showExistingDistanceButton = new JButton("Existing Locations");
        showExistingDistanceButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Location> allLocations = new ArrayList<>();

                for (Location location : travelMap.getUnvisited()) {
                    allLocations.add(location);
                }

                for (Location location : travelMap.getVisited()) {
                    allLocations.add(location);
                }

                String nameResponse1;
                String nameResponse2;

                nameResponse1 = JOptionPane.showInputDialog("Name of First Location");
                nameResponse2 = JOptionPane.showInputDialog("Name of Second Location");

                Location locationOne = travelMap.findLocationByName(allLocations, nameResponse1);
                Location locationTwo = travelMap.findLocationByName(allLocations, nameResponse2);

                JOptionPane.showMessageDialog(panel, travelMap.distanceTwoPoints(locationOne, locationTwo));
            }
        });

        return showExistingDistanceButton;
    }


    public static void main(String[] args) {
        new MapGUI();

    }

}

