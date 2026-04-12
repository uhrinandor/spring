package com.spring.prototype.views;

import java.util.List;

import com.spring.controllers.controllers.BaseController;
import com.spring.controllers.listeners.OnErrorListener;
import com.spring.models.utils.Tracer;
import com.spring.prototype.menuitems.MenuItem;
import com.spring.prototype.utils.Navigator;

/**
 * Minden nézet alap osztálya, ami implementálja az IView-t és az OnErrorListener-t. Ez tartalmazza a közös logikát a nézetek számára, mint például a parancsok kezelése és a hibák megjelenítése.
 */
public abstract class BaseView implements IView, OnErrorListener {
    Navigator navigator;
    List<MenuItem> menuItems;
    Tracer tracer = Tracer.getInstance();
    protected BaseController  controller;
    
    protected BaseView(Navigator navigator, List<MenuItem> menuItems, BaseController controller) {
        this.navigator = navigator;
        this.menuItems = menuItems;
        this.controller = controller;
        controller.addErrorListener(this);
    }

    @Override
    public void render(){
        tracer.info(getTitle());
    }

    @Override
    public void handle(String input){
        boolean valid = false;
        for(MenuItem item: menuItems){
            if(item.tryExecute(input)) {
                valid = true;
                break;
            }
        }
        if(!valid) tracer.error("Invalid command!");
    }

    @Override
    public void onError(String message) {
        tracer.error(message);
    }

    @Override
	public void showCommands() {
		tracer.info("Available commands:");
        for(MenuItem item: menuItems){
            tracer.info(item.getTitle());
        }
        tracer.info("HELP");
        tracer.info("EXIT");
	}
}
