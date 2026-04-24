package com.spring.prototype.menuitems.init;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spring.controllers.controllers.InitController;
import com.spring.prototype.menuitems.MenuItem;

/**
 * Ez a menüpont beállítja két field közötti kapcsolat irányát
 */
public class SetSide extends MenuItem {
    InitController controller;
    boolean direction;
    int field1;
    int field2;

    private static final Pattern PATTERN =
            Pattern.compile("(?i)^SET-SIDE\\s+(RIGHT|LEFT)\\s+(\\d+)\\s+(\\d+)$");

    public SetSide(InitController controller) {
        super("SET-SIDE <direction> <field1> <field2>");
        this.controller = controller;
    }

    @Override
    public boolean parse(String input) {
        Matcher matcher = PATTERN.matcher(input.trim());

        if (matcher.matches()) {
            direction = matcher.group(1).equalsIgnoreCase("RIGHT");
            field1 = Integer.parseInt(matcher.group(2));
            field2 = Integer.parseInt(matcher.group(3));
            return true;
        }

        return false;
    }

    @Override
    public void execute() {
        controller.setSide(direction, field1, field2);
    }
}