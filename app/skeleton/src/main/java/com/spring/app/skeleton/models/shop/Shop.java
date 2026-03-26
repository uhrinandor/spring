package com.spring.app.skeleton.models.shop;

public class Shop implements IShop {
    /**
     * Megvásárolunk egy adott kontextusra egy item-et valamilyen mennyiségben
     * @param ctx a vásárlási kontextus, amire a vásárlás vonatkozik
     * @param item a megvásárolni kívánt item
     * @param amount a megvásárolni kívánt item mennyisége
     */
    @Override
    public void buy(PurchaseContext ctx, ShopItem item, int amount) {
        int price = item.price();

        if(ctx.charge(price)) item.apply(ctx, amount);
    }
    
}
