package com.spring.app.skeleton.models.head;

import java.util.List;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.models.layer.ILayer;
import com.spring.app.skeleton.models.layer.Layer;
import com.spring.app.skeleton.models.vehicle.IInventory;
import com.spring.app.skeleton.utils.Entity;

public class Broom extends Entity implements IHead{
    
    @Override
    public List<String> init() {
        return List.of();
    }

    @Override
    public boolean interact(IField field, IInventory inventory) {
        ILayer current = field.getLayer();
        BroomClearLayerVisitor visitor = new BroomClearLayerVisitor();
        current.accept(visitor);

        if(!visitor.getResult()) return false;

        field.setLayer(new Layer());
        IField right = field.getRight();
        IField rightright = right.getRight();
        ILayer rightLayer = rightright.getLayer();
        rightLayer = rightLayer.merge(current);
        rightright.setLayer(rightLayer);
        return true;
    }

    @Override
    public Item key() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'key'");
    }
}
