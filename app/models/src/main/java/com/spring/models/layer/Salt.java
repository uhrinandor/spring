package com.spring.models.layer;

import java.util.List;

import com.spring.models.head.Item;
import com.spring.models.shop.PurchaseContext;
import com.spring.models.shop.ShopItem;
import com.spring.models.vehicle.IInventory;
import com.spring.models.vehicle.IInventoryItem;
import com.spring.models.vehicle.ISnowPlow;
import com.spring.models.utils.Entity;
import com.spring.models.utils.Tracer;

/**
 * Olvasztja a rétegeket.
 */
public class Salt extends Entity implements ShopItem, IInventoryItem, ISalt{
    /**
     * Megadja hogy a só hány körig lesz még érvényes.
     */
    private int timer;

    public Salt() {
        this.timer = 3;
    }

    public Salt(int timer){
        this.timer = timer;
    }

    public int getTimer(){
        return timer;
    }

    public void setTimer(int timer){
        this.timer = timer;
    }

    /**
     * Ez a függvény végzi a hó vagy jég olvasztását. 
     * @param Paraméternek egy ILayer-t fogad
     * @return Azzal a réteggel tér vissza ami a kapott layer-ből lesz a só olvasztása után.
     * A sónak csökken a körideje.
     */
    @Override
    public ILayer melt(ILayer layer) {
        if(Tracer.getInstance().askInt("Meddig érvényes a só?") <= 0) return layer;

        MeltLayerVisitor visitor = new MeltLayerVisitor();

        layer.accept(visitor);

        return visitor.getResult();
    }

    /**
     * @return Visszaadja az nevét.
     */
    @Override
    public Item key() {
        return Item.SALT;
    }

    /**
     * @return Visszaadja egy megvásárolható Salt árát.
     */
    @Override
    public int price() {
        return Tracer.getInstance().askInt("Mennyibe kerül a Salt?");
    }

    /**
     * Alkalmazza a vásárlást.
     * Jelzi az aktív hókotró inventory-jának, hogy adjon hozzá egy elemet a típusából.
     * @param ctx visszaadja azt a hókotrót, amire történik a vásárlás
     * @param amount a megvásárolni kívánt Salt-ok száma
     */
    @Override
    public void apply(PurchaseContext ctx, int amount) {
        ISnowPlow sp = ctx.snowPlow();
        IInventory inv = sp.getInventory();
        inv.addItem(this, amount);
    }

    @Override
    public List<String> init() {
        return List.of("timer: " + timer);
    }

}
