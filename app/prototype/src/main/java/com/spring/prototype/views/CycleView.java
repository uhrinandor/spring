package com.spring.prototype.views;

import java.util.List;

import com.spring.controllers.controllers.BusPlayerController;
import com.spring.controllers.controllers.CycleController;
import com.spring.controllers.controllers.EndController;
import com.spring.controllers.controllers.SnowPlowPlayerController;
import com.spring.controllers.listeners.CycleListener;
import com.spring.models.player.BusPlayer;
import com.spring.models.player.SnowplowPlayer;
import com.spring.prototype.utils.Navigator;

/**
 * Ez a nézet felelős a játék körének megjelenítéséért.
 * Igazából csak egy konténer, mindig egy belső nézetet jelenít meg
 */
public class CycleView extends BaseView implements CycleListener {
    SnowPlowPlayerView snowPlowPlayerView;
    SnowPlowPlayerController snowPlowPlayerController;

	BusPlayerController busPlayerController;
	BusPlayerView busPlayerView;

	CycleController controller;

	protected CycleView(Navigator navigator, CycleController controller) {
		super(navigator, List.of(), controller);

        snowPlowPlayerController = new SnowPlowPlayerController(controller, null);
        snowPlowPlayerView = new SnowPlowPlayerView(navigator, snowPlowPlayerController);

		busPlayerController = new BusPlayerController(controller);
		busPlayerView = new BusPlayerView(navigator, busPlayerController);
		this.controller = controller;
        controller.addCycleListener(this);
	}

	@Override
	public String getTitle() {
		return "-- GAME --";
	}

	@Override
	public void nextSnowPlowPlayer(SnowplowPlayer player) {
        snowPlowPlayerController.setPlayer(player);
        navigator.navigateTo(snowPlowPlayerView);
	}

	@Override
	public void nextBusPlayer(BusPlayer player) {
		busPlayerController.setPlayer(player);
		navigator.navigateTo(busPlayerView);
	}

	@Override
	public void onGameEnd(SnowplowPlayer winner1, BusPlayer winner2) {
		tracer.info("The game has ended!");
		EndController endController = new EndController(winner1, winner2, controller.getContext());
		navigator.navigateTo(new EndView(navigator, endController));
	}
}
