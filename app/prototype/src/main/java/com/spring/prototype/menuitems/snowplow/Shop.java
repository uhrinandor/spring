package com.spring.prototype.menuitems.snowplow;

import com.spring.controllers.controllers.SnowPlowController;
import com.spring.models.utils.Tracer;
import com.spring.prototype.menuitems.MenuItem;

import java.util.List;

import com.spring.models.shop.ShopItem;


public class Shop extends MenuItem {
    private final SnowPlowController controller;

    public Shop(SnowPlowController controller) {
        super("SHOP");
        this.controller = controller;
    }

    @Override
    public void execute() {
        List<ShopItem> items = controller.shop();
        Tracer tracer = Tracer.getInstance();
        for (int i = 0; i < items.size(); i++) {
            tracer.info(String.format("[%d] %s - %d pt", i, items.get(i).toString(), items.get(i).price()));
        }
    }

    @Override
    public boolean parse(String input) {
        return input.trim().equalsIgnoreCase("SHOP");
    }
}