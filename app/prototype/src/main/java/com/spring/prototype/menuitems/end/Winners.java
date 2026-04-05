package com.spring.prototype.menuitems.end;

import java.util.List;

import com.spring.models.player.IPlayer;
import com.spring.models.utils.Tracer;
import com.spring.controllers.controllers.EndController;
import com.spring.prototype.menuitems.MenuItem;

/**
 * Ez a menüpont felelős a győztesek kilistázásáért a játék végén
 */
public class Winners extends MenuItem {
    EndController endController;
    public Winners(EndController endController) {
        super("WINNERS");
        this.endController = endController;
	}


	@Override
	public void execute() {
		List<IPlayer> winners = endController.winners();
        
        for(IPlayer player : winners){
            Tracer.getInstance().info(player.toString());
        }
	}

	@Override
	public boolean parse(String input) {
		return input.equalsIgnoreCase("WINNERS");
	}
    
}
