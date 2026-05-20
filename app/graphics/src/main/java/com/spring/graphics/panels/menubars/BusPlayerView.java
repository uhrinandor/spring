package com.spring.graphics.panels.menubars;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.spring.controllers.controllers.BusPlayerController;

public class BusPlayerView extends JPanel{
    BusPlayerController controller;

    public BusPlayerView(BusPlayerController controller){
        this.controller = controller;
        add(new JLabel("Bus Player View"));
    }
}
