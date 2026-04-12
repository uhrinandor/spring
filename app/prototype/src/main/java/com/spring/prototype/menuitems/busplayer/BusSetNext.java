package com.spring.prototype.menuitems.busplayer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spring.controllers.controllers.BusPlayerController;
import com.spring.prototype.menuitems.MenuItem;

/**
 * Ez a menüpont felelős a következő lépés beállításáért a buszon
 */
public class BusSetNext extends MenuItem {
    BusPlayerController controller;
    int serial;
    private static final Pattern PATTERN = Pattern.compile("(?i)^SET-NEXT\\s+(\\d+)$");
    
    public BusSetNext(BusPlayerController controller) {
		super("SET-NEXT <serial>");
        this.controller = controller;
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

	@Override
	public void execute() {
		controller.setNext(serial);
	}
}
