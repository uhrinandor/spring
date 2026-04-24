package com.spring.prototype.menuitems.busplayer;

import java.util.List;

import com.spring.controllers.controllers.BusPlayerController;
import com.spring.models.field.IField;
import com.spring.models.utils.Tracer;
import com.spring.prototype.menuitems.MenuItem;

/**
 * Kilistázza a buszos játékos legaális lépéseit
 */
public class BusListAvailable extends MenuItem{
    BusPlayerController controller;
    
    public BusListAvailable(BusPlayerController controller) {
        super("LIST-AVAILABLE");
        this.controller = controller;
    }

    @Override
    public void execute() {
        List<IField> fields = controller.listAvailable();
        Tracer tracer = Tracer.getInstance();
        for(int i = 0; i < fields.size(); i++) {
            tracer.info(String.format("[%d]", i));
            tracer.newObject(fields.get(i));
        }
    }

    @Override
    public boolean parse(String input) {
        return input.trim().equalsIgnoreCase("LIST-AVAILABLE");
    }
}
