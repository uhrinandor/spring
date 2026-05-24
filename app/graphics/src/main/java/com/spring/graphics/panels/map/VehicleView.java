package com.spring.graphics.panels.map;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import com.spring.models.vehicle.Bus;
import com.spring.models.vehicle.Car;
import com.spring.models.vehicle.Snowplow;
import com.spring.models.vehicle.Vehicle;

public class VehicleView extends JPanel {

    private static final Color BUS_COLOR = new Color(0x34A853);
    private static final Color CAR_COLOR = new Color(0xFBBC04);
    private static final Color SNOWPLOW_COLOR = new Color(0x4285F4);
    private static final Color IMMOBILE_COLOR = new Color(0xEA4335); // red X

    private static final Color HEAD_NONE = Color.WHITE;
    private static final Color HEAD_BROOM = new Color(0x4285F4);
    private static final Color HEAD_BRUSH = new Color(0x34A853);
    private static final Color HEAD_ICEBREAKER = new Color(0xFBBC04);
    private static final Color HEAD_DRAGON = new Color(0xEA4335);
    private static final Color HEAD_SALTSPREADER = Color.WHITE;

    private static final int SIZE = 12;
    private static final int BORDER_WIDTH = 3;

    private final Color ringColor;
    private final Color fillColor;
    private final boolean immobile;

    public VehicleView(Bus bus) {
        this(BUS_COLOR, Color.WHITE, bus.isImmobile());
    }

    public VehicleView(Car car) {
        this(CAR_COLOR, Color.WHITE, car.isImmobile());
    }

    public VehicleView(Snowplow sp) {
        this(SNOWPLOW_COLOR, resolveHeadColor(sp), false);
    }

    public VehicleView(Vehicle v) {
        this(resolveRingColor(v), resolveFillColor(v), resolveImmobile(v));
    }

    private VehicleView(Color ring, Color fill, boolean immobile) {
        this.ringColor = ring;
        this.fillColor = fill;
        this.immobile = immobile;
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

    private static boolean resolveImmobile(Vehicle v) {
        return v instanceof Bus && ((Bus) v).isImmobile();
    }

    private static Color resolveRingColor(Vehicle v) {
        if (v instanceof Bus)
            return BUS_COLOR;
        if (v instanceof Car)
            return CAR_COLOR;
        if (v instanceof Snowplow)
            return SNOWPLOW_COLOR;
        return Color.GRAY;
    }

    private static Color resolveFillColor(Vehicle v) {
        if (v instanceof Snowplow)
            return resolveHeadColor((Snowplow) v);
        return Color.WHITE;
    }

    private static Color resolveHeadColor(Snowplow sp) {
        if (sp.getHead() == null)
            return HEAD_NONE;
        return switch (sp.getHead().getClass().getSimpleName()) {
            case "Broom" -> HEAD_BROOM;
            case "Brush" -> HEAD_BRUSH;
            case "IceBreaker" -> HEAD_ICEBREAKER;
            case "Dragon" -> HEAD_DRAGON;
            case "SaltSpreader" -> HEAD_SALTSPREADER;
            default -> HEAD_NONE;
        };
    }
}