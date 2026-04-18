package com.spring.models.vehicle;

import java.util.Map;

import com.spring.models.head.Item;
import com.spring.models.utils.IEntity;

/**
 * Ez az interfész felel az IInventoryItem-ek tárolásáért. Lehet elemet betenni és kivenni.
 */
public interface IInventory extends IEntity {
    /**
     * Ez a metódus azIInventoryItem típusú elemek inventory-ból történő kivételéért felel.
     * @param i a megadott Item amit ki kell venni az Inventory-ból
     * @param amount a megadott mennyiség amennyit ki kell venni az Inventory-ból a megadott Item-ből
     * @return A művelet sikerességéről egy bool értékkel tér vissza.
     */
    public boolean removeItem(IInventoryItem i, int amount);

    /**
     * Ez a metódus az IInventoryItem-ek inventory-hoz adásáért felel.
     * @param i a megadott Item, amit be kell tenni az Inventory-ba
     * @param amount a megadott mennyiség amennyit be kell tenni az Inventory-ba
     */
    public boolean addItem(IInventoryItem i, int amount);

    public Map<Item,Integer> getMap();
}
