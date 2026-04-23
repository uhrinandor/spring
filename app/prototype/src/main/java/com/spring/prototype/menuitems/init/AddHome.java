package com.spring.prototype.menuitems.init;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spring.controllers.controllers.InitController;
import com.spring.models.buildings.Building;
import com.spring.models.utils.Tracer;
import com.spring.prototype.menuitems.MenuItem;

public class AddHome extends MenuItem {

    private InitController controller;
    private int index;

    private static final Pattern PATTERN = Pattern.compile(
        "^\\s*ADD-HOME\\s+(\\d+)\\s*$"
    );

    public AddHome(InitController controller) {
        super("ADD-HOME <fieldIndex>");
        this.controller = controller;
    }

    @Override
    public boolean parse(String input) {
        Matcher matcher = PATTERN.matcher(input.trim());

        if (matcher.matches()) {
            index = Integer.parseInt(matcher.group(1));
            return true;
        }

        return false;
    }

    @Override
    public void execute() {
        Building home = controller.addHome(index);

        if (home == null) {
            return;
        }

        Tracer.getInstance().info(
            String.format("Home added next to %s", home.getField())
        );
    }
}