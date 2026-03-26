package com.spring.app.skeleton.models.vehicle;

import com.spring.app.skeleton.utils.IEntity;

public interface IInventory extends IEntity {
    public boolean removeItem(IInventoryItem i, int amount);
    public void addItem(IInventoryItem i, int amount);
}
