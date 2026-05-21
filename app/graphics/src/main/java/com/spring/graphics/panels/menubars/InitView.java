package com.spring.graphics.panels.menubars;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.spring.controllers.controllers.InitController;
import com.spring.graphics.components.BaseButton;
import com.spring.graphics.components.LegendButton;
import com.spring.graphics.panels.map.interfaces.IMap;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

public class InitView extends JPanel {
    IMap map;
    InitController controller;

    public InitView(IMap map, InitController controller){
        super();
        this.map = map;
        this.controller = controller;
        add(new JLabel("INIT VIEW"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel top = new JPanel();
        top.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0));
        top.setOpaque(false);

        BaseButton handleStart = new BaseButton("Start");
        BaseButton handleSetRound = new BaseButton("Set rounds");
        BaseButton handleAddSnowplowPlayer = new BaseButton("Add Snowplow Player");
        BaseButton handleAddBusPlayer = new BaseButton("Add Bus Player");

        handleStart.addActionListener(e -> handleStart());
        handleSetRound.addActionListener(e -> handleSetRound());
        handleAddSnowplowPlayer.addActionListener(e -> handleAddSnowplowPlayer());
        handleAddBusPlayer.addActionListener(e -> handleAddBusPlayer());

        top.add(handleStart);
        top.add(handleSetRound);
        top.add(handleAddSnowplowPlayer);
        top.add(handleAddBusPlayer);

        JPanel bottom = new JPanel();
        bottom.setLayout(new BorderLayout());
        bottom.setOpaque(false);

        LegendButton legend = new LegendButton();
        bottom.add(legend, BorderLayout.EAST);

        add(top);
        add(bottom);
        bottom.setVisible(true);
        top.setVisible(true);
    }

    public void handleAddSnowplowPlayer(){
        controller.addSnowplowPlayer();
    }

    public void handleAddBusPlayer(){
        controller.addBusPlayer();
    }

    public void handleSetRound(){
        String input = JOptionPane.showInputDialog(this, "Körök száma:");

        if (input == null) {
            return;
        }

        try {
            int rounds = Integer.parseInt(input);

            if (rounds < 1) {
                JOptionPane.showMessageDialog(this, "Legalább 1 kört kell megadni!");
                return;
            }

            controller.rounds(rounds);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Számot kell megadni!");
        }
    }

    public void handleStart(){
        controller.start();
    }
}
