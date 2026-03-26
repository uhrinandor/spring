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

public class Biokerosene extends Entity implements IInventoryItem, ShopItem{

    @Override
    public int price() {
        return Tracer.getInstance().askInt("Mennyibe kerul?");
    }

    @Override
    public void apply(PurchaseContext ctx, int amount) {
        ISnowPlow sp = ctx.snowPlow();
        IInventory inv = sp.getInventory();
        inv.addItem(this, amount);
    }

    @Override
    public Item key() {
        return Item.BIOKEROSENE;
    }

    @Override
    public List<String> init() {
        return List.of();
    }

}
