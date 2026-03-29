package com.spring.app.skeleton.models.head;

import java.util.List;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.models.layer.ILayer;
import com.spring.app.skeleton.models.layer.Layer;
import com.spring.app.skeleton.models.shop.PurchaseContext;
import com.spring.app.skeleton.models.shop.ShopItem;
import com.spring.app.skeleton.models.vehicle.IInventory;
import com.spring.app.skeleton.models.vehicle.ISnowPlow;
import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.Tracer;

/**
 * A hányófej felelőssége az, hogy a hókotróra szerelve az útról a havat vagy a feltört jeget
 * a közvetlenül tőle jobbra található Fielddel szomszédos jobb oldali Field-re tolja,
 * vagy ha nincs ilyen, akkor a hó vagy feltört jég az út szélére kerül
 * ezek után a játék során tovább nem foglalkozunk velük.
 */
public class Broom extends Entity implements IHead, ShopItem{
    
    @Override
    public List<String> init() {
        return List.of();
    }

    /**
     * Ez a metódus felel a hókotró és a mezőn lévő réteg interakciójának kezeléséért.
     * A havat és a tört jeget eltakarítja kettővel jobbra lévő útra vagy ha nincs ilyen
     * akkor eltűnteti. A sima jégre nem tud hatással lenni.
     * @param field amit le kéne takarítani, amin áll a hókotró.
     * @return ha a visitor szerint nem takaríthatja le a mezőt, hamissal tér vissza,
     * ha letakarította igazzal
     */
    @Override
    public boolean interact(IField field, IInventory inventory) {
        Tracer.getInstance().enterFunction(this, "interact",field,inventory);
        ILayer current = field.getLayer();
        BroomClearLayerVisitor visitor = new BroomClearLayerVisitor();
        Tracer.getInstance().newObject(visitor);
        current.accept(visitor);

        if(!visitor.getResult()){
            Tracer.getInstance().exitFunction(false);
            return false;
        }

        field.setLayer(new Layer());
        IField right = field.getRight();
        IField rightright = right.getRight();
        ILayer rightLayer = rightright.getLayer();
        rightLayer = rightLayer.merge(current);
        rightright.setLayer(rightLayer);
        return true;
    }

    /**
     * @return Visszaadja az nevét.
     */
    @Override
    public Item key() {
        return Item.BROOM;
    }


    /**
     * @return Visszaadja egy megvásárolható Broom árát.
     */
    @Override
    public int price() {
        return Tracer.getInstance().askInt("Mennyibe kerül a Broom?");
    }

    /**
     * Alkalmazza a vásárlást.
     * Jelzi az aktív hókotró inventory-jának, hogy adjon hozzá egy elemet a típusából.
     * @param ctx visszaadja azt a hókotrót, amire történik a vásárlás
     * @param amount a megvásárolni kívánt Broom-ok száma
     */
    @Override
    public void apply(PurchaseContext ctx, int amount) {
        ISnowPlow sp = ctx.snowPlow();
        IInventory inventory = sp.getInventory();
        inventory.addItem(this, amount);
    }
}
