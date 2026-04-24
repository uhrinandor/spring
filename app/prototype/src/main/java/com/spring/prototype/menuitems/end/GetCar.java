package com.spring.prototype.menuitems.end;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spring.controllers.controllers.EndController;
import com.spring.models.utils.Tracer;
import com.spring.models.vehicle.Vehicle;
import com.spring.prototype.menuitems.MenuItem;

/**
 * Beállítja a következő hókotró lépést
 */
public class GetCar extends MenuItem {
    private EndController controller;
    private int serial;
    private static final Pattern PATTERN = Pattern.compile("(?i)^GET-CAR\\s+(\\d+)$");

    public GetCar(EndController controller) {
        super("GET-CAR <serial>");
        this.controller = controller;
    }

    @Override
    public void execute() {
        Vehicle vehicle = controller.getCar(serial);
        if(vehicle != null){
            Tracer.getInstance().newObject(vehicle);
        }
    }

    @Override
    public boolean parse(String input) {
        Matcher matcher = PATTERN.matcher(input.trim());
        if (matcher.matches()) {
            this.serial = Integer.parseInt(matcher.group(1));
            return true;
        }
        return false;
    }
}