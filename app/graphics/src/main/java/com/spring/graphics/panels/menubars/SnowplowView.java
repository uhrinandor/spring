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
import com.spring.graphics.panels.map.interfaces.IMap;
import com.spring.models.field.IField;
import com.spring.models.head.Broom;
import com.spring.models.head.Brush;
import com.spring.models.head.Dragon;
import com.spring.models.head.IHead;
import com.spring.models.head.IceBreaker;
import com.spring.models.head.SaltSpreader;
import com.spring.models.head.StoneSplasher;
import com.spring.models.utils.IEntity;
import com.spring.models.utils.IObserver;

public class SnowplowView extends JPanel implements IObserver {
    SnowPlowController controller;
    IMap map;

    JLabel cashLabel;
    JLabel playerIdLabel;
    public SnowplowView(SnowPlowController controller, IMap map){
        this.controller = controller;
        this.map = map;
        setLayout(new GridLayout(2, 6));
        JLabel menuLabel = new JLabel("Sp-selected Menu");
        playerIdLabel = new JLabel("Player Id: " + controller.getSp().getId());
        
        cashLabel = new JLabel("Cash: " + controller.getSp().getPoints());
        this.controller.getSp().subscribe(this);

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

    private void handleStep() {
        List<IField> available = controller.listAvailable();
        map.waitForField(serial -> {
            controller.setNext(serial);
            if(serial != -1) JOptionPane.showMessageDialog(this, "Step selected successfully!");
        }, available);
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
        if(!controller.switchHead(head)){
            JOptionPane.showMessageDialog(
            null,
        "A fejcsere sikertelen"
            );
        }
    }

    @Override
    public void notifyChange(IEntity entity) {
        cashLabel.setText("Cash: " + controller.getSp().getPoints());
        playerIdLabel.setText("Player Id: " + controller.getSp().getId());

        revalidate();
        repaint();
    }
}
