package com.spring.prototype.views;

import java.util.List;

import com.spring.controllers.controllers.EndController;
import com.spring.controllers.controllers.InitController;
import com.spring.controllers.listeners.EndListener;
import com.spring.prototype.menuitems.end.GetBus;
import com.spring.prototype.menuitems.end.GetBusPlayer;
import com.spring.prototype.menuitems.end.GetCar;
import com.spring.prototype.menuitems.end.GetField;
import com.spring.prototype.menuitems.end.GetSnowplow;
import com.spring.prototype.menuitems.end.GetSnowplowPlayer;
import com.spring.prototype.menuitems.end.NewGame;
import com.spring.prototype.menuitems.end.Winners;
import com.spring.prototype.utils.Navigator;

/**
 * A játék végén megjelenő nézet, a győzteseket listázza ki, és lehetőséget ad új játék indítására
 */
public class EndView extends BaseView implements EndListener{
    protected EndView(Navigator navigator, EndController controller) {
        super(navigator, List.of(new Winners(controller),
        new NewGame(controller),
        new Winners(controller),
        new GetCar(controller),
        new GetBusPlayer(controller),
        new GetField(controller),
        new GetSnowplowPlayer(controller),
        new GetSnowplow(controller),
        new GetBus(controller)
        ), controller);
        controller.addEndListener(this);
    }

    @Override
    public String getTitle() {
        return "-- GAME END --";
    }

	@Override
	public void onNewGame() {
        InitController initController = new InitController();
		InitView initView = new InitView(navigator, initController);
        navigator.navigateTo(initView);
	}
}
