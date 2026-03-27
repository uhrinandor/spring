package com.spring.app.skeleton.models.vehicle;

import com.spring.app.skeleton.models.head.Item;
import com.spring.app.skeleton.utils.IEntity;

/**
 * Ezen interfészt megvalósító elemeket be tudjuk helyezni az Inventory-ba.
 */
public interface IInventoryItem extends IEntity {
    /**
     * @return visszaad egy enumot, ami az IInventoryItem neve.
    */
    public Item key();
}
