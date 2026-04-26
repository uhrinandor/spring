package com.spring.prototype.views;

import java.util.List;

import com.spring.controllers.controllers.BusPlayerController;
import com.spring.prototype.menuitems.busplayer.BusListAvailable;
import com.spring.prototype.menuitems.busplayer.BusPlayerInfo;
import com.spring.prototype.menuitems.busplayer.BusSetNext;
import com.spring.prototype.menuitems.busplayer.NextPlayer;
import com.spring.prototype.utils.Navigator;

/**
 * Amikor a soron kövektező játékos egy buszjátékos ezt jelenítjük meg
 */
public class BusPlayerView extends BaseView{

	protected BusPlayerView(Navigator navigator, BusPlayerController controller) {
		super(navigator, List.of(new BusPlayerInfo(controller),
		new BusListAvailable(controller), 
		new NextPlayer(controller),
		new BusSetNext(controller)), controller);
	}

	@Override
	public String getTitle() {
		return "-- BUS PLAYER --";
	}
    
}
