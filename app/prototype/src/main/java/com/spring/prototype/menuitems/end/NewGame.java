package com.spring.prototype.menuitems.end;

import com.spring.controllers.controllers.EndController;
import com.spring.prototype.menuitems.MenuItem;

/**
  * Új játékot indít, visszaállítja a játék állapotát a kezdetire
*/
public class NewGame extends MenuItem{
   
    EndController endController;
    public NewGame(EndController endController) {
        super("NEW");
        this.endController = endController;
	}

	@Override
	public void execute() {
		endController.newGame();
	}

	@Override
	public boolean parse(String input) {
		return input.equalsIgnoreCase("NEW");
	}
}
