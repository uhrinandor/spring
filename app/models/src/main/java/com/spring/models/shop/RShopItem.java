package com.spring.models.shop;

import com.spring.models.utils.IEntity;

public interface RShopItem extends IEntity{
     /**
     * @return Visszaadja egy megvásárolható ShopItem árát.
     */
    public int price();
}
