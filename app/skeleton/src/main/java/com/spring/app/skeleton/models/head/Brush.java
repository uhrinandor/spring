package com.spring.app.skeleton.models.head;

import java.lang.reflect.Field;
import java.util.List;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.models.layer.ILayer;
import com.spring.app.skeleton.models.layer.Layer;
import com.spring.app.skeleton.models.vehicle.IInventory;
import com.spring.app.skeleton.utils.Entity;

public class Brush extends Entity implements IHead{

    @Override
    public boolean interact(IField field, IInventory inventory) {
        ILayer current = field.getLayer();
        BrushClearLayerVisitor visitor = new BrushClearLayerVisitor();
        current.accept(visitor);

        if(!visitor.getResult()) return false;

        field.setLayer(new Layer());
        IField right = field.getRight();
        ILayer rightLayer = right.getLayer();
        rightLayer = rightLayer.merge(current);
        right.setLayer(rightLayer);
        return true;
    }

    @Override
    public List<String> init() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'init'");
    }
    
}
