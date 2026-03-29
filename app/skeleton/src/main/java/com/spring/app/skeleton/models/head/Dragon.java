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
        Tracer.getInstance().enterFunction(this, "interact",field,inventory);

        if (!inventory.removeItem(new Biokerosene(), 1)){
            Tracer.getInstance().exitFunction(false);
            return false;
        } 
        field.setLayer(new Layer());
        IRoad nextRoad = field.getFront();
        IField nextField = nextRoad.getAvailable().get(0);
        nextField.setLayer(new Layer());

        IRoad nextnextRoad = nextField.getFront();
        IField nextnextField = nextnextRoad.getAvailable().get(0);
        nextnextField.setLayer(new Layer());
        Tracer.getInstance().exitFunction(true);
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
        Tracer.getInstance().enterFunction(this, "price");
        int tmp = Tracer.getInstance().askInt("Mennyibe kerül a Dragon?");
        Tracer.getInstance().exitFunction(tmp);
        return tmp;
    }

    @Override
    public void apply(PurchaseContext ctx, int amount) {
        Tracer.getInstance().enterFunction(this, "apply",ctx,amount);
        ISnowPlow sp = ctx.snowPlow();
        IInventory inventory = sp.getInventory();
        inventory.addItem(this, amount);
        Tracer.getInstance().exitFunction();
    }

}
