package com.spring.prototype.views;

import java.util.List;

import com.spring.controllers.controllers.CycleController;
import com.spring.controllers.controllers.InitController;
import com.spring.controllers.listeners.GameStartedListener;
import com.spring.controllers.utils.GameContext;
import com.spring.prototype.menuitems.init.AddCrossRoad;
import com.spring.prototype.menuitems.init.AddField;
import com.spring.prototype.menuitems.init.AddOffice;
import com.spring.prototype.menuitems.init.AddPlayer;
import com.spring.prototype.menuitems.init.AddStations;
import com.spring.prototype.menuitems.init.ListCrossRoad;
import com.spring.prototype.menuitems.init.ListField;
import com.spring.prototype.menuitems.init.PlaceBus;
import com.spring.prototype.menuitems.init.PlaceSp;
import com.spring.prototype.menuitems.init.Rounds;
import com.spring.prototype.menuitems.init.SetFrontCrossRoad;
import com.spring.prototype.menuitems.init.SetFrontField;
import com.spring.prototype.menuitems.init.SetSide;
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
            new AddPlayer(controller),
			new AddField(controller),
			new AddCrossRoad(controller),
			new AddOffice(controller),
			new AddStations(controller),
			new ListCrossRoad(controller),
			new ListField(controller),
			new SetSide(controller),
			new SetFrontCrossRoad(controller),
			new SetFrontField(controller),
			new PlaceBus(controller),
			new PlaceSp(controller)
			
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
