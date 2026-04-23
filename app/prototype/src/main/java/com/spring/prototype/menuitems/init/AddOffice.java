package com.spring.prototype.menuitems.init;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spring.controllers.controllers.InitController;
import com.spring.models.buildings.Building;
import com.spring.models.utils.Tracer;
import com.spring.prototype.menuitems.MenuItem;

public class AddOffice extends MenuItem {

    private InitController controller;
    private int index;

    private static final Pattern PATTERN = Pattern.compile(
        "^\\s*ADD-OFFICE\\s+(\\d+)\\s*$"
    );

    public AddOffice(InitController controller) {
        super("ADD-OFFICE <fieldIndex>");
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
        Building office = controller.addOffice(index);

        if (office == null) {
            return;
        }

        Tracer.getInstance().info(
            String.format("Office added next to %s", office.getField())
        );
    }
}