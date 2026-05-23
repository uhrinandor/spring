package com.spring.graphics.panels.menubars;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import com.spring.controllers.controllers.SnowPlowPlayerController;
import com.spring.graphics.components.BaseButton;
import com.spring.graphics.components.LegendButton;

public class SnowplowPlayerView extends JPanel{
    SnowPlowPlayerController controller;

    public SnowplowPlayerView(SnowPlowPlayerController controller){
        super();
        this.controller = controller;
        setLayout(new GridLayout(2, 6));
        JLabel menuLabel = new JLabel("Sp-player Menu");
        JLabel playerIdLabel = new JLabel("Player Id: ");
        JLabel cashLabel = new JLabel("Cash: ");

        BaseButton selectSpButton = new BaseButton("Select Sp");
        BaseButton buySpButton = new BaseButton("Buy Sp");
        BaseButton infoButton = new BaseButton("Info");
        BaseButton endTurnButton = new BaseButton("End Turn");
        BaseButton addCashButton = new BaseButton("Add Cash");
        LegendButton legendButton = new LegendButton();

        buySpButton.addActionListener(e -> handleBuySnowplow());
        selectSpButton.addActionListener(e -> handleSelect());
        infoButton.addActionListener(e -> handleInfo());
        endTurnButton.addActionListener(e -> handleStepAll());
        addCashButton.addActionListener(e -> handleAddMoney());

        add(menuLabel);
        add(playerIdLabel);
        add(cashLabel);
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));

        add(selectSpButton);
        add(buySpButton);
        add(infoButton);
        add(endTurnButton);
        add(addCashButton);
        add(legendButton);

    }

    public void handleSelect(){
        int length = controller.getPlayer().vehicles().size();
        Integer[] indexes = new Integer[length];
        for(int i = 0; i < length; i++){
            indexes[i] = i+1;
        }
        int selectedIndex = (int)JOptionPane.showInputDialog(
                this,
                "Válassz hókotrót",
                "Hókotró választás",
                JOptionPane.QUESTION_MESSAGE,
                null,
                indexes,
                indexes[0]
        );
        controller.select(selectedIndex-1);
    }

    public void handleBuySnowplow(){
        controller.buySnowPlow();
    }

    public void handleInfo(){
        JOptionPane.showMessageDialog(
            null,
            "id: " + controller.info().getId() + "\nmoney: " + controller.info().getPoints() + "\n vehicles: " + controller.info().vehicles(),
            "Information",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    public void handleStepAll(){
        controller.stepAll();
        //next player-re léptet
    }

    public void handleAddMoney(){
        String input = JOptionPane.showInputDialog(
            null,
            "Adj meg egy egész számot:",
            "Szám bekérése",
            JOptionPane.QUESTION_MESSAGE
        );
        try {
            int szam = Integer.parseInt(input);
            controller.getPlayer().give(szam);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                null,
                "Ez nem érvényes egész szám!",
                "Hiba",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    
    
    
}
