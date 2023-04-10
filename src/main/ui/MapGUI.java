package ui;

import model.EventLog;
import model.Location;
import model.Map;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
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

    protected Map travelMap;
    private String cityName;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs new MapGUI class with a new map, jsonWriter/jsonReader, and initializes mainframe, buttons
    public MapGUI() throws IOException {
        super("Map App");

        travelMap = new Map("City");
        travelMap.setTripFinished(false);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        initializeMainFrame();
        initializeMainButtons();
        initializeListButtons();
        initializeDistanceButtons();
        buttonActions();
    }

    // MODIFIES: this
    // EFFECTS: initializes the main frame, and the panels in the application
    public void initializeMainFrame() throws IOException {
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
        mainFrame.setMinimumSize(new Dimension(700, 1000));

        BufferedImage myPicture = ImageIO.read(new File("./data/Political-World-Map-3360.jpg"));
        Image newImage = myPicture.getScaledInstance(400, 250, Image.SCALE_DEFAULT);
        JLabel picLabel = new JLabel(new ImageIcon(newImage));

        JLabel welcomeLabel = new JLabel("Welcome to Travel Logger!", SwingConstants.CENTER);
        panel.add(welcomeLabel);
        panel.add(picLabel);
        panel.repaint();
        panel.revalidate();
    }

    // MODIFIES: this
    // EFFECTS: initializes the buttons on the main frame
    public void initializeMainButtons() {
        unvisitedButton = new JButton("Unvisited Locations");
        visitedButton = new JButton("Visited Locations");
        distanceButton = new JButton("Calculate Distance");
        loadButton = new JButton("Load Map");
        saveButton = new JButton("Save Map");

        panel.add(unvisitedButton);
        panel.add(visitedButton);
        panel.add(distanceButton);
        panel.add(loadButton());
        panel.add(saveButton());
        panel.add(quitButton());
    }

    // MODIFIES: this
    // EFFECTS: initializes the buttons on the unvisited and visited panels
    public void initializeListButtons() {
        unvisitedPanel.add(addLocationButtonU());
        unvisitedPanel.add(removeLocationButtonU());
        unvisitedPanel.add(moveLocationButtonU());
        unvisitedPanel.add(checkLocationButtonU());
        unvisitedPanel.add(backButton());

        visitedPanel.add(addLocationButtonV());
        visitedPanel.add(removeLocationButtonV());
        visitedPanel.add(moveLocationButtonV());
        visitedPanel.add(checkLocationButtonV());
        visitedPanel.add(backButton());
    }

    // MODIFIES: this
    // EFFECTS: initializes the buttons on the distance panel
    public void initializeDistanceButtons() {
        distancePanel.add(newDistanceButton());
        distancePanel.add(existingDistanceButton());
        distancePanel.add(backButton());
    }

    // MODIFIES: this
    // EFFECTS: sets commands for buttons available in the main panel
    public void buttonActions() {
        unvisitedButton.addActionListener(this);
        unvisitedButton.setActionCommand("Unvisited");

        visitedButton.addActionListener(this);
        visitedButton.setActionCommand("Visited");

        distanceButton.addActionListener(this);
        distanceButton.setActionCommand("Distance");
    }

    // MODIFIES: this
    // EFFECTS: initializes the main panel's button responses
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Unvisited")) {
            loadListPanel(unvisitedPanel);
        } else if (e.getActionCommand().equals("Visited")) {
            loadListPanel(visitedPanel);
        } else if (e.getActionCommand().equals("Move")) {
            loadMovePanel(unvisitedPanel, travelMap.getUnvisited(), travelMap.getVisited());
        } else if (e.getActionCommand().equals("Move")) {
            loadMovePanel(visitedPanel, travelMap.getVisited(), travelMap.getUnvisited());
        } else if (e.getActionCommand().equals("Distance")) {
            loadDistancePanel();
        }
    }

    // MODIFIES: this
    // EFFECTS: creates save button and sets the action to save the application state
    public JButton saveButton() {
        JButton showSaveButton = new JButton("Save");
        showSaveButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(travelMap);
                    jsonWriter.close();
                    JOptionPane.showMessageDialog(panel,"Saved map to" + JSON_STORE);
                } catch (FileNotFoundException f) {
                    JOptionPane.showMessageDialog(panel,"Unable to write to file: " + JSON_STORE);
                }
            }
        });

        return showSaveButton;
    }

    // MODIFIES: this
    // EFFECTS: initializes the load button to load a previous state
    public JButton loadButton() {
        JButton showLoadButton = new JButton("Load");
        showLoadButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    travelMap = jsonReader.read();
                    JOptionPane.showMessageDialog(panel,"Loaded previous map from" + JSON_STORE);
                } catch (IOException g) {
                    JOptionPane.showMessageDialog(panel,"Unable to read from file: " + JSON_STORE);
                }
            }
        });

        return showLoadButton;
    }

    // MODIFIES: this
    // EFFECTS: initializes the back button to return to the main panel
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

    // MODIFIES: this
    // EFFECTS: initializes the add button for the unvisited list to add new locations to that list
    public JButton addLocationButtonU() {
        JButton showAddLocationButtonU = new JButton("Add Location");
        showAddLocationButtonU.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameResponse = JOptionPane.showInputDialog("Name of Location");
                String addressResponse = JOptionPane.showInputDialog("Address of Location");
                double longitudeResponse = Double.parseDouble(JOptionPane.showInputDialog("Longitude of Location"));
                double latitudeResponse = Double.parseDouble(JOptionPane.showInputDialog("Latitude of Location"));

                Location userLocation = new Location(nameResponse, addressResponse, longitudeResponse,
                        latitudeResponse);
                travelMap.addLocation(travelMap.getUnvisited(), userLocation);
                JOptionPane.showMessageDialog(panel,nameResponse + " successfully added.");
            }
        });

        return showAddLocationButtonU;
    }

    // MODIFIES: this
    // EFFECTS: initializes the add button for the visited list to add new locations to that list
    public JButton addLocationButtonV() {
        JButton showAddLocationButtonV = new JButton("Add Location");
        showAddLocationButtonV.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameResponse = JOptionPane.showInputDialog("Name of Location");
                String addressResponse = JOptionPane.showInputDialog("Address of Location");
                double longitudeResponse = Double.parseDouble(JOptionPane.showInputDialog("Longitude of Location"));
                double latitudeResponse = Double.parseDouble(JOptionPane.showInputDialog("Latitude of Location"));

                Location userLocation = new Location(nameResponse, addressResponse, longitudeResponse,
                        latitudeResponse);
                travelMap.addLocation(travelMap.getVisited(), userLocation);
                JOptionPane.showMessageDialog(panel,nameResponse + " successfully added.");
            }
        });

        return showAddLocationButtonV;
    }

    // REQUIRES: unvisited list must have at least one element
    // MODIFIES: this
    // EFFECTS: initializes the remove button for the unvisited list to remove a location from that list
    public JButton removeLocationButtonU() {
        JButton showRemoveLocationButtonU = new JButton("Remove Location");
        showRemoveLocationButtonU.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String removeName = JOptionPane.showInputDialog("Name of Location to Remove");

                if (travelMap.removeLocation(travelMap.getUnvisited(),
                        travelMap.findLocationByName(travelMap.getUnvisited(), removeName))) {
                    JOptionPane.showMessageDialog(panel,removeName + " successfully removed.");
                } else {
                    JOptionPane.showMessageDialog(panel,"Location not found.");
                }
            }
        });

        return showRemoveLocationButtonU;
    }

    // REQUIRES: visited list must have at least one element
    // MODIFIES: this
    // EFFECTS: initializes the remove button for the visited list to remove a location from that list
    public JButton removeLocationButtonV() {
        JButton showRemoveLocationButtonV = new JButton("Remove Location");
        showRemoveLocationButtonV.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String removeName = JOptionPane.showInputDialog("Name of Location to Remove");

                if (travelMap.removeLocation(travelMap.getVisited(),
                        travelMap.findLocationByName(travelMap.getVisited(), removeName))) {
                    JOptionPane.showMessageDialog(panel,removeName + " successfully removed.");
                } else {
                    JOptionPane.showMessageDialog(panel,"Location not found.");
                }
            }
        });

        return showRemoveLocationButtonV;
    }

    // MODIFIES: this
    // EFFECTS: initializes move button to move a location from unvisited list to visited
    public JButton moveLocationButtonU() {
        JButton showMoveLocationButtonU = new JButton("Move Location");
        showMoveLocationButtonU.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String moveName = JOptionPane.showInputDialog("Name of Location to Move");

                if (travelMap.moveLocation(travelMap.getUnvisited(), travelMap.getVisited(), moveName)) {
                    JOptionPane.showMessageDialog(panel,moveName + " successfully moved.");
                } else {
                    JOptionPane.showMessageDialog(panel,"Location not found.");
                }
            }
        });

        return showMoveLocationButtonU;
    }

    // MODIFIES: this
    // EFFECTS: initializes move button to move a location from visited list to unvisited
    public JButton moveLocationButtonV() {
        JButton showMoveLocationButtonV = new JButton("Move Location");
        showMoveLocationButtonV.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String moveName = JOptionPane.showInputDialog("Name of Location to Move");

                if (travelMap.moveLocation(travelMap.getVisited(), travelMap.getUnvisited(), moveName)) {
                    JOptionPane.showMessageDialog(panel,moveName + " successfully moved.");
                } else {
                    JOptionPane.showMessageDialog(panel,"Location not found.");
                }
            }
        });

        return showMoveLocationButtonV;
    }

    // MODIFIES: this
    // EFFECTS: initializes check location button to display the unvisited list to the user
    public JButton checkLocationButtonU() {
        JButton showCheckLocationButtonU = new JButton("View Location List");
        showCheckLocationButtonU.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] locationArray = travelMap.getUnvisited().toArray();
                JOptionPane.showMessageDialog(panel, new JList(locationArray));
            }
        });

        return showCheckLocationButtonU;
    }

    // MODIFIES: this
    // EFFECTS: initializes check location button to display the visited list to the user
    public JButton checkLocationButtonV() {
        JButton showCheckLocationButtonV = new JButton("View Location List");
        showCheckLocationButtonV.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] locationArray = travelMap.getVisited().toArray();
                JOptionPane.showMessageDialog(panel, new JList(locationArray));
            }
        });

        return showCheckLocationButtonV;
    }

    // MODIFIES: this
    // EFFECTS: initializes the panel that represents both the visited and unvisited lists
    public void loadListPanel(JPanel p) {
        mainFrame.add(p, BorderLayout.CENTER);

        p.setBorder(BorderFactory.createBevelBorder(2));
        p.setLayout(new GridLayout(0, 1));

        p.setVisible(true);
        panel.setVisible(false);
        distancePanel.setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: initializes the move location panel
    public void loadMovePanel(JPanel p, ArrayList<Location> locationList1, ArrayList<Location> locationList2) {
        mainFrame.add(p, BorderLayout.CENTER);

        p.setBorder(BorderFactory.createBevelBorder(2));
        p.setLayout(new GridLayout(0, 1));

        p.setVisible(true);
        panel.setVisible(false);
        distancePanel.setVisible(false);

    }

    // MODIFIES: this
    // EFFECTS: initializes quit button to end the trip and exit the application
    public JButton quitButton() {
        JButton showQuitButton = new JButton("Quit");
        showQuitButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                travelMap.setTripFinished(true);
                JOptionPane.showMessageDialog(panel,"Goodbye.");

                for (model.Event event : model.EventLog.getInstance()) {
                    System.out.println(event);
                }

                System.exit(0);
            }
        });

        return showQuitButton;

    }

    // MODIFIES: this
    // EFFECTS: initializes distance panel
    public void loadDistancePanel() {
        mainFrame.add(distancePanel, BorderLayout.CENTER);

        distancePanel.setBorder(BorderFactory.createBevelBorder(2));
        distancePanel.setLayout(new GridLayout(0, 1));

        distancePanel.setVisible(true);
        panel.setVisible(false);
        unvisitedPanel.setVisible(false);
        visitedPanel.setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: initializes new distance button to find distance between two new locations
    public JButton newDistanceButton() {
        JButton showNewDistanceButton = new JButton("New Locations");
        showNewDistanceButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(panel, "Please input information for your first location.");
                double longitudeResponse1 = Double.parseDouble(JOptionPane.showInputDialog("Longitude of Location"));
                double latitudeResponse1 = Double.parseDouble(JOptionPane.showInputDialog("Latitude of Location"));

                JOptionPane.showMessageDialog(panel, "Please input information for your second location.");
                double longitudeResponse2 = Double.parseDouble(JOptionPane.showInputDialog("Longitude of Location"));
                double latitudeResponse2 = Double.parseDouble(JOptionPane.showInputDialog("Latitude of Location"));

                Location userLocation1 = new Location(null, null, longitudeResponse1,
                        latitudeResponse1);
                Location userLocation2 = new Location(null, null, longitudeResponse2,
                        latitudeResponse2);
                JOptionPane.showMessageDialog(panel,
                        travelMap.distanceTwoPoints(userLocation1, userLocation2) + " KM");
            }
        });

        return showNewDistanceButton;
    }

    // MODIFIES: this
    // EFFECTS: initializes new distance button to find distance between two locations on either list
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
        try {
            new MapGUI();
        } catch (FileNotFoundException e) {
            System.out.println("The file is not found.");
        } catch (IOException i) {
            System.out.println("IO EXCEPTION");
        }
    }

}

