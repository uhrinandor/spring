package com.spring.prototype.menuitems.snowplow;


import com.spring.controllers.controllers.SnowPlowController;
import com.spring.models.utils.Tracer;
import com.spring.models.vehicle.ISnowPlow;
import com.spring.prototype.menuitems.MenuItem;

public class SnowplowInfo extends MenuItem {
    SnowPlowController controller;
	public SnowplowInfo(SnowPlowController controller) {
		super("INFO");
        this.controller = controller;
	}

	@Override
	public void execute() {
		ISnowPlow snowplow = controller.info();
        Tracer tracer = Tracer.getInstance();
        tracer.newObject(snowplow);
	}

	@Override
	public boolean parse(String input) {
		return input.trim().equalsIgnoreCase("INFO");
	}
    
}
