package com.spring.prototype.menuitems.snowplow;

import java.util.List;

import com.spring.controllers.controllers.SnowPlowController;
import com.spring.models.field.IField;
import com.spring.models.utils.Tracer;
import com.spring.prototype.menuitems.MenuItem;

/**
 * Kilistázza a hókotró legaális lépéseit
 */
public class SnowPlowListAvailable extends MenuItem {
    private SnowPlowController controller;

    public SnowPlowListAvailable(SnowPlowController controller) {
        super("LIST-AVAILABLE");
        this.controller = controller;
    }

    @Override
    public void execute() {
        List<IField> fields = controller.listAvailable();
        Tracer tracer = Tracer.getInstance();
        for(int i = 0; i < fields.size(); i++) {
            tracer.info(String.format("%d", i));
            tracer.newObject(fields.get(i));
        }
    }

    @Override
    public boolean parse(String input) {
        return input.trim().equalsIgnoreCase("LIST-AVAILABLE");
    }
}