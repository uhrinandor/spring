package com.spring.graphics.panels.map;

import javax.swing.BorderFactory;

import com.spring.models.buildings.Building;

import java.awt.Color;
public class BuildingView extends Pin{
    Building building;
    public BuildingView(Building b){
        building=b;
        BuildingColorPickerVisitor v=new BuildingColorPickerVisitor();
        b.accept(v);
        setBackground(v.getColor());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setOpaque(true);
    }
    
    
}

