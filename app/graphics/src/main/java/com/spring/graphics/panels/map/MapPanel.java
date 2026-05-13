package com.spring.graphics.panels.map;

import java.awt.Point;
import java.util.function.Consumer;

import javax.swing.JPanel;

import com.spring.controllers.utils.GameContext;
import com.spring.graphics.enums.SelectorMode;
import com.spring.graphics.panels.map.interfaces.IMap;
import com.spring.graphics.panels.map.interfaces.RoadViewListener;
import com.spring.models.buildings.Building;
import com.spring.models.field.IRField;
import com.spring.models.field.IRoad;

public class MapPanel extends JPanel implements IMap, RoadViewListener {
    SelectorMode selectorMode = null;
    Consumer<Integer> callback = null;
    GameContext context;

    public MapPanel(GameContext context) {
        super();
        this.context = context;
        setLayout(null);
    }
    
    @Override
    public void addField(IRField field, Point location) {
        FieldView fieldView = new FieldView(field, location);
        fieldView.setListener(this);
        add(fieldView);
        revalidate();
        repaint();
    }

    @Override
    public void addCrossRoad(IRoad field, Point location) { 
        CrossRoadView crossRoadView = new CrossRoadView(field, location);
        add(crossRoadView);
        revalidate();
        repaint();
    }

    @Override
    public void addBuilding(Building building) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addBuilding'");
    }

    @Override
    public void recalculateArrows() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'renderArrows'");
    }

    @Override
    public void waitForField(Consumer<Integer> callback) {
        selectorMode = SelectorMode.FIELD;
        this.callback = callback;
    }

    @Override
    public void waitForCar(Consumer<Integer> callback) {
        selectorMode = SelectorMode.CAR;
        this.callback = callback;
    }

    @Override
    public void onFieldClicked(IRField field) {
        System.out.println("Field clicked: " + field);
        if(selectorMode == SelectorMode.FIELD && callback != null){
            callback.accept(context.getFields().indexOf(field));
            selectorMode = null;
            callback = null;
        }else if(selectorMode == SelectorMode.CAR && callback != null){
            callback.accept(context.getCars().indexOf(field.getVehicle()));
            selectorMode = null;
            callback = null;
        }
    }
}
