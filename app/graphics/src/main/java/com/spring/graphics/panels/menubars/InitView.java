package com.spring.graphics.panels.menubars;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.spring.controllers.controllers.InitController;
import com.spring.graphics.panels.map.IMap;
import com.spring.models.field.IRField;
import com.spring.models.layer.Layer;

public class InitView extends JPanel {
    IMap map;
    InitController controller;

    public InitView(IMap map, InitController controller){
        super();
        this.map = map;
        this.controller = controller;

        JButton addFieldBtn = new JButton("Add field");
        addFieldBtn.addActionListener(e -> map.waitForPoint(this::handleAddField));

        add(addFieldBtn);
    }

    void handleAddField(Point point){
        boolean isUnderground = false;
        Layer layer = new Layer();
        IRField field = controller.addField(layer, isUnderground);
        map.addField(field, point);
    }
    
}
