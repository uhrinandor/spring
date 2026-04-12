package com.spring.prototype.menuitems.snowplowplayer;

import com.spring.controllers.controllers.SnowPlowPlayerController;
import com.spring.models.player.SnowplowPlayer;
import com.spring.models.utils.Tracer;
import com.spring.models.vehicle.Vehicle;
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
		SnowplowPlayer player = controller.info();
        Tracer.getInstance().info(player.toString());
		for(int i=0; i<player.vehicles().size(); i++){
			Vehicle v = player.vehicles().get(i);
			Tracer.getInstance().info(String.format("[%d] %s", i, v.toString()));
		}
	}

	@Override
	public boolean parse(String input) {
		return input.trim().equalsIgnoreCase("INFO");
	}
    
}
