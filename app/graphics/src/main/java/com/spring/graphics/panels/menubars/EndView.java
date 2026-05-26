package com.spring.graphics.panels.menubars;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.spring.controllers.controllers.EndController;
import com.spring.graphics.components.BaseButton;
import com.spring.graphics.components.LegendButton;
import com.spring.graphics.panels.map.interfaces.IMap;
import com.spring.models.field.IRField;
import com.spring.models.player.IPlayer;
import com.spring.models.utils .IEntity;
import com.spring.models.vehicle.Vehicle;

public class EndView extends JPanel{
    EndController controller;
    IMap map;

    public EndView(EndController controller, IMap map){
        super();
        this.controller = controller;
        this.map = map;
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
        handleGetField.addActionListener(e -> map.waitForField(this::handleGetField));
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

        bottom.setBorder(new EmptyBorder(10,10,5,5));
        bottom.setOpaque(false);

        add(top);
        add(bottom);
        this.setVisible(true);
        bottom.setVisible(true);
        top.setVisible(true);
        

    }

    public void handleWinners(){
        var winners = controller.winners();
        IPlayer spPlayer = winners.get(0);
        IPlayer busPlayer = winners.get(1);
        String spWinner = spPlayer == null? "No winning Snowplow Player" : spPlayer.toString();
        String busWinner = busPlayer == null? "No winning Bus Player" : busPlayer.toString();

        JOptionPane.showMessageDialog(this, 
            "Snowplow Winner: " + spWinner + "\n Bus Winner: " + busWinner,
            "Winners",
            JOptionPane.INFORMATION_MESSAGE);
    }

    public void handleNewGame(){
        controller.newGame();
    }

    public void handleGetField(int index){
        IRField field = controller.getField(index);
        if (field == null) return;

        StringBuilder sb = new StringBuilder();
        sb.append(field.toString()).append("\n");
        List<String> props = field.init();
        for (String prop : props) {
            sb.append("  - ").append(prop).append("\n");
        }

        SwingUtilities.invokeLater(() -> {
            JTextArea textArea = new JTextArea(sb.toString());
            textArea.setEditable(false);
            JScrollPane scroll = new JScrollPane(textArea);

            JOptionPane.showMessageDialog(
                this,
                scroll,
                "Field Info",
                JOptionPane.INFORMATION_MESSAGE
            );
        });
    }

    public void handleGetSnowplows(){
        List<IEntity> items = controller.getSnowpowPlayers().stream()
        .map(sp -> (IEntity) sp.vehicles().get(0))
        .collect(java.util.stream.Collectors.toList());
        showGetX("Snowplow", items);
    }

    public void handleGetSnowplowPlayer(){
        showGetX("Snowplow Player", new java.util.ArrayList<>(controller.getSnowpowPlayers()));
    }

    public void handleGetBus(){
        List<IEntity> items = controller.getBusPlayers().stream()
        .map(bp -> (IEntity) bp.vehicles().get(0))
        .collect(java.util.stream.Collectors.toList());
        showGetX("Bus", items);
    }

    public void handleGetCar(){
        map.waitForCar(index ->{
            Vehicle car = controller.getCar(index);
            if(car == null) return;
            StringBuilder sb = new StringBuilder();
            sb.append(car.toString()).append("\n");

            List<String> props = car.init();
            for(String prop:props){
                sb.append(" - ").append(prop).append("\n");
            }

            SwingUtilities.invokeLater(()-> {
                JTextArea textArea = new JTextArea(sb.toString());
                textArea.setEditable(false);
                JScrollPane scroll = new JScrollPane(textArea);
                JOptionPane.showMessageDialog(this, scroll, "Car info", JOptionPane.INFORMATION_MESSAGE);
            });
        } );
    }

    public void handleGetBusPlayer(){
        showGetX("Bus Players", new java.util.ArrayList<>(controller.getBusPlayers()));
    }

    private void showGetX(String title, List<IEntity> items) {
        if (items == null || items.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No items found.", title, JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JComboBox<IEntity> comboBox = new JComboBox<>(items.toArray( new IEntity[0]));
        int result = JOptionPane.showConfirmDialog(
            this,
            comboBox,
            title,

            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) return;

        IEntity selected = (IEntity) comboBox.getSelectedItem();
        if (selected == null) return;

        StringBuilder sb = new StringBuilder();
        sb.append(selected).append("\n\n");
        for (String prop : selected.init()) {
            sb.append("  - ").append(prop).append("\n");
        }

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(textArea);

        JOptionPane.showMessageDialog(this, scroll, title + " Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
