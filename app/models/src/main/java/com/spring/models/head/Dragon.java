package com.spring.models.head;

import java.util.List;

import com.spring.models.field.IField;
import com.spring.models.field.IRoad;
import com.spring.models.layer.Biokerosene;
import com.spring.models.shop.PurchaseContext;
import com.spring.models.shop.ShopItem;
import com.spring.models.utils.Entity;
import com.spring.models.utils.Tracer;
import com.spring.models.vehicle.IInventory;
import com.spring.models.vehicle.ISnowPlow;

/**
 * A sárkány az úton levő havat/jeget felolvasztja.
 * Ehhez a hókotrónak biokerozint kell birtokolnia az IInventory-jában.
 */
public class Dragon extends Entity implements IHead, ShopItem{

    /**
     * Ez a metódus felel a hókotró és a mezőn lévő réteg interakciójának kezeléséért.
     * Rétegtől függően más-más interakció történik.
     * Az inventory-ból kivesz egy Biokerosene-t, ha nincs akkor nem takarít.
     * @param field a mező amivel kezdi a takarítást, amin áll.
     * További két mezőt is letakarít maga előtt.
     * @return false ha nincs Biokerosene, true ha sikerült letakarítani
     */
    @Override
    public boolean interact(IField field, IInventory inventory) {
        Tracer.getInstance().enterFunction(this, "interact",field,inventory);
        DragonClearLayerVisitor v = new DragonClearLayerVisitor();

        if (!inventory.removeItem(new Biokerosene(), 1)){
            Tracer.getInstance().exitFunction(false);
            return false;
        }
        field.getLayer().accept(v);
        field.setLayer(v.getResult());
        IRoad nextRoad = field.getFront();
        IField nextField = nextRoad.getAvailable().get(0);
        nextField.getLayer().accept(v);
        nextField.setLayer(v.getResult());

        IRoad nextnextRoad = nextField.getFront();
        IField nextnextField = nextnextRoad.getAvailable().get(0);
        nextnextField.getLayer().accept(v);
        nextnextField.setLayer(v.getResult());
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
        return Item.DRAGON;
    }

    /**
     * @return Visszaadja egy megvásárolható Dragon árát.
     */
    @Override
    public int price() {
        Tracer.getInstance().enterFunction(this, "price");
        return 4;
    }

    /**
     * Alkalmazza a vásárlást.
     * Jelzi az aktív hókotró inventory-jának, hogy adjon hozzá egy elemet a típusából.
     * @param ctx visszaadja azt a hókotrót, amire történik a vásárlás
     * @param amount a megvásárolni kívánt Dragon-ok száma
     */
    @Override
    public void apply(PurchaseContext ctx, int amount) {
        Tracer.getInstance().enterFunction(this, "apply",ctx,amount);
        ISnowPlow sp = ctx.snowPlow();
        IInventory inventory = sp.getInventory();
        inventory.addItem(this, amount);
        Tracer.getInstance().exitFunction();
    }

    @Override
    public int limit() {
        return 1;
    }

}
