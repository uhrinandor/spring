package com.spring.graphics.panels.menubars;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.spring.controllers.controllers.BusPlayerController;
import com.spring.graphics.components.BaseButton;
import com.spring.graphics.components.LegendButton;
import com.spring.graphics.panels.map.interfaces.IMap;
import com.spring.models.field.IField;

public class BusPlayerView extends JPanel {
    BusPlayerController controller;
    IMap map;

    public BusPlayerView(BusPlayerController controller, IMap map) {
        super();
        this.controller = controller;
        this.map = map;

        setLayout(new GridLayout(2, 3));

        add(new JLabel("Bus-player Menu"));
        add(new JLabel("Player Id: " + controller.info().toString()));
        add(new JLabel("Points: " + controller.info().getPoints()));
        add(new JLabel("Target: " + controller.info().getBus().geStation().getField().getId()));

        BaseButton stepButton = new BaseButton("Step");
        BaseButton endTurnButton = new BaseButton("End Turn");

        stepButton.addActionListener(e -> handleStep());
        endTurnButton.addActionListener(e -> handleEndTurn());

        add(stepButton);
        add(endTurnButton);
        add(new LegendButton());
    }

    private void handleStep() {
        List<IField> available = controller.listAvailable();
        map.waitForField(serial -> controller.setNext(serial), available);
    }

    private void handleEndTurn() {
        controller.nextPlayer();
    }
}
