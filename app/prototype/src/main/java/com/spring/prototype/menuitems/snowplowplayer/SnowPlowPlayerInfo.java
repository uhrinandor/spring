package com.spring.prototype.menuitems.snowplowplayer;

import com.spring.controllers.controllers.SnowPlowPlayerController;
import com.spring.models.player.IRPlayer;
import com.spring.models.utils.Tracer;
import com.spring.prototype.menuitems.MenuItem;

/**
 * A játékos adatait jeleníti meg, mint például a pontszáma, járművei
 */
public class SnowPlowPlayerInfo extends MenuItem {
    SnowPlowPlayerController controller;
    public SnowPlowPlayerInfo(SnowPlowPlayerController controller) {
        super("INFO");
        this.controller = controller;
    }

	@Override
	public void execute() {
		IRPlayer player = controller.info();
        Tracer.getInstance().newObject(player);
	}

	@Override
	public boolean parse(String input) {
		return input.trim().equalsIgnoreCase("INFO");
	}
}
