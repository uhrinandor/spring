package com.spring.app.skeleton.models.vehicle;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.utils.IEntity;

public interface IHead extends IEntity{
    public boolean interact(IField field, IInventory inventory);
}
