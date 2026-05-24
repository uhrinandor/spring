package com.spring.graphics.panels.map;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import com.spring.models.utils.IEntity;
import com.spring.models.utils.IObserver;
import com.spring.models.vehicle.Vehicle;

public class VehicleView extends JPanel implements IObserver {

    private static final Color IMMOBILE_COLOR = new Color(0xEA4335);
    private static final int BORDER_WIDTH = 3;

    private Color ringColor = Color.GRAY;
    private Color fillColor = Color.WHITE;
    private boolean immobile = false;
    private boolean active = false;

    // CHANGE: keep reference to vehicle for unsubscribe on change
    private Vehicle vehicle;

    public VehicleView() {
        // no-arg constructor for FieldView to create a fixed instance
    }

    /**
     * Sets (or replaces) the vehicle this view observes.
     * Unsubscribes from the old vehicle, subscribes to the new one.
     */
    public void setVehicle(Vehicle v) {
        if (vehicle != null)
            vehicle.unsubscribe(this);
        vehicle = v;
        if (vehicle != null) {
            vehicle.subscribe(this);
            refresh();
            active = true;
        } else {
            active = false;
        }
    }

    private void refresh() {
        if (vehicle == null)
            return;
        VehicleColorVisitor visitor = new VehicleColorVisitor();
        vehicle.accept(visitor);
        ringColor = visitor.getRingColor();
        fillColor = visitor.getFillColor();
        immobile = visitor.isImmobile();
    }

    @Override
    public void notifyChange(IEntity entity) {
        refresh();
    }

    public void drawOn(Graphics2D g2, int cx, int cy, int size) {
        if (!active)
            return;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(fillColor);
        g2.fillOval(cx, cy, size, size);

        g2.setColor(ringColor);
        g2.setStroke(new BasicStroke(BORDER_WIDTH));
        g2.drawOval(cx + BORDER_WIDTH / 2, cy + BORDER_WIDTH / 2,
                size - BORDER_WIDTH, size - BORDER_WIDTH);

        if (immobile) {
            g2.setColor(IMMOBILE_COLOR);
            g2.setStroke(new BasicStroke(2));
            int m = 4;
            g2.drawLine(cx + m, cy + m, cx + size - m, cy + size - m);
            g2.drawLine(cx + size - m, cy + m, cx + m, cy + size - m);
        }
    }
}