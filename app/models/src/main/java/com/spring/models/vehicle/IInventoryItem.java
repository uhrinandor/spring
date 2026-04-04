package com.spring.models.vehicle;

import com.spring.models.head.Item;
import com.spring.models.utils.IEntity;

/**
 * Ezen interfészt megvalósító elemeket be tudjuk helyezni az Inventory-ba.
 */
public interface IInventoryItem extends IEntity {
    /**
     * @return visszaad egy enumot, ami az IInventoryItem neve.
    */
    public Item key();
}
