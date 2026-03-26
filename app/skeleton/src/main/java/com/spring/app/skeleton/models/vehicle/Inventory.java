package com.spring.app.skeleton.models.vehicle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.app.skeleton.models.head.Item;
import com.spring.app.skeleton.utils.Entity;

public class Inventory extends Entity implements IInventory{

    Map<Item,Integer> items = new HashMap<>();

    public Inventory(){
        for(Item i : Item.values()) items.put(i, 0);
    }

    @Override
    public boolean removeItem(IInventoryItem i, int amount) {
        if(items.get(i.key()) >= amount){
            items.put(i.key(), items.get(i.key())-amount);
            return true;
        }
        return false;
    }

    @Override
    public void addItem(IInventoryItem i, int amount) {
        items.put(i.key(), items.get(i.key())+amount);
    }

    @Override
    public List<String> init() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'init'");
    }
    
}
