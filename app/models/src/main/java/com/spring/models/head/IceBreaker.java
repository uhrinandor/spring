package com.spring.models.head;

import java.util.List;

import com.spring.models.field.IField;
import com.spring.models.layer.ILayer;
import com.spring.models.shop.PurchaseContext;
import com.spring.models.shop.ShopItem;
import com.spring.models.utils.Entity;
import com.spring.models.utils.Tracer;
import com.spring.models.vehicle.IInventory;
import com.spring.models.vehicle.ISnowPlow;

/**
 * A jégtörőfej feladata az, hogy a hókotróra szerelve, az úton levő jeget feltöri,
 * de nem takarítja el azt.
 */
public class IceBreaker extends Entity implements IHead, ShopItem{

    /**
     * Összetöri a jeget, ha van eredménye, akkor sikeres volt a törés
     * A visitor már bebillenti a flaget
     * @return igaz, ha sikeres volt
     */
    @Override
    public boolean interact(IField field, IInventory inventory) {
        Tracer.getInstance().enterFunction(this, "getInstance",field,inventory);
        ILayer layer = field.getLayer();
        IceBreakerClearLayerVisitor visitor = new IceBreakerClearLayerVisitor();
        layer.accept(visitor);

        boolean tmp = visitor.getResult() != null;
        Tracer.getInstance().exitFunction(tmp);
        return tmp;
    }

    @Override
    public List<String> init() {
        return List.of();
    }

    /**
     * Visszaadja a nevét.
     */
    @Override
    public Item key() {
        return Item.ICEBREAKER;
    }

    /**
     * @return Visszaadja egy megvásárolható IceBreaker árát.
     */
    @Override
    public int price() {
        return 2;
    }

    /**
     * Alkalmazza a vásárlást.
     * Jelzi az aktív hókotró inventory-jának, hogy adjon hozzá egy elemet a típusából.
     * @param ctx visszaadja azt a hókotrót, amire történik a vásárlás
     * @param amount a megvásárolni kívánt IceBreaker-ek száma
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
