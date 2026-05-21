package com.spring.graphics.panels.menubars;


import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.spring.controllers.controllers.SnowPlowController;
import com.spring.graphics.components.BaseButton;

public class SnowplowView extends JPanel {
    SnowPlowController controller;
    public SnowplowView(SnowPlowController controller){
        setLayout(new GridLayout(2, 5));
        JLabel menuLabel = new JLabel("Sp-selected Menu");
        JLabel playerIdLabel = new JLabel("Player Id: " + controller.getSp().getId());
        JLabel currentSpLabel = new JLabel("Current player: "); // TODO
        JLabel cashLabel = new JLabel("Cash: " + controller.getSp().getPoints());

        JButton stepButton = new BaseButton("Step");
        JButton shopButton = new BaseButton("Shop");
        JButton infoButton = new BaseButton("Info");
        JButton backButton = new BaseButton("Back");
        JButton legendButton = new BaseButton("Legend");

        add(menuLabel);
        add(playerIdLabel);
        add(currentSpLabel);
        add(cashLabel);
        add(new JLabel(""));

        add(stepButton);
        add(shopButton);
        add(infoButton);
        add(backButton);
        add(legendButton);
    }

    public void handleStep() {
        
    }

    public void handleShop() {
        
    }

    public void handleInfo() {
        
    }

    public void handleBack() {
        
    }

    public void handleLegend() {
        
    }
}
