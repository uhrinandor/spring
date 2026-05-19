package com.spring.graphics.components;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Legend extends JFrame{
    public void Legend(){
        add(new JLabel("Legend"));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
       
        JPanel fields = new JPanel();
        JPanel vehicles = new JPanel();
        JPanel connections = new JPanel();

        fields.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        vehicles.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        connections.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        fields.add(new JLabel("Fields: "));        
        vehicles.add(new JLabel("Vehicles: "));
        connections.add(new JLabel("Connections: "));

        //TODO: nevek és képek elhelyezése


        add(fields);
        add(vehicles);
        add(connections);

        fields.setVisible(true);
        vehicles.setVisible(true);
        connections.setVisible(true);
    }
}
