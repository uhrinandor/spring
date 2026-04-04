package com.spring.prototype.menuitems.init;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spring.controllers.controllers.InitController;
import com.spring.prototype.menuitems.MenuItem;

/**
 * Parancs: ADD <type>
 * Ezzel tudunk hozzáadni új játékost a játékhoz
 */
public class AddPlayer extends MenuItem{
    InitController controller;
    String type;

    private static final Pattern PATTERN = Pattern.compile("(?i)^ADD\\s+(SP|BP)$");

    public AddPlayer(InitController controller) {
        super("ADD <type>");
        this.controller = controller;
    }

	@Override
	public void execute() {
		if(type.equals("SP")) controller.addSnowplowPlayer();
        else controller.addBusPlayer();
	}

	@Override
	public boolean parse(String input) {
		Matcher matcher = PATTERN.matcher(input.trim());

        if(matcher.matches()){
            type = matcher.group(1);
            return true;
        }

        return false;
	}
    
}
