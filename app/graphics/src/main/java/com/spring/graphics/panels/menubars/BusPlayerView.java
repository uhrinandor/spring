package com.spring.graphics.panels.menubars;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Point;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import com.spring.controllers.controllers.BusPlayerController;
import com.spring.graphics.components.BaseButton;
import com.spring.graphics.components.LegendButton;

public class BusPlayerView extends JPanel {
    BusPlayerController controller;
 
    private final JLabel playerIdLabel;
    private final JLabel pointsLabel;
 
    public BusPlayerView(BusPlayerController controller) {
        this.controller = controller;
 
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
 
        JPanel topRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        topRow.setOpaque(false);
 
        topRow.add(new JLabel("Bus-player Menu"));
 
        topRow.add(new JLabel("Player Id:"));
        playerIdLabel = new JLabel("");
        topRow.add(playerIdLabel);
 
        topRow.add(new JLabel("Points:"));
        pointsLabel = new JLabel("");
        topRow.add(pointsLabel);
 
        JPanel buttonRow = new JPanel(new GridLayout(1, 3, 5, 0));
        buttonRow.setOpaque(false);
 
        BaseButton stepButton    = new BaseButton("Step");
        BaseButton endTurnButton = new BaseButton("End Turn");
 
        stepButton.addActionListener(e -> handleStep());
        endTurnButton.addActionListener(e -> handleEndTurn());
 
        buttonRow.add(stepButton);
        buttonRow.add(endTurnButton);
        buttonRow.add(new LegendButton());
 
        add(topRow);
        add(buttonRow);
    }
 
    public void refresh() {
        if (controller.info() == null) return;
 
        playerIdLabel.setText(controller.info().toString());
        pointsLabel.setText(String.valueOf(controller.info().getPoints()));
    }
 
    private void handleStep() {
        String input = JOptionPane.showInputDialog(this, "Lépés sorszáma:");
        if (input == null) return;
 
        try {
            int serial = Integer.parseInt(input.trim());
            controller.setNext(serial);
            refresh();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Számot kell megadni!");
        }
    }
 
    private void handleEndTurn() {
        controller.nextPlayer();
        refresh();
    }
}