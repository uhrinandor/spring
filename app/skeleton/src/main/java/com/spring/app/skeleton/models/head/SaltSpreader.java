package com.spring.app.skeleton.models.head;

import java.util.List;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.models.layer.Salt;
import com.spring.app.skeleton.models.vehicle.IInventory;
import com.spring.app.skeleton.utils.Entity;

public class SaltSpreader extends Entity implements IHead{

    /**
     * Ha van az inventory-ban só akkor lerakja
     * @return false minden esetben, a lerakásért nem jár pont
     */
    @Override
    public boolean interact(IField field, IInventory inventory) {
        Salt salt = new Salt();
        if(inventory.removeItem(salt, 1))
            field.setSalt(salt);

        return false;
    }

    @Override
    public List<String> init() {
        return List.of();
    }

}
