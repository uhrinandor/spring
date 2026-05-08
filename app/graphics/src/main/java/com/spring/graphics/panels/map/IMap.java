package com.spring.graphics.panels.map;

import java.awt.Point;
import java.util.function.Consumer;

import com.spring.graphics.enums.SelectorMode;
import com.spring.models.field.IRField;

public interface IMap {
    public void waitForPoint(Consumer<Point> callback);
    public void changeSelectorMode(SelectorMode mode);
    public void addField(IRField field, Point location);
}
