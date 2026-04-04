package com.spring.models.shop;

import java.util.List;

import com.spring.models.utils.Entity;
import com.spring.models.utils.Tracer;

public class Shop extends Entity implements IShop {
    /**
     * Megvásárolunk egy adott kontextusra egy item-et valamilyen mennyiségben
     * @param ctx a vásárlási kontextus, amire a vásárlás vonatkozik
     * @param item a megvásárolni kívánt item
     * @param amount a megvásárolni kívánt item mennyisége
     */
    public Shop(){};

    @Override
    public void buy(PurchaseContext ctx, ShopItem item, int amount) {
        Tracer.getInstance().enterFunction(this, "buy",ctx,item,amount);
        int price = item.price();

        if(ctx.charge(price)) item.apply(ctx, amount);
        Tracer.getInstance().exitFunction();
    }

    @Override
    public List<String> init() {
        return List.of();
    }
    
}
