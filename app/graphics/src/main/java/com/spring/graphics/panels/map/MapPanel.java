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
    List<IField> availablePicks;
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
        crossRoadViews.add(crossRoadView);
        add(crossRoadView);
        revalidate();
        repaint();
    }

    @Override
    public void addBuilding(Building building) {
        IRField field = building.getField();

        for (FieldView fieldView : fieldViews) {
            if (fieldView.getField() == field) {
                Pin buildingPin = new BuildingView(building);
                fieldView.addPin(buildingPin);
                break;
            }
        }
    }

    /*
     * A függvény a mezők közötti nyilak összekötésére szolgál.
     * Ehhez először végig megy az összes Mezőhöz tartozó view-on,
     * kinyeri a mezőt a viewból és hogy mely másik mezőkkel kell összekötni
     * majd amikor talál egy mezőt, amihez hozzá kell kötni a jelenleg külső
     * loopban szereplőt olyankor ezekkel attribútomként meghívja az összeköttetés
     * rajzoló függvényt.
     * 
     * A külső loop ehhez ketté van bontva, először a FIELD mezőkhöz tartozó
     * összeköttetéseket rajzolja ki, majd a CROSSROAD mezőkhöz tartozókat.
     */

    @Override
    public void recalculateArrows(Graphics2D g2d) {
        for (FieldView from : fieldViews) {
            boolean isCrossRoad = false;
            IRoad front = from.getField().getFront();
            List<IField> available = new ArrayList<IField>(front.getAvailable());
            IRField right = from.getField().getRight();
            for (CrossRoadView to : crossRoadViews) {
                if (to.getCrossRoad() == front) {
                    isCrossRoad = true;
                    drawConnection(g2d, from, to, false);
                    break;
                }
            }
            for (FieldView to : fieldViews) {
                if (right == null && available.isEmpty())
                    break;

                if (to.getField() == right) {
                    drawConnection(g2d, from, to, true);
                    right = null;
                } else if (available.contains(to.getField()) && !isCrossRoad) {
                    drawConnection(g2d, from, to, false);
                    available.remove(to.getField());
                }
            }
        }
        for (CrossRoadView from : crossRoadViews) {
            List<IField> available = new ArrayList<IField>(from.getCrossRoad().getAvailable());
            for (FieldView to : fieldViews) {
                if (available.isEmpty())
                    break;
                if (available.contains(to.getField())) {
                    drawConnection(g2d, from, to, false);
                    available.remove(to.getField());
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        recalculateArrows(g2d);
    }

    public void refreshVehicles() {
        for (FieldView fieldView : fieldViews) {
            fieldView.notifyChange(null);
        }
    }

    // Welp ehhez lehet kelleni fog egy paintComponets de idk hogy máshogy kéne
    // vonalat rajzoltatni
    private void drawConnection(Graphics2D g2d, JPanel from, JPanel to, boolean isSide) {
        if (from == null || to == null)
            return;

        int x1 = from.getX() + from.getWidth() / 2;
        int y1 = from.getY() + from.getHeight() / 2;
        int x2 = to.getX() + to.getWidth() / 2;
        int y2 = to.getY() + to.getHeight() / 2;

        g2d.setColor(isSide ? Color.RED : Color.BLACK);
        g2d.drawLine(x1, y1, x2, y2);

        int mx = (x1 + x2) / 2;
        int my = (y1 + y2) / 2;

        double angle = Math.atan2(y2 - y1, x2 - x1);
        if (!isSide) {
            drawArrowHead(g2d, mx, my, angle);
        }

    }

    private void drawArrowHead(Graphics2D g2d, int x, int y, double angle) {
        int size = 8;

        int tipX = x + (int) (Math.cos(angle) * size);
        int tipY = y + (int) (Math.sin(angle) * size);

        int leftX = x + (int) (Math.cos(angle + Math.toRadians(145)) * size);
        int leftY = y + (int) (Math.sin(angle + Math.toRadians(145)) * size);
        int rightX = x + (int) (Math.cos(angle - Math.toRadians(145)) * size);
        int rightY = y + (int) (Math.sin(angle - Math.toRadians(145)) * size);

        int[] xPoints = { tipX, leftX, rightX };
        int[] yPoints = { tipY, leftY, rightY };

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
        if (selectorMode == SelectorMode.FIELD && callback != null) {
            callback.accept(context.getFields().indexOf(field));
            selectorMode = null;
            callback = null;
        } else if (selectorMode == SelectorMode.CAR && callback != null) {
            callback.accept(context.getCars().indexOf(field.getVehicle()));
            selectorMode = null;
            callback = null;
        } else if (selectorMode == SelectorMode.FROM_FIELD_LIST && callback != null && availablePicks != null
                && availablePicks.contains(field)) {
            callback.accept(availablePicks.indexOf(field));
            selectorMode = null;
            callback = null;
        }
    }

    @Override
    public void waitForField(Consumer<Integer> callback, List<IField> fields) {
        this.selectorMode = SelectorMode.FROM_FIELD_LIST;
        this.availablePicks = fields;
        this.callback = callback;
    }
}
