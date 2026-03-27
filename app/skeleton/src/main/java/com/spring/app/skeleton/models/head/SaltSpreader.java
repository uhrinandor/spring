package com.spring.app.skeleton.models.head;

import java.util.List;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.models.layer.Salt;
import com.spring.app.skeleton.models.shop.PurchaseContext;
import com.spring.app.skeleton.models.shop.ShopItem;
import com.spring.app.skeleton.models.vehicle.IInventory;
import com.spring.app.skeleton.models.vehicle.ISnowPlow;
import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.Tracer;

public class SaltSpreader extends Entity implements IHead, ShopItem{

    /**
     * Ha van az inventory-ban só akkor lerakja
     * @return false minden esetben, a lerakásért nem jár pont
     */
    @Override
    public boolean interact(IField field, IInventory inventory) {
        Tracer.getInstance().enterFunction(this, "interact",field,inventory);
        Salt salt = new Salt();
        Tracer.getInstance().newObject(salt);
        if(inventory.removeItem(salt, 1)){
            field.setSalt(salt);
        }
            
        Tracer.getInstance().exitFunction(false);
        return false;
    }

    @Override
    public List<String> init() {
        return List.of();
    }

    @Override
    public Item key() {
        return Item.SALTSPREADER;
    }

        @Override
    public int price() {
        return Tracer.getInstance().askInt("Mennyibe kerül a SaltSpreader?");
    }

    @Override
    public void apply(PurchaseContext ctx, int amount) {
        ISnowPlow sp = ctx.snowPlow();
        IInventory inventory = sp.getInventory();
        inventory.addItem(this, amount);
    }

}
