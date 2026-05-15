package com.spring.graphics.panels.map;

import java.awt.Color;
import java.util.List;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Graphics2D;
import java.awt.color.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.function.Consumer;

import javax.swing.JPanel;

import com.spring.controllers.utils.GameContext;
import com.spring.graphics.enums.SelectorMode;
import com.spring.graphics.panels.map.interfaces.IMap;
import com.spring.graphics.panels.map.interfaces.RoadViewListener;
import com.spring.models.buildings.Building;
import com.spring.models.field.IRField;
import com.spring.models.field.IRoad;
import com.spring.models.field.IField;

public class MapPanel extends JPanel implements IMap, RoadViewListener {
    SelectorMode selectorMode = null;
    Consumer<Integer> callback = null;
    GameContext context;
    List<FieldView> fieldViews;
    List<CrossRoadView> crossRoadViews;

    public MapPanel(GameContext context) {
        super();
        this.context = context;
        setLayout(null);
        fieldViews = new ArrayList<FieldView>();
        crossRoadViews = new ArrayList<CrossRoadView>();
    }
    
    @Override
    public void addField(IRField field, Point location) {
        FieldView fieldView = new FieldView(field, location);
        fieldView.setListener(this);
        fieldViews.add(fieldView);
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
    public void recalculateArrows(Graphics2D g2d) {
        for(int i = 0; i < fieldViews.size(); i++){
            var from = fieldViews.get(i);
            List<IField> available = from.getField().getFront().getAvailable();
            IRField right = from.getField().getRight();
            IRField left = from.getField().getLeft();

            for(int c = 0; i+c < fieldViews.size(); c++){
                var to = fieldViews.get(c+i);
                if(to.getField() == right || to.getField() == left){
                    drawArrow(g2d, from, to, false);
                }
            }

        }
    }


    //Welp ehhez lehet kelleni fog egy paintComponets de idk hogy máshogy kéne vonalat rajzoltatni
    private void drawArrow(Graphics2D g2d, FieldView from, FieldView to, boolean isArrow) {
        if (from == null || to == null) return;

        int x1 = from.getX() + from.getWidth() / 2;
        int y1 = from.getY() + from.getHeight() / 2;
        int x2 = to.getX() + to.getWidth() / 2;
        int y2 = to.getY() + to.getHeight() / 2;

        g2d.setColor(isArrow ? Color.RED : Color.BLACK);
        g2d.drawLine(x1, y1, x2, y2);

        if (isArrow) {
            drawArrowHead(g2d, x1, y1, x2, y2);
        }
    }

    private void drawArrowHead(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        double angle = Math.atan2(y2 - y1, x2 - x1);
        int size = 12;

        AffineTransform tx = g2d.getTransform();
        g2d.translate(x2, y2);
        g2d.rotate(angle - Math.PI / 2);

        Polygon head = new Polygon();
        head.addPoint(0, 0);
        head.addPoint(-6, -size);
        head.addPoint(6, -size);

        g2d.fill(head);
        g2d.setTransform(tx);
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
