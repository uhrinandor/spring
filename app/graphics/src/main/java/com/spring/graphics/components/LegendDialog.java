package com.spring.graphics.components;

import java.awt.FlowLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
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
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        List<Map> maps = mapMathcer();

        JPanel fields = new JPanel();
        JPanel pins = new JPanel();
        JPanel vehicles = new JPanel();
        JPanel connections = new JPanel();

        fields.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        pins.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        vehicles.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        connections.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        fields.add(new JLabel("Fields: "));
        pins.add(new JLabel("Pins - squares on the fields:"));        
        vehicles.add(new JLabel("Vehicles: "));
        connections.add(new JLabel("Connections: "));

        int c = 0;

        for(Map<String,String> map : maps){
            if(c<1){
                for(String key : map.keySet()){
                    createRowWithImage(map.get(key), key);
                }
                c++;
            }else{
                for(String key : map.keySet()){
                    createRowWithDescription(map.get(key), key);
                }
            }
        }

        add(fields);
        add(vehicles);
        add(connections);

        fields.setVisible(true);
        vehicles.setVisible(true);
        connections.setVisible(true);
    }

    private JPanel createRowWithImage(String iconPath, String labelText) {
    JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
    try {
        ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
        Image scaled = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        row.add(new JLabel(new ImageIcon(scaled)));
    } catch (Exception e) {
        row.add(new JLabel("?"));
    }
    row.add(new JLabel(labelText));
    return row;
    }

    private JPanel createRowWithDescription(String desc, String label){
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row.add(new JLabel(label));
        row.add(new JLabel(desc));
        return row;
    }

    private List<Map> mapMathcer(){
        Map<String, String> fieldMap = new HashMap<>();
        Map<String,String> pinMap = new HashMap<>();
        Map<String,String> vehiclesMap = new HashMap<>();
        Map<String,String> connectionsMap = new HashMap<>();

        fieldMap.put("Broken Ice","/fields/brokenice.jpeg");
        fieldMap.put("Compressed Snow","/fields/compressedsnow.jpeg");
        fieldMap.put("High Snow","/fields/highsnow.jpeg");
        fieldMap.put("Ice","/fields/ice.jpeg");
        fieldMap.put("Empty","/fields/layer.jpeg");
        fieldMap.put("Snow","/fields/snow,jpeg");

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
