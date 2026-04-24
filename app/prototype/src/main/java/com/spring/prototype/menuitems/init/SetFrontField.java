package com.spring.prototype.menuitems.init;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spring.controllers.controllers.InitController;
import com.spring.prototype.menuitems.MenuItem;

/**
 * Ez a menüpont beállítja a field1 front attribútumát egy Field-re
 */
public class SetFrontField extends MenuItem {
    InitController controller;
    int field1;
    int field2;

    private static final Pattern PATTERN =
            Pattern.compile("(?i)^SET-FRONT-FIELD\\s+(\\d+)\\s+(\\d+)$");

    public SetFrontField(InitController controller) {
        super("SET-FRONT-FIELD <field1> <field2>");
        this.controller = controller;
    }

    @Override
    public boolean parse(String input) {
        Matcher matcher = PATTERN.matcher(input.trim());

        if (matcher.matches()) {
            field1 = Integer.parseInt(matcher.group(1));
            field2 = Integer.parseInt(matcher.group(2));
            return true;
        }

        return false;
    }

    @Override
    public void execute() {
        controller.setFrontField(field1, field2);
    }
}