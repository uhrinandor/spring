package com.spring.prototype.views;

import java.util.List;

import com.spring.controllers.controllers.SnowPlowController;
import com.spring.controllers.listeners.SnowPlowListener;
import com.spring.prototype.menuitems.snowplow.Back;
import com.spring.prototype.menuitems.snowplow.Buy;
import com.spring.prototype.menuitems.snowplow.SnowPlowListAvailable;
import com.spring.prototype.menuitems.snowplow.Shop;
import com.spring.prototype.menuitems.snowplow.SnowplowInfo;
import com.spring.prototype.menuitems.snowplow.SnowplowSetNext;
import com.spring.prototype.menuitems.snowplow.SwitchHead;
import com.spring.prototype.utils.Navigator;

/**
 * Amikor egy hókotró játékos kiválaszt egy hókotrót ezt fogjuk megjeleníteni
 * Itt tudunk vásárolni új elemet a hókotróra, megnézni a hókotró adatait, fejet váltani, és beállítani a következő lépést a hókotróval. Itt tudunk visszalépni a játékos nézetre is
*/
public class SnowPlowView extends BaseView implements SnowPlowListener {
    SnowPlowPlayerView playerView;
	protected SnowPlowView(Navigator navigator, SnowPlowPlayerView playerView, SnowPlowController controller) {
		super(navigator, List.of(new SnowplowInfo(controller), new Shop(controller), new SnowPlowListAvailable(controller), new Buy(controller), new SwitchHead(controller), new SnowplowSetNext(controller), new Back(controller)), controller);
        this.playerView = playerView;
		controller.addSnowPlowListener(this);
	}

	@Override
	public String getTitle() {
		return "-- SNOWPLOW --";
	}

	@Override
	public void onExit() {
		navigator.navigateTo(playerView);
	}
    
}
