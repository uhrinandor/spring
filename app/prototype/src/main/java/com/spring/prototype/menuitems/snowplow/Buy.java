package com.spring.prototype.menuitems.snowplow;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spring.controllers.controllers.SnowPlowController;
import com.spring.prototype.menuitems.MenuItem;

/**
 * A hókotróra tudunk vásárolni új elemeket(fejek, muníció)
 */
public class Buy extends MenuItem {
    private final SnowPlowController controller;
    private int serial;
    private static final Pattern PATTERN = Pattern.compile("(?i)^BUY\\s+(\\d+)$");

    public Buy(SnowPlowController controller) {
        super("BUY <serial>");
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.buy(serial);
    }

    @Override
    public boolean parse(String input) {
        Matcher matcher = PATTERN.matcher(input.trim());
        if (matcher.matches()) {
            this.serial = Integer.parseInt(matcher.group(1));
            return true;
        }
        return false;
    }
}