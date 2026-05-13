package com.spring.graphics.panels.map;

import com.spring.models.buildings.Home;
import com.spring.models.buildings.IBuildingVisitor;
import com.spring.models.buildings.Office;
import com.spring.models.buildings.Station;


import java.awt.Color;

public class BuildingColorPickerVisitor  implements IBuildingVisitor{

    Color color;
    @Override
    public void visit(Office o) {
        color=Color.ORANGE;
    }

    @Override
    public void visit(Station s) {
        color=Color.RED;
    }

    @Override
    public void visit(Home h) {
        color=Color.GREEN;
    }
    
}
