package com.spring.prototype.menuitems.snowplowplayer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spring.controllers.controllers.SnowPlowPlayerController;
import com.spring.prototype.menuitems.MenuItem;

/**
 * Ez a menüpont felelős a hókotró kiválasztásáért
 */
public class SelectSnowPlow extends MenuItem{
    int serial;
    SnowPlowPlayerController controller;
    private static final Pattern PATTERN = Pattern.compile("(?i)^SELECT\\s+(\\d+)$");

    public SelectSnowPlow(SnowPlowPlayerController controller) {
        super("SELECT <serial>" );
        this.controller = controller;
    }
	@Override
	public void execute() {
		controller.select(serial);
	}

	@Override
	public boolean parse(String input) {
        Matcher matcher = PATTERN.matcher(input.trim());

        if(matcher.matches()){
            serial = Integer.parseInt(matcher.group(1));
            return true;
        }

        return false;
	}
    
}
