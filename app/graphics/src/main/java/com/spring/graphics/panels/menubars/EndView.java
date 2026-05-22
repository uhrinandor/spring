package com.spring.graphics.panels.menubars;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import com.spring.controllers.controllers.EndController;
import com.spring.graphics.components.BaseButton;
import com.spring.graphics.components.LegendButton;
import com.spring.graphics.panels.map.interfaces.IMap;
import com.spring.models.field.IRField;
import com.spring.models.player .IPlayer;

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
        this.setVisible(true);
        bottom.setVisible(true);
        top.setVisible(true);
        

    }

    public void handleWinners(){
        var winners = controller.winners();
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        IPlayer spPlayer = winners.getFirst();
        IPlayer busPlayer = winners.getLast();
        String spWinner = spPlayer == null? "No winning Snowplow Player" : spPlayer.toString();
        String busWinner = busPlayer == null? "No winning Bus Player" : busPlayer.toString();

        JLabel spWinnerLabel = new JLabel(spWinner);
        JLabel busWinnerLabel = new JLabel(busWinner);

        panel.add(spWinnerLabel);
        panel.add(busWinnerLabel);

        panel.setVisible(true);
    };

    public void handleNewGame(){
        controller.newGame();
    };

    public void handleGetField(){
        map.waitForField(index -> {
            IRField field = controller.getField(index);
            if (field == null) return;

            StringBuilder sb = new StringBuilder();
            sb.append("Field #").append(field.getId()).append("\n\n");
            List<String> props = field.init();
            for (String prop : props) {
                sb.append("  - ").append(prop).append("\n");
            }

            SwingUtilities.invokeLater(() -> {
                JTextArea textArea = new JTextArea(sb.toString());
                textArea.setEditable(false);
                JScrollPane scroll = new JScrollPane(textArea);
                scroll.setPreferredSize(new java.awt.Dimension(300, 200));

                JOptionPane.showMessageDialog(
                    this,
                    scroll,
                    "Field Info",
                    JOptionPane.INFORMATION_MESSAGE
                );
            });
        });
    }

    public void handleGetSnowplows(){};

    public void handleGetSnowplowPlayer(){};

    public void handleGetBus(){};

    public void handleGetCar(){};

    public void handleGetBusPlayer(){};
}
