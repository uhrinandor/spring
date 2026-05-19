package com.spring.graphics.panels.map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JPanel;

import com.spring.controllers.utils.GameContext;
import com.spring.graphics.enums.SelectorMode;
import com.spring.graphics.panels.map.interfaces.IMap;
import com.spring.graphics.panels.map.interfaces.RoadViewListener;
import com.spring.models.buildings.Building;
import com.spring.models.field.IField;
import com.spring.models.field.IRField;
import com.spring.models.field.IRoad;

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
        for(FieldView from : fieldViews){
            List<IField> available = from.getField().getFront().getAvailable();
            IRField right = from.getField().getRight();
            IRField left = from.getField().getLeft();

            for(FieldView to : fieldViews){
                if(to.getField() == right || to.getField() == left){
                    drawConnection(g2d, from, to, true);
                }else if(available.contains(to.getField())){
                    drawConnection(g2d, from, to , false);
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        recalculateArrows(g2d);
    }


    //Welp ehhez lehet kelleni fog egy paintComponets de idk hogy máshogy kéne vonalat rajzoltatni
    private void drawConnection(Graphics2D g2d, FieldView from, FieldView to, boolean isSide) {
        if (from == null || to == null) return;

        int x1 = from.getX() + from.getWidth() / 2;
        int y1 = from.getY() + from.getHeight() / 2;
        int x2 = to.getX() + to.getWidth() / 2;
        int y2 = to.getY() + to.getHeight() / 2;

        g2d.setColor(isSide ? Color.RED : Color.BLACK);
        g2d.drawLine(x1, y1, x2, y2);

        int mx = (x1 + x2) / 2;
        int my = (y1 + y2) / 2;

        double angle = Math.atan2(y2 - y1, x2 - x1);
        drawArrowHead(g2d, mx, my, angle);

    }

    private void drawArrowHead(Graphics2D g2d, int x, int y, double angle) {
    int size = 10;
    double left  = angle + Math.toRadians(150);
    double right = angle - Math.toRadians(150);

    int[] xPoints = {
        x + (int)(Math.cos(angle) * size),
        x + (int)(Math.cos(left)  * size),
        x + (int)(Math.cos(right) * size)
    };
    int[] yPoints = {
        y + (int)(Math.sin(angle) * size),
        y + (int)(Math.sin(left)  * size),
        y + (int)(Math.sin(right) * size)
    };

    g2d.fillPolygon(xPoints, yPoints, 3);
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
