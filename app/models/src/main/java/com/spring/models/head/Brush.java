package com.spring.models.head;

import java.util.List;

import com.spring.models.field.IField;
import com.spring.models.layer.ILayer;
import com.spring.models.layer.Layer;
import com.spring.models.shop.PurchaseContext;
import com.spring.models.shop.ShopItem;
import com.spring.models.utils.Entity;
import com.spring.models.utils.Tracer;
import com.spring.models.vehicle.IInventory;
import com.spring.models.vehicle.ISnowPlow;

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
        current.accept(visitor);

        if(!visitor.getResult()){
            Tracer.getInstance().exitFunction(false);
            return false;
        } 

        field.setLayer(new Layer());
        IField right = field.getRight();
        if(right == null) return true;
        ILayer rightLayer = right.getLayer();
        rightLayer = rightLayer.merge(current);
        right.setLayer(rightLayer);
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
        return Item.BRUSH;
    }

    /**
     * @return Visszaadja egy megvásárolható Brush árát.
     */
    @Override
    public int price() {
        return 2;
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

    @Override
    public int limit() {
        return 1;
    }
    
}
