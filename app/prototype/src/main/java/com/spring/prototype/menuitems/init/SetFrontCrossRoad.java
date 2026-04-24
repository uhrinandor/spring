package com.spring.prototype.menuitems.init;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spring.controllers.controllers.InitController;
import com.spring.prototype.menuitems.MenuItem;

/**
 * Ez a menüpont beállítja a field front attribútumát egy CrossRoad-ra
 */
public class SetFrontCrossRoad extends MenuItem {
    InitController controller;
    int field;
    int crossroad;

    private static final Pattern PATTERN =
            Pattern.compile("(?i)^SET-FRONT-CROSSROAD\\s+(\\d+)\\s+(\\d+)$");

    public SetFrontCrossRoad(InitController controller) {
        super("SET-FRONT-CROSSROAD <field> <crossroad>");
        this.controller = controller;
    }

    @Override
    public boolean parse(String input) {
        Matcher matcher = PATTERN.matcher(input.trim());

        if (matcher.matches()) {
            field = Integer.parseInt(matcher.group(1));
            crossroad = Integer.parseInt(matcher.group(2));
            return true;
        }

        return false;
    }

    @Override
    public void execute() {
        controller.setFrontCrossRoad(field, crossroad);
    }
}