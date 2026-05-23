package com.spring.graphics.panels.map;

import javax.swing.JPanel;
import java.awt.Point;

import com.spring.models.vehicle.Vehicle;

public class VehicleView extends JPanel{
    Vehicle vehicle;
    Point point;

    public VehicleView(Vehicle v){
        vehicle = v;
    }

    public void setPoint(Point p){
        point = p;
    }

    
}
