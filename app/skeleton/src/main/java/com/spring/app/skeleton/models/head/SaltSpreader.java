package com.spring.app.skeleton.models.head;

import java.util.List;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.models.vehicle.IInventory;
import com.spring.app.skeleton.utils.Entity;

public class SaltSpreader extends Entity implements IHead{

    @Override
    public boolean interact(IField field, IInventory inventory) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'interact'");
    }

    @Override
    public List<String> init() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'init'");
    }

}
