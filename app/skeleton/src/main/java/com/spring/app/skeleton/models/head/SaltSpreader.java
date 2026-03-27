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
        Salt salt = new Salt();
        if(inventory.removeItem(salt, 1))
            field.setSalt(salt);

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
        return Tracer.getInstance().askInt("Mennyibe kerül a SaltSpreader?");
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

}
