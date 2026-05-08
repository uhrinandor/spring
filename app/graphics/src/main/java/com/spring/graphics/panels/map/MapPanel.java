package com.spring.graphics.panels.map;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.spring.graphics.enums.SelectorMode;
import com.spring.models.field.IRField;

public class MapPanel extends JPanel implements IMap {
    SelectorMode selectorMode = SelectorMode.POINT;

    Consumer<Point> pointCallback;

    public MapPanel() {
        super();
        setLayout(null);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
             handleClick(e.getPoint());
            }
        });
        setBorder(BorderFactory.createLineBorder(Color.RED));
    }

    public void handleClick(Point point){
        if(selectorMode == SelectorMode.POINT && pointCallback != null) pointCallback.accept(point);
    }

    @Override
    public void waitForPoint(Consumer<Point> callback) {
        selectorMode = SelectorMode.POINT;
        pointCallback = callback;
    }

    @Override
    public void changeSelectorMode(SelectorMode mode) {
        selectorMode = mode;
    }

    @Override
    public void addField(IRField field, Point location) {
        FieldView fieldView = new FieldView(field, location);
        add(fieldView);
        revalidate();
        repaint();
    }
}
