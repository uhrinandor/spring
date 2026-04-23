package com.spring.prototype.menuitems.init;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spring.controllers.controllers.InitController;
import com.spring.models.field.IRoad;
import com.spring.models.utils.Tracer;
import com.spring.prototype.menuitems.MenuItem;

public class AddCrossRoad extends MenuItem {

    private InitController controller;
    private List<Integer> indices;

    private static final Pattern PATTERN = Pattern.compile(
        "^\\s*ADD-CROSSROAD\\s+([0-9]+(?:\\s*,\\s*[0-9]+)*)\\s*$"
    );

    public AddCrossRoad(InitController controller) {
        super("ADD-CROSSROAD <1,2,...>");
        this.controller = controller;
    }

    @Override
    public boolean parse(String input) {
        Matcher matcher = PATTERN.matcher(input.trim());

        if (matcher.matches()) {
            String group = matcher.group(1);

            indices = Arrays.stream(group.split("\\s*,\\s*"))
                            .map(Integer::parseInt)
                            .toList();

            return true;
        }

        return false;
    }

    @Override
    public void execute() {
        IRoad road = controller.addCrossRoad(indices);

        if (road != null) {
            Tracer.getInstance().info(
                String.format("CrossRoad added with outgoing fields: %s", road.getAvailable())
            );
        }
    }
}