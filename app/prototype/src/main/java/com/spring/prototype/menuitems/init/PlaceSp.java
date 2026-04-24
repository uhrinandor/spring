package com.spring.prototype.menuitems.init;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spring.controllers.controllers.InitController;
import com.spring.prototype.menuitems.MenuItem;

/**
 * Ez a menüpont lerakja a snowplowplayer sorszámú játékos hókotróját a field sorszámú mezőre
 */
public class PlaceSp extends MenuItem {
    InitController controller;
    int snowplowplayer;
    int field;

    private static final Pattern PATTERN =
            Pattern.compile("(?i)^PLACE-SP\\s+(\\d+)\\s+(\\d+)$");

    public PlaceSp(InitController controller) {
        super("PLACE-SP <snowplowplayer> <field>");
        this.controller = controller;
    }

    @Override
    public boolean parse(String input) {
        Matcher matcher = PATTERN.matcher(input.trim());

        if (matcher.matches()) {
            snowplowplayer = Integer.parseInt(matcher.group(1));
            field = Integer.parseInt(matcher.group(2));
            return true;
        }

        return false;
    }

    @Override
    public void execute() {
        controller.placeSp(snowplowplayer, field);
    }
}