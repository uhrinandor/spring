package com.spring.models.shop;

/**
 * Ez az interfész felel a boltból történő vásárlás lebonyolításáért.
 * Innen tudnak vásárolni a játékosok.
 */
public interface IShop {
    /**
     * Ez a metódus felel a boltból történő vásárlásért, paraméterként egy PurchaseContext-et,
     * egy ShopItem-et és egy amount-ot kap amik meghatározzák,
     * hogy ki mit és mennyit akar vásárolni. Ez a függvény kezeli a játékosok pénzügyeit is.
     * @param ctx a vásárlási kontextus, amire a vásárlás vonatkozik
     * @param item a megvásárolni kívánt item
     * @param amount a megvásárolni kívánt item mennyisége
     */
    public void buy(PurchaseContext ctx, ShopItem item, int amount);
}
