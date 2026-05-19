package com.spring.graphics.panels.menubars;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridLayout;

import com.spring.controllers.controllers.SnowPlowPlayerController;
import com.spring.graphics.components.BaseButton;

public class SnowplowPlayerView extends JPanel{
    SnowPlowPlayerController controller;

    public SnowplowPlayerView(SnowPlowPlayerController controller){
        super();
        this.controller = controller;
        setLayout(new GridLayout(2, 6));
        JLabel menuLabel = new JLabel("Sp-player Menu");
        JLabel playerIdLabel = new JLabel("Player Id: ");
        JLabel cashLabel = new JLabel("Cash: ");

        JButton selectSpButton = new BaseButton("Select Sp");
        JButton buySpButton = new BaseButton("Buy Sp");
        JButton infoButton = new BaseButton("Info");
        JButton endTurnButton = new BaseButton("End Turn");
        JButton addCashButton = new BaseButton("Add Cash");
        JButton legendButton = new BaseButton("Legend");

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

    public void handleSelect(int sp1){
        if(controller.getPlayer().vehicles().size() < sp1){
            controller.select(sp1);
        }
    }

    public void handleBuySnowplow(){
        controller.buySnowPlow();
    }

    public void handleInfo(){
        JOptionPane.showMessageDialog(
            null,
            controller.getPlayer().init(),
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
