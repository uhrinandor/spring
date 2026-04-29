package com.spring.prototype.menuitems.snowplow;

import com.spring.controllers.controllers.InitController;
import com.spring.prototype.menuitems.MenuItem;

public class AddInventory extends MenuItem{
    protected AddInventory(InitController controller) {
        super("ADD-INVENTORY <item>");
    }

    InitController controller;
    String item;

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    @Override
    public boolean parse(String input) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parse'");
    }
    
}
