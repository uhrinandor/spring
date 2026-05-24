package com.spring.graphics.panels.map;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import com.spring.models.vehicle.Vehicle;

public class VehicleView extends JPanel {

    private static final Color IMMOBILE_COLOR = new Color(0xEA4335);
    private static final int BORDER_WIDTH = 3;

    private final Color ringColor;
    private final Color fillColor;
    private final boolean immobile;

    public VehicleView(Vehicle v) {
        VehicleColorVisitor visitor = new VehicleColorVisitor();
        v.accept(visitor);
        this.ringColor = visitor.getRingColor();
        this.fillColor = visitor.getFillColor();
        this.immobile = visitor.isImmobile();
    }

    public void drawOn(Graphics2D g2, int cx, int cy, int size) {
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