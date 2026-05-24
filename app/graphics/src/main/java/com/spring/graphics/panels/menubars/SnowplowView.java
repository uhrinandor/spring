package com.spring.graphics.panels.menubars;


import java.awt.GridLayout;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.spring.controllers.controllers.SnowPlowController;
import com.spring.graphics.components.BaseButton;
import com.spring.graphics.components.LegendButton;
import com.spring.models.field.IField;
import com.spring.models.head.Broom;
import com.spring.models.head.Brush;
import com.spring.models.head.Dragon;
import com.spring.models.head.IHead;
import com.spring.models.head.IceBreaker;
import com.spring.models.head.SaltSpreader;
import com.spring.models.head.StoneSplasher;

public class SnowplowView extends JPanel {
    SnowPlowController controller;
    public SnowplowView(SnowPlowController controller){
        this.controller = controller;
        setLayout(new GridLayout(2, 6));
        JLabel menuLabel = new JLabel("Sp-selected Menu");
        JLabel playerIdLabel = new JLabel("Player Id: " + controller.getSp().getId());
        
        JLabel cashLabel = new JLabel("Cash: " + controller.getSp().getPoints());

        JButton stepButton = new BaseButton("Step");
        JButton shopButton = new BaseButton("Shop");
        JButton infoButton = new BaseButton("Info");
        JButton backButton = new BaseButton("Back");
        JButton switchHeadButton = new BaseButton("Switch Head");
        LegendButton legendButton = new LegendButton();

        infoButton.addActionListener(e -> handleInfo());
        switchHeadButton.addActionListener(e -> handleSwitchHead());
        backButton.addActionListener(e -> handleBack());
        shopButton.addActionListener(e -> handleShop());
        stepButton.addActionListener(e -> handleStep());

        add(menuLabel);
        add(playerIdLabel);
        add(new JLabel(""));
        add(cashLabel);
        add(switchHeadButton);
        add(new JLabel(""));

        add(stepButton);
        add(shopButton);
        add(infoButton);
        add(backButton);
        add(legendButton);
    }

    public void handleStep() {
        int size = controller.listAvailable().size();
        IField[] fields = new IField[size];
        for(int i = 0; i < size; i++){
            fields[i] = controller.listAvailable().get(i);
        }
        IField chosen = (IField)JOptionPane.showInputDialog(
                this,
                "Válassz tárgyat",
                "Tárgy választás",
                JOptionPane.QUESTION_MESSAGE,
                null,
                fields,
                fields[0]
        );
        for(int i = 0; i < size; i++){
            if(chosen == fields[i]){
                controller.setNext(i);
            }
        }
    }

    public void handleShop() {
        String[] options = {"DRAGON", "BROOM", "BRUSH", "ICEBREAKER", "STONESPLASHER", "SALTSPREADER", "SALT", "STONE", "BIOKEROSENE"};
        String choosen = (String)JOptionPane.showInputDialog(
                this,
                "Válassz tárgyat",
                "Tárgy választás",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        int index;
        List<String> optionsList = Arrays.asList(options);
        index = optionsList.indexOf(choosen);
        controller.buy(index);
    }

    public void handleInfo() {
        JOptionPane.showMessageDialog(
            null,
            "id: " + controller.info().getId() + "\nhead: " + controller.info().getHead() + "\ninventory: " + controller.info().getInventory().init(),
            "Information",
            JOptionPane.INFORMATION_MESSAGE
        );
        
    }

    public void handleBack() {
        controller.back();
    }

    public void handleSwitchHead(){
        String[] options = {"DRAGON", "STONESPLASHER", "BRUSH", "BROOM", "SALTSPREADER", "ICEBREAKER"};
        String choosen = (String)JOptionPane.showInputDialog(
                this,
                "Válassz hókotrót",
                "Hókotró választás",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        List<IHead> heads = List.of(new Dragon(), new StoneSplasher(), new Brush(), new Broom(), new SaltSpreader(), new IceBreaker());
        List<String> optionsList = Arrays.asList(options);
        
        IHead head = heads.get(optionsList.indexOf(choosen));
        /*switch (choosen) {
            case "DRAGON":
                head = new Dragon();
                break;
            case "STONESPLASHER":
                head = new StoneSplasher();
                break;
            case "BRUSH":
                head = new Brush();
                break;
            case "BROOM":
                head = new Broom();
                break;
            case "SALTSPREADER":
                head = new SaltSpreader();
                break;
            case "ICEBREAKER":
                head = new IceBreaker();
                break;
            default:
                head = new Brush();
                break;
        }*/
        if(!controller.switchHead(head)){
            JOptionPane.showMessageDialog(
            null,
        "A fejcsere sikertelen"
            );
        }
    }
}
