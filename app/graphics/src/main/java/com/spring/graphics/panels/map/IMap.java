package com.spring.graphics.panels.map;

import java.awt.Point;
import java.util.function.Consumer;

import com.spring.graphics.enums.SelectorMode;
import com.spring.models.buildings.Building;
import com.spring.models.field.IRField;
import com.spring.models.field.IRoad;

public interface IMap {
    public void waitForPoint(Consumer<Point> callback);
    public void changeSelectorMode(SelectorMode mode);
    public void addField(IRField field, Point location);
    public void addCrossRoad(IRoad field, Point location);
    public void addBuilding(Building building);
    public void renderArrows();
}
