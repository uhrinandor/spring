package com.spring.graphics.panels.map;

import com.spring.models.buildings.Building;

public class BuildingView extends Pin{
    Building building;
    public BuildingView(Building b){
        building=b;
        BuildingColorPickerVisitor v=new BuildingColorPickerVisitor();
        b.accept(v);
    }
    
}

