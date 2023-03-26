package ui;

import model.Location;
import model.Map;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

// represents application's main window frame
class MapGUI extends JFrame implements ActionListener {

    private JFrame mainFrame;
    private JPanel panel;

    private JLabel label;

    private JButton unvisitedButton;
    private JButton visitedButton;
    private JButton distanceButton;
    private JButton loadButton;
    private JButton saveButton;
    private JButton backButton;

    private JButton addLocationButton;
    private JButton removeLocationButton;
    private JButton moveLocationButton;
    private JButton checkLocationButton;

    private JPanel unvisitedPanel;
    private JPanel visitedPanel;
    private JPanel distancePanel;

    private Map map;

    public MapGUI() {
        super("Map App");
        initializeMainFrame();
        initializeButtons();
        buttonActions();
        backButton = new JButton("Go Back");


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
            addListPanel(unvisitedPanel);
        } else if (e.getActionCommand().equals("Visited")) {
            addListPanel(visitedPanel);
        } else if (e.getActionCommand().equals("Distance")) {
//            distanceCommands();
            // TODO: distance command
        } else if (e.getActionCommand().equals("Load")) {
//            loadCommand();
            // TODO: load command
        } else if (e.getActionCommand().equals("Save")) {
//            saveCommand();
            // TODO: save command
        }

    }

    public void addListPanel(JPanel p) {
        mainFrame.add(p, BorderLayout.CENTER);

        p.setBorder(BorderFactory.createBevelBorder(2));
        p.setLayout(new GridLayout(0, 1));

        p.setVisible(true);
        panel.setVisible(false);

        addLocationButton = new JButton("Add a new location.");
        removeLocationButton = new JButton("Remove a location.");
        moveLocationButton = new JButton("Move a location to the visited list.");
        checkLocationButton = new JButton("Look at your unvisited locations list");

        p.add(addLocationButton);
        p.add(removeLocationButton);
        p.add(moveLocationButton);
        p.add(checkLocationButton);
        p.add(backButton);
    }




    public static void main(String[] args) {
        new MapGUI();

    }

}

