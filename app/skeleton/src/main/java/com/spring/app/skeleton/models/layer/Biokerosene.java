package com.spring.app.skeleton.models.layer;

import java.util.List;

import com.spring.app.skeleton.models.head.Item;
import com.spring.app.skeleton.models.shop.PurchaseContext;
import com.spring.app.skeleton.models.shop.ShopItem;
import com.spring.app.skeleton.models.vehicle.IInventory;
import com.spring.app.skeleton.models.vehicle.IInventoryItem;
import com.spring.app.skeleton.models.vehicle.ISnowPlow;
import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.Tracer;

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
        return Tracer.getInstance().askInt("Mennyibe kerül?");
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

}
