package com.spring.app.skeleton.models.shop;

import com.spring.app.skeleton.utils.IEntity;

public interface ShopItem extends IEntity {
    public int price();
    public void apply(PurchaseContext ctx, int amount);
}
