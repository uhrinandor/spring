package com.spring.app.skeleton.models.shop;


public interface IShop {
 public void buy(PurchaseContext ctx, ShopItem item, int amount);
}
