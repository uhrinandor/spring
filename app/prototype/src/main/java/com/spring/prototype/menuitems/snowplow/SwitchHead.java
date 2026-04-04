package com.spring.prototype.menuitems.snowplow;

import com.spring.controllers.controllers.SnowPlowController;
import com.spring.models.head.Broom;
import com.spring.models.head.Brush;
import com.spring.models.head.Dragon;
import com.spring.models.head.IHead;
import com.spring.models.head.IceBreaker;
import com.spring.models.head.SaltSpreader;
import com.spring.models.head.StoneSplasher;
import com.spring.prototype.menuitems.MenuItem;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Ez a menüpont felelős a hókotró fejének váltásáért
 */
public class SwitchHead extends MenuItem {
    private final SnowPlowController controller;
    private IHead head;
    private static final Pattern PATTERN = Pattern.compile("(?i)^SWITCH-HEAD\\s+(BROOM|BRUSH|SALTSPREADER|STONESPLASHER|DRAGON|ICEBREAKER)$");

    public SwitchHead(SnowPlowController controller) {
        super("SWITCH-HEAD <head>");
        this.controller = controller;
    }

    public IHead parseHead(String headType) {
        switch (headType.toUpperCase()) {
            case "BROOM":
                return new Broom();
            case "BRUSH":
                return new Brush();
            case "SALTSPREADER":
                return new SaltSpreader();
            case "STONESPLASHER":
                return new StoneSplasher();
            case "DRAGON":
                return new Dragon();
            case "ICEBREAKER":
                return new IceBreaker();
            default:
                return null;
        }
    }

    @Override
    public void execute() {
        controller.switchHead(head);
    }

    @Override
    public boolean parse(String input) {
        Matcher matcher = PATTERN.matcher(input.trim());
        if (matcher.matches()) {
            String headType = matcher.group(1).toUpperCase();
            this.head = parseHead(headType);
            return this.head != null;
        }
        return false;
    }
}