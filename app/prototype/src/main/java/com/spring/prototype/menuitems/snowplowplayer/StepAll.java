package com.spring.prototype.menuitems.snowplowplayer;

import com.spring.controllers.controllers.SnowPlowPlayerController;
import com.spring.prototype.menuitems.MenuItem;

/**
 * A beállított lépéseket végrehajtja
 */
public class StepAll extends MenuItem {
    SnowPlowPlayerController controller;

    public StepAll(SnowPlowPlayerController controller) {
        super("STEP-ALL");
        this.controller = controller;
    }

	@Override
	public void execute() {
		controller.stepAll();
	}

	@Override
	public boolean parse(String input) {
		return input.trim().equalsIgnoreCase("STEP-ALL");
	}   
}
