package com.spring.prototype.menuitems.init;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spring.controllers.controllers.InitController;
import com.spring.prototype.menuitems.MenuItem;

/**
 * Ez a menüpont lerakja a busplayer sorszámú játékos buszát a field sorszámú mezőre
 */
public class PlaceBus extends MenuItem {
    InitController controller;
    int busplayer;
    int field;

    private static final Pattern PATTERN =
            Pattern.compile("(?i)^PLACE-BUS\\s+(\\d+)\\s+(\\d+)$");

    public PlaceBus(InitController controller) {
        super("PLACE-BUS <busplayer> <field>");
        this.controller = controller;
    }

    @Override
    public boolean parse(String input) {
        Matcher matcher = PATTERN.matcher(input.trim());

        if (matcher.matches()) {
            busplayer = Integer.parseInt(matcher.group(1));
            field = Integer.parseInt(matcher.group(2));
            return true;
        }

        return false;
    }

    @Override
    public void execute() {
        controller.placeBus(busplayer, field);
    }
}