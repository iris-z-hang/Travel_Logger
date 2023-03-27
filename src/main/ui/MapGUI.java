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
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.*;

import static ui.MapFunctions.JSON_STORE;

// represents application's main window frame
class MapGUI extends JFrame implements ActionListener {

    private JFrame mainFrame;
    private JPanel panel;

    private JButton unvisitedButton;
    private JButton visitedButton;
    private JButton distanceButton;
    private JButton loadButton;
    private JButton saveButton;
    JButton continueButton = new JButton("Continue");

    private JButton distanceNewButton;
    private JButton distanceExistingButton;

    private JPanel unvisitedPanel;
    private JPanel visitedPanel;
    private JPanel distancePanel;

    private JPanel moreInformationPanel;

    private Map travelMap;
    private String cityName;

    JTextField txtInput = new JTextField("");

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    public MapGUI() {
        super("Map App");

        travelMap = new Map(cityName);

        initializeMainFrame();
        initializeButtons();
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

    public void initializeButtons() {
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
            loadListPanel(unvisitedPanel);
        } else if (e.getActionCommand().equals("Visited")) {
            loadListPanel(visitedPanel);
        } else if (e.getActionCommand().equals("Distance")) {
            loadDistancePanel();
        } else if (e.getActionCommand().equals("Save")) {
            saveMap();
        } else if (e.getActionCommand().equals("Load")) {
            loadMap();
        } else if (e.getActionCommand().equals("Add Location")) {
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

    public JButton addLocationButton() {
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
                travelMap.addLocation(travelMap.getUnvisited(), userLocation);
                JOptionPane.showMessageDialog(panel,nameResponse + "successfully added.");
            }
        });

        return showAddLocationButton;
    }

    public JButton removeLocationButton() {
        JButton showRemoveLocationButton = new JButton("Remove Location");
        showRemoveLocationButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String removeName;
                removeName = JOptionPane.showInputDialog("Name of Location to Remove");

                if (travelMap.removeLocation(travelMap.getUnvisited(),
                        travelMap.findLocationByName(travelMap.getUnvisited(), removeName))) {
                    JOptionPane.showMessageDialog(panel,removeName + " successfully removed.");
                } else {
                    JOptionPane.showMessageDialog(panel,"Location not found.");

                }
            }
        });

        return showRemoveLocationButton;
    }

    public JButton moveLocationButton() {
        JButton showMoveLocationButton = new JButton("Move Location");
        showMoveLocationButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String moveName;
                moveName = JOptionPane.showInputDialog("Name of Location to Move");

                if (travelMap.moveLocation(travelMap.getUnvisited(), travelMap.getUnvisited(), moveName)) {
                    JOptionPane.showMessageDialog(panel,moveName + " successfully moved.");
                } else {
                    JOptionPane.showMessageDialog(panel,"Location not found.");
                }

            }
        });

        return showMoveLocationButton;
    }

    public JButton checkLocationButton() {
        JButton showCheckLocationButton = new JButton("View Location List");
        showCheckLocationButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] locationArray = travelMap.getUnvisited().toArray();

                JPanel displayListPanel = new JPanel();
                mainFrame.add(displayListPanel, BorderLayout.CENTER);
                displayListPanel.setBorder(BorderFactory.createBevelBorder(2));
                displayListPanel.setLayout(new GridLayout(0, 1));

                displayListPanel.setVisible(true);
                panel.setVisible(false);
                unvisitedPanel.setVisible(false);
                visitedPanel.setVisible(false);
                distancePanel.setVisible(false);

                displayListPanel.add(new JList(locationArray));

            }
        });

        return showCheckLocationButton;
    }

    public void loadListPanel(JPanel p) {
        mainFrame.add(p, BorderLayout.CENTER);

        p.setBorder(BorderFactory.createBevelBorder(2));
        p.setLayout(new GridLayout(0, 1));

        p.setVisible(true);
        panel.setVisible(false);

        p.add(addLocationButton());
        p.add(removeLocationButton());
        p.add(moveLocationButton());
        p.add(checkLocationButton());
        p.add(backButton());

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


// --------------------------------------------------------------------------------------------------------------------

    public void loadDistancePanel() {
        mainFrame.add(distancePanel, BorderLayout.CENTER);

        distancePanel.setBorder(BorderFactory.createBevelBorder(2));
        distancePanel.setLayout(new GridLayout(0, 1));

        distancePanel.setVisible(true);
        panel.setVisible(false);
        unvisitedPanel.setVisible(false);
        visitedPanel.setVisible(false);
        distancePanel.setVisible(false);

        distanceNewButton = new JButton("Distance from New Locations");
        distanceExistingButton = new JButton("Distance from Existing Locations");

        distancePanel.add(distanceNewButton);
        distancePanel.add(distanceExistingButton);
        distancePanel.add(backButton());

        distanceButtonActions();
    }

    public void distanceButtonActions() {
        distanceNewButton.addActionListener(this);
        distanceNewButton.setActionCommand("New Locations");

        distanceExistingButton.addActionListener(this);
        distanceExistingButton.setActionCommand("Existing Locations");
    }

    private void newLocationsCommand() {

    }

    private void existingLocationsCommand() {

    }

    public static void main(String[] args) {
        new MapGUI();

    }

}

