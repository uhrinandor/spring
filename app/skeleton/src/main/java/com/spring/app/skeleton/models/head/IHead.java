package com.spring.app.skeleton.models.head;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.models.vehicle.IInventory;
import com.spring.app.skeleton.models.vehicle.IInventoryItem;

public interface IHead extends IInventoryItem {
    public boolean interact(IField field, IInventory inventory);
}
