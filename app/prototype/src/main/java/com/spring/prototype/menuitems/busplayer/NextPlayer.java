package com.spring.prototype.menuitems.busplayer;

import com.spring.controllers.controllers.BusPlayerController;
import com.spring.prototype.menuitems.MenuItem;

/**
 * Ez a menüpont felelős a következő lépés beállításáért a buszon
 */
public class NextPlayer extends MenuItem {
    BusPlayerController controller;

    
    public NextPlayer(BusPlayerController controller) {
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
