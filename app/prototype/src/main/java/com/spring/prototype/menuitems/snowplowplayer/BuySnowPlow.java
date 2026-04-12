package com.spring.prototype.menuitems.snowplowplayer;

import com.spring.controllers.controllers.SnowPlowPlayerController;
import com.spring.prototype.menuitems.MenuItem;

/**
 * Ez a menüpont felelős a hókotró vásárlásáért
 */
public class BuySnowPlow  extends MenuItem{
    SnowPlowPlayerController controller;
	public BuySnowPlow(SnowPlowPlayerController controller) {
		super("BUY-SNOWPLOW");
        this.controller = controller;
	}

	@Override
	public void execute() {
		controller.buySnowPlow();
	}

	@Override
	public boolean parse(String input) {
		return input.trim().equalsIgnoreCase("BUY-SNOWPLOW");
	}
    
}
