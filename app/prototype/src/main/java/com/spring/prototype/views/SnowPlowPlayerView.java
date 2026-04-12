package com.spring.prototype.views;

import java.util.List;

import com.spring.controllers.controllers.SnowPlowController;
import com.spring.controllers.controllers.SnowPlowPlayerController;
import com.spring.controllers.listeners.SnowPlowPlayerListener;
import com.spring.models.vehicle.Snowplow;
import com.spring.prototype.menuitems.snowplowplayer.BuySnowPlow;
import com.spring.prototype.menuitems.snowplowplayer.SelectSnowPlow;
import com.spring.prototype.menuitems.snowplowplayer.SnowPlowPlayerInfo;
import com.spring.prototype.menuitems.snowplowplayer.StepAll;
import com.spring.prototype.utils.Navigator;

/**
 * Amikor a soron következő játékos egy hókotró játékos ezt jelenítjük meg. Itt tudja megnézni a játékos a pontszámát, járműveit, kiválasztani járművet, és vásárolni új hókotrókat
 */
public class SnowPlowPlayerView extends BaseView implements SnowPlowPlayerListener {
	SnowPlowView snowPlowView;
	SnowPlowController snowPlowController;
	SnowPlowPlayerController playerController;

    protected SnowPlowPlayerView(Navigator navigator, SnowPlowPlayerController controller) {
		super(navigator, List.of(new SnowPlowPlayerInfo(controller), new StepAll(controller), new SelectSnowPlow(controller), new BuySnowPlow(controller)), controller);
		controller.addSnowPlowPlayerListener(this);
		this.playerController = controller;
		this.snowPlowController = new SnowPlowController();
		this.snowPlowView = new SnowPlowView(navigator, this, snowPlowController);
	}

	@Override
	public String getTitle() {
		return "-- SNOWPLOW PLAYER --";
	}

	@Override
	public void onSnowPlowSelected() {
		snowPlowController.setPlayer(playerController.info());
		navigator.navigateTo(snowPlowView);
	}
}
