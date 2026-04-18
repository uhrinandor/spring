package com.spring.models.head;

import java.util.List;

import com.spring.models.field.IField;
import com.spring.models.layer.Salt;
import com.spring.models.shop.PurchaseContext;
import com.spring.models.shop.ShopItem;
import com.spring.models.vehicle.IInventory;
import com.spring.models.vehicle.ISnowPlow;
import com.spring.models.utils.Entity;
import com.spring.models.utils.Tracer;

/**
 * A sószórófej feladata az, hogy a hókotróra szerelve az útra sót szórjon és
 * ezáltal a havat/jeget megolvassza.
 * Működéséhez a hókotrónak sót kell birtokolnia az IInventory-jában.
 */
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

    /**
     * @return Visszaadja az nevét.
     */
    @Override
    public Item key() {
        return Item.SALTSPREADER;
    }

    /**
     * @return Visszaadja egy megvásárolható SaltSpreader árát.
     */
    @Override
    public int price() {
        return 2;
    }

    /**
     * Alkalmazza a vásárlást.
     * Jelzi az aktív hókotró inventory-jának, hogy adjon hozzá egy elemet a típusából.
     * @param ctx visszaadja azt a hókotrót, amire történik a vásárlás
     * @param amount a megvásárolni kívánt SaltSpreader-ök száma
     */
    @Override
    public void apply(PurchaseContext ctx, int amount) {
        ISnowPlow sp = ctx.snowPlow();
        IInventory inventory = sp.getInventory();
        inventory.addItem(this, amount);
    }

    @Override
    public int limit() {
        return 1;
    }

}
