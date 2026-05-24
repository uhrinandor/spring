package com.spring.graphics.panels.map;

import java.awt.Color;

import com.spring.models.utils.Entity;
import com.spring.models.vehicle.Bus;
import com.spring.models.vehicle.Car;
import com.spring.models.vehicle.IVehicleVisitor;
import com.spring.models.vehicle.Snowplow;

public class VehicleColorVisitor extends Entity implements IVehicleVisitor {

    private static final Color BUS_COLOR = new Color(0x34A853);
    private static final Color CAR_COLOR = new Color(0xFBBC04);
    private static final Color SNOWPLOW_COLOR = new Color(0x4285F4);
    private static final Color HEAD_BROOM = new Color(0x4285F4);
    private static final Color HEAD_BRUSH = new Color(0x34A853);
    private static final Color HEAD_ICEBREAKER = new Color(0xFBBC04);
    private static final Color HEAD_DRAGON = new Color(0xEA4335);

    private Color ringColor = Color.GRAY;
    private Color fillColor = Color.WHITE;
    private boolean immobile = false;

    @Override
    public java.util.List<String> init() {
        return java.util.List.of();
    }

    public Color getRingColor() {
        return ringColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public boolean isImmobile() {
        return immobile;
    }

    @Override
    public void visit(Bus b) {
        ringColor = BUS_COLOR;
        fillColor = Color.WHITE;
        immobile = b.isImmobile();
    }

    @Override
    public void visit(Car c) {
        ringColor = CAR_COLOR;
        fillColor = Color.WHITE;
        immobile = c.isImmobile();
    }

    @Override
    public void visit(Snowplow s) {
        ringColor = SNOWPLOW_COLOR;
        immobile = false;

        if (s.getHead() == null) {
            fillColor = Color.WHITE;
            return;
        }

        fillColor = switch (s.getHead().getClass().getSimpleName()) {
            case "Broom" -> HEAD_BROOM;
            case "Brush" -> HEAD_BRUSH;
            case "IceBreaker" -> HEAD_ICEBREAKER;
            case "Dragon" -> HEAD_DRAGON;
            default -> Color.WHITE;
        };
    }
}
