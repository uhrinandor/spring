package com.spring.prototype.views;

import java.util.List;

import com.spring.controllers.controllers.EndController;
import com.spring.controllers.controllers.InitController;
import com.spring.controllers.listeners.EndListener;
import com.spring.prototype.menuitems.end.NewGame;
import com.spring.prototype.menuitems.end.Winners;
import com.spring.prototype.utils.Navigator;

/**
 * A játék végén megjelenő nézet, a győzteseket listázza ki, és lehetőséget ad új játék indítására
 */
public class EndView extends BaseView implements EndListener{
    protected EndView(Navigator navigator, EndController controller) {
        super(navigator, List.of(new Winners(controller), new NewGame(controller)), controller);
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
