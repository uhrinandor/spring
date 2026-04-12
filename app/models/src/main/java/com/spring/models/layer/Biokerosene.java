package com.spring.models.layer;

import java.util.List;

import com.spring.models.head.Item;
import com.spring.models.shop.PurchaseContext;
import com.spring.models.shop.ShopItem;
import com.spring.models.vehicle.IInventory;
import com.spring.models.vehicle.IInventoryItem;
import com.spring.models.vehicle.ISnowPlow;
import com.spring.models.utils.Entity;
import com.spring.models.utils.Tracer;

/**
 * A biokerozin a Dragon fej működéséhez szükséges üzemanyag. Használat közben fogy.
 * A hókotró a boltból veszi és az IInventory-jában tartja.
 */
public class Biokerosene extends Entity implements IInventoryItem, ShopItem{

    /**
     * @return Visszaadja egy megvásárolható Biokerosene árát.
     */
    @Override
    public int price() {
        return Tracer.getInstance().askInt("Mennyibe kerül?", 10);
    }

    /**
     * Alkalmazza a vásárlást.
     * Jelzi az aktív hókotró inventory-jának, hogy adjon hozzá egy elemet a típusából.
     */
    @Override
    public void apply(PurchaseContext ctx, int amount) {
        ISnowPlow sp = ctx.snowPlow();
        IInventory inv = sp.getInventory();
        inv.addItem(this, amount);
    }

    /**
     * @return Visszaadja az nevét.
     */
    @Override
    public Item key() {
        return Item.BIOKEROSENE;
    }

    @Override
    public List<String> init() {
        return List.of();
    }

    @Override
    public int limit() {
        return -1;
    }

}
