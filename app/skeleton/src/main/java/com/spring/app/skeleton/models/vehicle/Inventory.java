package com.spring.app.skeleton.models.vehicle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.app.skeleton.models.head.Item;
import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.Tracer;

/**
 * Az Inventory egy tároló, amelyben mindent tárolni tudunk, ami implementálja az 
 * InventoryItem interfészt, valamint kivenni belőle.
 */
public class Inventory extends Entity implements IInventory{

    /**
     * itt tároljuk kulcs szerint a tárolt mennyiséget a különböző elemekből
     */
    Map<Item,Integer> items = new HashMap<>();

    public Inventory(){
        for(Item i : Item.values()) items.put(i, 0);
    }

    /**
     * Ez a metódus az IInventoryItem típusú elemek inventory-ból történő kivételéért felel.
     * @param i az adott elem, amiből az adott mennyiséget veszi ki, ha lehetséges
     * @param amount a megadott mennyiség
     * @return A művelet sikerességéről egy bool értékkel tér vissza.
     */
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

    /**
     * Ez a metódus az IInventoryItem-ek Inventory-hoz adásáért felel.
     * @param i az adott elem, amiből az adott mennyiséget teszi be az Inventory-ba
     * @param amount a megadott mennyiség
     */
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
