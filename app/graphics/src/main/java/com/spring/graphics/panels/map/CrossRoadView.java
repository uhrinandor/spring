package com.spring.graphics.panels.map;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.spring.graphics.panels.map.interfaces.RoadViewListener;
import com.spring.models.field.IRoad;
import com.spring.models.utils.IEntity;
import com.spring.models.utils.IObserver;

public class CrossRoadView extends JPanel implements IObserver{
    Point location;
    IRoad road;
    RoadViewListener listener;

    public CrossRoadView(IRoad road, Point location){
        super();
        this.road = road;
        this.location = location;
        road.subscribe(this);
        setBounds(location.x, location.y, 50, 50);
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        setBackground(Color.LIGHT_GRAY);
        this.setLayout(new GridBagLayout());
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
    @Override
    public void notifyChange(IEntity entity) {
        repaint();
    }

}
