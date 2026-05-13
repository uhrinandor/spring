package com.spring.graphics.panels.map;

import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.spring.models.field.IRoad;

public class CrossRoadView extends JPanel {
    Point location;
    IRoad road;

    public CrossRoadView(IRoad road, Point location){
        // TODO: implement crossroad view
        super();
        this.road = road;
        this.location = location;
        setBounds(location.x, location.y, 50, 50);
        add(new JLabel(road.toString()));
    }
}
