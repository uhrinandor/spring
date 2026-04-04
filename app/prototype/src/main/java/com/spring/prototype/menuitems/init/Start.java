package com.spring.prototype.menuitems.init;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spring.controllers.controllers.InitController;
import com.spring.prototype.menuitems.MenuItem;

/**
 * Parancs: START <deterministicMode>
 * Ezzel tudjuk elindítani a játékot, determinisztikus módban vagy véletlenszerűen.
 */
public class Start extends MenuItem{
    InitController controller;
	boolean deterministicMode;

	private static final Pattern PATTERN = Pattern.compile("(?i)^START\\s+(T|F)$");

    public Start(InitController controller) {
        super("START <deterministicMode>");
        this.controller = controller;
    }

	@Override
	public void execute() {
		controller.start(deterministicMode);
	}

	@Override
	public boolean parse(String input) {
		 Matcher matcher = PATTERN.matcher(input.trim());
        if (matcher.matches()) {
            this.deterministicMode = matcher.group(1).equalsIgnoreCase("T");
            return true;
        }
        return false;
	}
    
}
