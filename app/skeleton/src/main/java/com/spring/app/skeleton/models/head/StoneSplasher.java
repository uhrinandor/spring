package com.spring.app.skeleton.models.head;

import java.util.List;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.models.layer.Stone;
import com.spring.app.skeleton.models.shop.PurchaseContext;
import com.spring.app.skeleton.models.shop.ShopItem;
import com.spring.app.skeleton.models.vehicle.IInventory;
import com.spring.app.skeleton.models.vehicle.ISnowPlow;
import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.Tracer;

public class StoneSplasher extends Entity implements IHead, ShopItem{
    
    /**
     * Ha van az inventory-ban kavics akkor lerakja
     * @return false minden esetben, a lerakásért nem jár pont
     */
    @Override
    public boolean interact(IField field, IInventory inventory) {
        Tracer.getInstance().enterFunction(this, "interact",field,inventory);
        Stone stone = new Stone();
        Tracer.getInstance().newObject(stone);
         if (!inventory.removeItem(stone, 1)){
            Tracer.getInstance().exitFunction(false);
            return false;
        } 
        stone.setPrevious(field.getLayer());
        field.setLayer(stone);
        Tracer.getInstance().exitFunction(true);
        return true;
    }

    @Override
    public List<String> init() {
        return List.of();
    }

    /**
     * @return Visszaadja az nevét.
     */
    @Override
    public Item key() {
        return Item.STONESPLASHER;
    }

    /**
     * @return Visszaadja egy megvásárolható StoneSplasher árát.
     */
    @Override
    public int price() {
        //TODO 
        return Tracer.getInstance().askInt("Mennyibe kerül a StoneSplasher?");
    }

    /**
     * Alkalmazza a vásárlást.
     * Jelzi az aktív hókotró inventory-jának, hogy adjon hozzá egy elemet a típusából.
     * @param ctx visszaadja azt a hókotrót, amire történik a vásárlás
     * @param amount a megvásárolni kívánt StoneSplasher-ök száma
     */
    @Override
    public void apply(PurchaseContext ctx, int amount) {
        ISnowPlow sp = ctx.snowPlow();
        IInventory inventory = sp.getInventory();
        inventory.addItem(this, amount);
    }
}
