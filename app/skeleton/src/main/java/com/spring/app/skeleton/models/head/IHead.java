package com.spring.app.skeleton.models.head;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.utils.IEntity;
import com.spring.app.skeleton.models.vehicle.IInventory;

public interface IHead extends IEntity{
    public boolean interact(IField field, IInventory inventory);
}
