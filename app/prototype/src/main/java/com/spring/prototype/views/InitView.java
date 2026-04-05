package com.spring.prototype.views;

import java.util.List;

import com.spring.controllers.controllers.CycleController;
import com.spring.controllers.controllers.InitController;
import com.spring.controllers.listeners.GameStartedListener;
import com.spring.controllers.utils.GameContext;
import com.spring.prototype.menuitems.init.AddPlayer;
import com.spring.prototype.menuitems.init.Rounds;
import com.spring.prototype.menuitems.init.Start;
import com.spring.prototype.utils.Navigator;

/**
 * Az INIT állapot megjelenítéséért felelős osztály. Itt tudjuk beállítani a játék paramétereit, hozzáadni játékosokat és elindítani a játékot.
 * Amint a játék elindul, ez a nézet eltűnik és megjelenik a CycleView
 */
public class InitView extends BaseView implements GameStartedListener{
    InitController controller;
	
	public InitView(Navigator navigator, InitController controller) {
        super(navigator, List.of(
            new Rounds(controller),
            new Start(controller), 
            new AddPlayer(controller)
        ), controller);
        this.controller = controller;
        controller.addListener(this);
	}

	@Override
	public String getTitle() {
		return "-- INIT --";
	}

	@Override
	public void onGameStarted(GameContext context) {
		CycleController cycleController = new CycleController(context);
		CycleView cycleView = new CycleView(navigator, cycleController);
		navigator.navigateTo(cycleView);
		cycleController.cycle();
	}

}
