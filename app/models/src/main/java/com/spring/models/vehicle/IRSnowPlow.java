package com.spring.models.vehicle;

import com.spring.models.head.IHead;
import com.spring.models.utils.IEntity;

public interface IRSnowPlow extends IEntity{
    /**
     * @return visszaadja a hókotró Inventory-ját
     */
    public IInventory getInventory();

    public IHead getHead();
}
