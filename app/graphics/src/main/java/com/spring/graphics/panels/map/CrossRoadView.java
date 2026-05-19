package com.spring.graphics.panels.map;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.spring.graphics.panels.map.interfaces.RoadViewListener;
import com.spring.models.field.IRoad;

public class CrossRoadView extends JPanel{
    Point location;
    IRoad road;
    RoadViewListener listener;

    public CrossRoadView(IRoad road, Point location){
        super();
        this.road = road;
        this.location = location;
        setBounds(location.x, location.y, 50, 50);
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        setBackground(Color.LIGHT_GRAY);
        this.setLayout(new GridBagLayout());
        add(new JLabel(road.toString()));  
    }
    @Override
    public int getX(){
        return location.x;
    }
    @Override
    public int getY(){
        return location.y;
    }
    public IRoad getCrossRoad(){
        return this.road;
    }

}
