package com.spring.app.skeleton.models.layer;

import java.util.List;

import com.spring.app.skeleton.models.head.Item;
import com.spring.app.skeleton.models.shop.PurchaseContext;
import com.spring.app.skeleton.models.shop.ShopItem;
import com.spring.app.skeleton.models.vehicle.IInventory;
import com.spring.app.skeleton.models.vehicle.IInventoryItem;
import com.spring.app.skeleton.models.vehicle.ISnowPlow;
import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.Tracer;

public class Salt extends Entity implements ShopItem, IInventoryItem, ISalt{
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

    @Override
    public ILayer melt(ILayer layer) {
        if(Tracer.getInstance().askInt("Meddig érvényes a só?") <= 0) return layer;

        MeltLayerVisitor visitor = new MeltLayerVisitor();

        layer.accept(visitor);

        return visitor.getResult();
    }

    @Override
    public Item key() {
        return Item.SALT;
    }

    @Override
    public int price() {
        return Tracer.getInstance().askInt("Mennyibe kerül a Salt?");
    }

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
