package com.spring.prototype.menuitems.init;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spring.controllers.controllers.InitController;
import com.spring.prototype.menuitems.MenuItem;

/**
 * Parancs: ROUNDS <number>
 * Ezzel tudjuk megadni, hogy hány kör legyen a játékban
 * Regex-el parsol és elmenti, hogy hány kör legyen, majd execute-ban meghívja a kontrollert, hogy állítsa be a játékra vonatkozóan
 */
public class Rounds extends MenuItem {
    int rounds;
    InitController controller;

    private static final Pattern PATTERN = Pattern.compile("(?i)^ROUNDS\\s+(\\d+)$");

	public Rounds(InitController controller) {
		super("ROUNDS <number>");
		this.controller = controller;
	}

	@Override
	public boolean parse(String input) {
		Matcher matcher = PATTERN.matcher(input.trim());

        if(matcher.matches()){
            rounds = Integer.parseInt(matcher.group(1));
            return true;
        }

        return false;
	}

	@Override
	public void execute() {
		controller.rounds(rounds);
	}
    
}
