package com.spring.models.shop;


/**
 * Az ilyen interfészt megvalósító osztályokat lehet vásárolni a boltban.
 */ 
public interface ShopItem extends RShopItem {

    /**
     * Adott kontextusra alkalmazza a vásárlást
     * @param ctx a vásárlási kontextus, amire a vásárlás vonatkozik
     * @param amount a megvásárolni kívánt item mennyisége
     */
    public void apply(PurchaseContext ctx, int amount);
}
