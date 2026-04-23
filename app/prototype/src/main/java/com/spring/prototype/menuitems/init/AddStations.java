package com.spring.prototype.menuitems.init;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spring.controllers.controllers.InitController;
import com.spring.models.buildings.Building;
import com.spring.models.utils.Tracer;
import com.spring.prototype.menuitems.MenuItem;

public class AddStations extends MenuItem {

    private InitController controller;
    private int index1;
    private int index2;

    private static final Pattern PATTERN = Pattern.compile(
        "^\\s*ADD-STATIONS\\s+(\\d+)\\s+(\\d+)\\s*$"
    );

    public AddStations(InitController controller) {
        super("ADD-STATIONS <fieldIndex1> <fieldIndex2>");
        this.controller = controller;
    }

    @Override
    public boolean parse(String input) {
        Matcher matcher = PATTERN.matcher(input.trim());

        if (matcher.matches()) {
            index1 = Integer.parseInt(matcher.group(1));
            index2 = Integer.parseInt(matcher.group(2));
            return true;
        }

        return false;
    }

    @Override
    public void execute() {
        List<Building> stations = controller.addStations(index1, index2);

        if (stations == null) {
            return;
        }

        Tracer.getInstance().info(
            String.format(
                "Stations %s and %s added at fields %s and %s",
                stations.get(0),
                stations.get(1),
                stations.get(0).getField(),
                stations.get(1).getField()
            )
        );
    }
}