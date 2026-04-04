package com.spring.prototype.menuitems.snowplow;

import com.spring.controllers.controllers.SnowPlowController;
import com.spring.prototype.menuitems.MenuItem;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Beállítja a következő hókotró lépést
 */
public class SnowplowSetNext extends MenuItem {
    private SnowPlowController controller;
    private int serial;
    private static final Pattern PATTERN = Pattern.compile("(?i)^SET-NEXT\\s+(\\d+)$");

    public SnowplowSetNext(SnowPlowController controller) {
        super("SET-NEXT <serial>");
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.setNext(serial);
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