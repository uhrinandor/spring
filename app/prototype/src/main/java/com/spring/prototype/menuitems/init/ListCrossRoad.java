package com.spring.prototype.menuitems.init;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spring.controllers.controllers.InitController;
import com.spring.prototype.menuitems.MenuItem;

/**
 * Ez a menüpont kilistázza sorszámozva az elérhető CrossRoad-okat
 */
public class ListCrossRoad extends MenuItem {
    InitController controller;
    private static final Pattern PATTERN = Pattern.compile("(?i)^LIST-CROSSROAD$");

    public ListCrossRoad(InitController controller) {
        super("LIST-CROSSROAD");
        this.controller = controller;
    }

    @Override
    public boolean parse(String input) {
        Matcher matcher = PATTERN.matcher(input.trim());
        return matcher.matches();
    }

    @Override
    public void execute() {
        controller.listCrossRoads();
    }
}