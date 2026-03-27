package com.spring.app.skeleton.models.vehicle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.app.skeleton.models.head.Item;
import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.Tracer;

public class Inventory extends Entity implements IInventory{

    Map<Item,Integer> items = new HashMap<>();

    public Inventory(){
        for(Item i : Item.values()) items.put(i, 0);
    }

    @Override
    public boolean removeItem(IInventoryItem i, int amount) {
        Tracer.getInstance().enterFunction(this, "removeItem",i,amount);
        int available = Tracer.getInstance().askInt("Mennyi van belőle? "+i.key());
        if(available >= amount){
            items.put(i.key(), available-amount);
            Tracer.getInstance().exitFunction(true);
            return true;
        }
        Tracer.getInstance().exitFunction(false);
        return false;
    }

    @Override
    public void addItem(IInventoryItem i, int amount) {
        Tracer.getInstance().enterFunction(this, "addItem",i,amount);
        items.put(i.key(), items.get(i.key())+amount);
        Tracer.getInstance().exitFunction();
    }

    @Override
    public List<String> init() {
        return List.of("items: "+items.toString());
    }
    
}
