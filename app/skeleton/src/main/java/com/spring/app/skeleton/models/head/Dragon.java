package com.spring.app.skeleton.models.head;

import java.util.List;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.models.field.IRoad;
import com.spring.app.skeleton.models.layer.Biokerosene;
import com.spring.app.skeleton.models.layer.Layer;
import com.spring.app.skeleton.models.shop.PurchaseContext;
import com.spring.app.skeleton.models.shop.ShopItem;
import com.spring.app.skeleton.models.vehicle.IInventory;
import com.spring.app.skeleton.models.vehicle.ISnowPlow;
import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.Tracer;

public class Dragon extends Entity implements IHead, ShopItem{

    @Override
    public boolean interact(IField field, IInventory inventory) {
        if (!inventory.removeItem(new Biokerosene(), 1)) return false;
        field.setLayer(new Layer());
        IRoad nextRoad = field.getFront();
        IField nextField = nextRoad.getAvailable().get(0);
        nextField.setLayer(new Layer());
        IRoad nextnextRoad = nextField.getFront();
        IField nextnextField = nextnextRoad.getAvailable().get(0);
        nextnextField.setLayer(new Layer());
        return true;
    }

    @Override
    public List<String> init() {
        return List.of();
    }

    @Override
    public Item key() {
        return Item.DRAGON;
    }


    @Override
    public int price() {
        return Tracer.getInstance().askInt("Mennyibe kerül a Dragon?");
    }

    @Override
    public void apply(PurchaseContext ctx, int amount) {
        ISnowPlow sp = ctx.snowPlow();
        IInventory inventory = sp.getInventory();
        inventory.addItem(this, amount);
    }

}
