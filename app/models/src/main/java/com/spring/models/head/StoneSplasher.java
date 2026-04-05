package com.spring.models.head;

import java.util.List;

import com.spring.models.field.IField;
import com.spring.models.layer.Stone;
import com.spring.models.shop.PurchaseContext;
import com.spring.models.shop.ShopItem;
import com.spring.models.vehicle.IInventory;
import com.spring.models.vehicle.ISnowPlow;
import com.spring.models.utils.Entity;
import com.spring.models.utils.Tracer;

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
        return Tracer.getInstance().askInt("Mennyibe kerül a StoneSplasher?", 10);
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

    @Override
    public int limit() {
        return -1;
    }
}
