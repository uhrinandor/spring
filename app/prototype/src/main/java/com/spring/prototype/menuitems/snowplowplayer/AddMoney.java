package com.spring.prototype.menuitems.snowplowplayer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spring.controllers.controllers.SnowPlowPlayerController;
import com.spring.models.utils.Tracer;
import com.spring.prototype.menuitems.MenuItem;

/**
 * Ez a menüpont felelős a hókotró kiválasztásáért
 */
public class AddMoney extends MenuItem{
    int amount;
    SnowPlowPlayerController controller;
    private static final Pattern PATTERN = Pattern.compile("(?i)^ADD-MONEY\\s+(\\d+)$");

    public AddMoney(SnowPlowPlayerController controller) {
        super("ADD-MONEY <serial>" );
        this.controller = controller;
    }
	@Override
	public void execute() {
		controller.addMoney(amount);
        Tracer.getInstance().info(String.format("Added %d money to player %s", amount, controller.info()));
	}

	@Override
	public boolean parse(String input) {
        Matcher matcher = PATTERN.matcher(input.trim());

        if(matcher.matches()){
            amount = Integer.parseInt(matcher.group(1));
            return true;
        }

        return false;
	}
    
}
