package com.spring.prototype.menuitems.busplayer;

import com.spring.controllers.controllers.BusPlayerController;
import com.spring.models.player.BusPlayer;
import com.spring.models.utils.Tracer;
import com.spring.prototype.menuitems.MenuItem;

/**
 * A játékos adatait jeleníti meg, mint például a pontszáma, busza
 */
public class BusPlayerInfo extends MenuItem {
    BusPlayerController controller;
    public BusPlayerInfo(BusPlayerController controller) {
        super("INFO");
        this.controller = controller;
    }

	@Override
	public void execute() {
		BusPlayer player = controller.info();
        Tracer.getInstance().info(player.toString());
		//TODO:
	}

	@Override
	public boolean parse(String input) {
		return input.trim().equalsIgnoreCase("INFO");
	}
    
}