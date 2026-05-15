package com.spring.graphics.panels.menubars;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.spring.controllers.controllers.EndController;
import com.spring.graphics.components.BaseButton;
import com.spring.graphics.components.LegendButton;

public class EndView extends JPanel{
    EndController controller;

    public EndView(EndController controller){
        super();
        add(new JLabel("END VIEW"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel top = new JPanel();
        top.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0));
        top.setOpaque(false);

        JLabel topLabel = new JLabel("get:");

        BaseButton handleWinners = new BaseButton("Winners");        
        BaseButton handleGetField = new BaseButton("Field");
        BaseButton handleGetSnowplows = new BaseButton("Snowplows");
        BaseButton handleGetSnowplowPlayer = new BaseButton("SP player");
        BaseButton handleGetBus = new BaseButton("Bus");
        BaseButton handleGetCar = new BaseButton("Car");
        BaseButton handleGetBusPlayer = new BaseButton("Bus Player");    
        
        handleWinners.addActionListener(e -> handleWinners());
        
        handleGetField.addActionListener(e -> handleGetField());
        handleGetSnowplows.addActionListener(e -> handleGetSnowplows());
        handleGetSnowplowPlayer.addActionListener(e -> handleGetSnowplowPlayer());
        handleGetBus.addActionListener(e -> handleGetBus());
        handleGetCar.addActionListener(e -> handleGetCar());
        handleGetBusPlayer.addActionListener(e -> handleGetBusPlayer());

        top.add(handleWinners);
        top.add(topLabel);
        top.add(handleGetField);
        top.add(handleGetSnowplows);
        top.add(handleGetSnowplowPlayer);
        top.add(handleGetBus);
        top.add(handleGetBusPlayer);
        top.add(handleGetCar);

        JPanel bottom = new JPanel();

        bottom.setLayout(new BorderLayout());

        BaseButton handleNewGame = new BaseButton("New Game");
        LegendButton legend = new LegendButton();

        handleNewGame.addActionListener(e->handleNewGame());

        bottom.add(handleNewGame, BorderLayout.WEST);
        bottom.add(legend, BorderLayout.EAST);

        add(top);
        add(bottom);
        bottom.setVisible(true);
        top.setVisible(true);
        

    }

    public void handleWinners(){};

    public void handleNewGame(){};

    public void handleGetField(){};

    public void handleGetSnowplows(){};

    public void handleGetSnowplowPlayer(){};

    public void handleGetBus(){};

    public void handleGetCar(){};

    public void handleGetBusPlayer(){};
}
