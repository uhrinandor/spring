package com.spring.prototype.menuitems.init;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spring.controllers.controllers.InitController;
import com.spring.models.utils.Tracer;
import com.spring.prototype.menuitems.MenuItem;

/**
 * Parancs: SET-SNOWING <isSnowing>
 * Ezzel tudjuk elindítani a játékot, determinisztikus módban vagy véletlenszerűen.
 */
public class SetSnowing extends MenuItem{
    InitController controller;
	boolean isSnowing;

	private static final Pattern PATTERN = Pattern.compile("(?i)^SET-SNOWING\\s+(T|F)$");

    public SetSnowing(InitController controller) {
        super("SET-SNOWING <isSnowing>");
        this.controller = controller;
    }

	@Override
	public void execute() {
		controller.setSnowing(isSnowing);
        Tracer.getInstance().info("Snowing set to: " + isSnowing);
	}

	@Override
	public boolean parse(String input) {
		 Matcher matcher = PATTERN.matcher(input.trim());
        if (matcher.matches()) {
            this.isSnowing = matcher.group(1).equalsIgnoreCase("T");
            return true;
        }
        return false;
	}
    
}
