package com.spring.graphics.components;

import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LegendDialog extends JDialog{
    public LegendDialog(){
        setTitle("Legend");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        List<Map> maps = mapMathcer();

        JPanel fields = new JPanel();
        JPanel pins = new JPanel();
        JPanel vehicles = new JPanel();
        JPanel connections = new JPanel();

        fields.setLayout(new BoxLayout(fields, BoxLayout.Y_AXIS));
        pins.setLayout(new BoxLayout(pins, BoxLayout.Y_AXIS));
        vehicles.setLayout(new BoxLayout(vehicles, BoxLayout.Y_AXIS));
        connections.setLayout(new BoxLayout(connections, BoxLayout.Y_AXIS));

        JLabel fieldsLabel = new JLabel("Fields: ");
        fieldsLabel.setAlignmentX(LEFT_ALIGNMENT);
        JLabel pinsLabel = new JLabel("Pins - squares on the fields:");
        pinsLabel.setAlignmentX(LEFT_ALIGNMENT);
        JLabel vehiclesLabel = new JLabel("Vehicles: ");
        vehiclesLabel.setAlignmentX(LEFT_ALIGNMENT);
        JLabel connectionsLabel = new JLabel("Connections: ");
        connectionsLabel.setAlignmentX(LEFT_ALIGNMENT);

        fields.add(fieldsLabel);
        pins.add(pinsLabel);        
        vehicles.add(vehiclesLabel);
        connections.add(connectionsLabel);

        int c = 0;

        for(Map<String,String> map : maps){
            switch(c){
                case 0: 
                    for(String key : map.keySet()){
                        fields.add(createRowWithImage(map.get(key), key));
                    }
                    c++;
                    break;
                case 1: 
                    for(String key : map.keySet()){
                        pins.add(createRowWithDescription(map.get(key), key));
                    }
                    c++;
                    break;
                case 2: 
                    for(String key : map.keySet()){
                        vehicles.add(createRowWithDescription(map.get(key), key));
                    }
                    c++;
                    break;
                case 3: 
                    for(String key : map.keySet()){
                        connections.add(createRowWithDescription(map.get(key), key));
                    }
                    c++;
                    break;
                
            }
        }

        add(fields);
        add(pins);
        add(vehicles);
        add(connections);

        pack();
        this.setVisible(false);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
    }

    private JPanel createRowWithImage(String iconPath, String labelText) {
        JPanel row = new JPanel(new GridLayout());
        JLabel label = new JLabel(labelText);
        label.setAlignmentX(LEFT_ALIGNMENT);
        row.add(label);
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
            Image scaled = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            JLabel imgLbl = new JLabel(new ImageIcon(scaled));
            imgLbl.setAlignmentX(RIGHT_ALIGNMENT);
            row.add(imgLbl);
        } catch (Exception e) {
            row.add(new JLabel("?"));
        }
        return row;
    }

    private JPanel createRowWithDescription(String desc, String label){
        JPanel row = new JPanel(new GridLayout());
        row.add(new JLabel(label));
        JLabel descr = new JLabel(desc);
        descr.setAlignmentX(RIGHT_ALIGNMENT);
        row.add(descr);
        return row;
    }

    private List<Map> mapMathcer(){
        Map<String, String> fieldMap = new LinkedHashMap<>();
        Map<String,String> pinMap = new LinkedHashMap<>();
        Map<String,String> vehiclesMap = new LinkedHashMap<>();
        Map<String,String> connectionsMap = new LinkedHashMap<>();

        fieldMap.put("Broken Ice","/fields/brokenice.jpeg");
        fieldMap.put("Compressed Snow","/fields/compressedsnow.jpeg");
        fieldMap.put("High Snow","/fields/highsnow.jpeg");
        fieldMap.put("Ice","/fields/ice.jpeg");
        fieldMap.put("Empty","/fields/layer.jpeg");
        fieldMap.put("Snow","/fields/snow.jpeg");

        pinMap.put("Station","");
        pinMap.put("Office","");
        pinMap.put("Home","");
        pinMap.put("Salt","White square");

        vehiclesMap.put("Bus","");
        vehiclesMap.put("Snowplow","");
        vehiclesMap.put("Snowplow head","Snowplow fill color");
        vehiclesMap.put("Broom","");
        vehiclesMap.put("Brush","");
        vehiclesMap.put("Ice breaker","");
        vehiclesMap.put("Drago","");
        vehiclesMap.put("Salt spreader","");

        connectionsMap.put("Forward","Black line with arrow");
        connectionsMap.put("Sideways","Red line");        

        List<Map> maps = new ArrayList<>();

        maps.add(fieldMap);
        maps.add(pinMap);
        maps.add(vehiclesMap);
        maps.add(connectionsMap);

        return maps;
    }
}
