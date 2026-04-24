package com.spring.prototype.menuitems.snowplowplayer;

import com.spring.controllers.controllers.SnowPlowPlayerController;
import com.spring.prototype.menuitems.MenuItem;

/**
 * Ez a menüpont felelős a következő lépés beállításáért a buszon
 */
public class NextPlayer extends MenuItem {
    SnowPlowPlayerController controller;

    
    public NextPlayer(SnowPlowPlayerController controller) {
		super("NEXT-PLAYER");
        this.controller = controller;
	}

	@Override
	public boolean parse(String input) {
		return input.equalsIgnoreCase("NEXT-PLAYER");
	}

	@Override
	public void execute() {
		controller.nextPlayer();
	}
}
