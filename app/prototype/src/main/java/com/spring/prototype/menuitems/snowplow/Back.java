package com.spring.prototype.menuitems.snowplow;

import com.spring.controllers.controllers.SnowPlowController;
import com.spring.prototype.menuitems.MenuItem;

/**
 * Visszalépteti a játékos viewra
 */
public class Back extends MenuItem{
    SnowPlowController controller;
	public Back(SnowPlowController controller) {
		super("BACK");
        this.controller = controller;
	}

	@Override
	public void execute() {
		controller.back();
	}

	@Override
	public boolean parse(String input) {
		return input.trim().equalsIgnoreCase("BACK");
	}
}
