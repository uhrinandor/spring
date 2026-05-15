package com.spring.graphics.panels.map.interfaces;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.function.Consumer;

import com.spring.models.buildings.Building;
import com.spring.models.field.IRField;
import com.spring.models.field.IRoad;

public interface IMap {
    public void waitForField(Consumer<Integer> callback);
    public void waitForCar(Consumer<Integer> callback);
    
    public void addField(IRField field, Point location);
    public void addCrossRoad(IRoad field, Point location);
    public void addBuilding(Building building);
    public void recalculateArrows(Graphics2D g2d);
}
