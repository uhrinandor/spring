package com.spring.graphics.components;

import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Legend extends JFrame{
    Map<String,String> map = new HashMap<String,String>();

    public Legend(){
        setTitle("Legend");
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
       

        JPanel fields = new JPanel();
        JPanel vehicles = new JPanel();
        JPanel connections = new JPanel();

        fields.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        vehicles.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        connections.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        fields.add(new JLabel("Fields: "));        
        vehicles.add(new JLabel("Vehicles: "));
        connections.add(new JLabel("Connections: "));

        


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

    private void mapPicToName(){
        Map.put("Broken ice","brokenice");

    }


}
