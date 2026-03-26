package com.spring.app.skeleton.models.head;

import java.util.List;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.models.vehicle.IInventory;
import com.spring.app.skeleton.utils.Entity;

public class Broom extends Entity implements IHead{
    
    @Override
    public List<String> init() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean interact(IField field, IInventory inventory) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'interact'");
    }
}
