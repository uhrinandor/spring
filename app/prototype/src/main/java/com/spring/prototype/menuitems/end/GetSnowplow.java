package com.spring.prototype.menuitems.end;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spring.controllers.controllers.EndController;
import com.spring.models.utils.Tracer;
import com.spring.models.vehicle.Vehicle;
import com.spring.prototype.menuitems.MenuItem;

/**
 * Visszaadja a hókotró játékos által vezetett hókotrót a sorozatszám alapján
 */
public class GetSnowplow extends MenuItem {
    private EndController controller;
    private int serial;
    private static final Pattern PATTERN = Pattern.compile("(?i)^GET-SNOWPLOW\\s+(\\d+)$");

    public GetSnowplow(EndController controller) {
        super("GET-SNOWPLOW <serial>");
        this.controller = controller;
    }

    @Override
    public void execute() {
        Vehicle vehicle = controller.getSnowplow(serial);
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