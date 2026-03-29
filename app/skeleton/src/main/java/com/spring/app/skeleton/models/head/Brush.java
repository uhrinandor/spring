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
 * A hókotróra szerelve a havat az adott mező jobb oldali szomszédjára tudja söpörni,
 * ha van neki szomszédja, ha nincs, eltünteti a havat a pályáról.
 */
public class Brush extends Entity implements IHead, ShopItem{

    /**
     * Ez a metódus felel a hókotró és a mezőn lévő réteg interakciójának kezeléséért.
     * Rétegtől függően más-más interakció történik.
     * @param field amit le kéne takarítani, amin áll a hókotró.
     * @return ha a visitor szerint nem takaríthatja le a mezőt, hamissal tér vissza,
     * ha letakarította igazzal
     */
    @Override
    public boolean interact(IField field, IInventory inventory) {
        Tracer.getInstance().enterFunction(this, "interact",field,inventory);
        ILayer current = field.getLayer();
        BrushClearLayerVisitor visitor = new BrushClearLayerVisitor();
        Tracer.getInstance().newObject(visitor);
        current.accept(visitor);

        if(!visitor.getResult()){
            Tracer.getInstance().exitFunction(false);
            return false;
        } 

        field.setLayer(new Layer());
        IField right = field.getRight();
        ILayer rightLayer = right.getLayer();
        rightLayer = rightLayer.merge(current);
        right.setLayer(rightLayer);
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
        return Item.BRUSH;
    }

    /**
     * @return Visszaadja egy megvásárolható Brush árát.
     */
    @Override
    public int price() {
        return Tracer.getInstance().askInt("Mennyibe kerül a Brush?");
    }

    /**
     * Alkalmazza a vásárlást.
     * Jelzi az aktív hókotró inventory-jának, hogy adjon hozzá egy elemet a típusából.
     * @param ctx visszaadja azt a hókotrót, amire történik a vásárlás
     * @param amount a megvásárolni kívánt Brush-ok száma
     */
    @Override
    public void apply(PurchaseContext ctx, int amount) {
        ISnowPlow sp = ctx.snowPlow();
        IInventory inventory = sp.getInventory();
        inventory.addItem(this, amount);
    }
    
}
