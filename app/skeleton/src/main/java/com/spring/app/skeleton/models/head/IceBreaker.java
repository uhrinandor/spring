package com.spring.app.skeleton.models.head;

import java.util.List;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.models.layer.ILayer;
import com.spring.app.skeleton.models.shop.PurchaseContext;
import com.spring.app.skeleton.models.shop.ShopItem;
import com.spring.app.skeleton.models.vehicle.IInventory;
import com.spring.app.skeleton.models.vehicle.ISnowPlow;
import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.Tracer;

public class IceBreaker extends Entity implements IHead, ShopItem{

    /**
     * Összetöri a jeget, ha van eredménye, akkor sikeres volt a törés
     * A visitor már bebillenti a flaget
     * @return igaz, ha sikeres volt
     */
    @Override
    public boolean interact(IField field, IInventory inventory) {
        ILayer layer = field.getLayer();
        IceBreakerClearLayerVisitor visitor = new IceBreakerClearLayerVisitor();
        layer.accept(visitor);

        return visitor.getResult() != null;
    }

    @Override
    public List<String> init() {
        return List.of();
    }

    @Override
    public Item key() {
        return Item.ICEBREAKER;
    }

    @Override
    public int price() {
        return Tracer.getInstance().askInt("Mennyibe kerül a IceBreaker?");
    }

    @Override
    public void apply(PurchaseContext ctx, int amount) {
        ISnowPlow sp = ctx.snowPlow();
        IInventory inventory = sp.getInventory();
        inventory.addItem(this, amount);
    }
}
