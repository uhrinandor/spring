package com.spring.app.skeleton.models.head;

import java.util.List;

import com.spring.app.skeleton.models.layer.ILayerVisitor;
import com.spring.app.skeleton.models.layer.Ice;
import com.spring.app.skeleton.models.layer.Layer;
import com.spring.app.skeleton.models.layer.Snow;
import com.spring.app.skeleton.utils.Entity;

public class BroomClearLayerVisitor extends Entity implements ILayerVisitor{
    private boolean result;

    public boolean getResult(){
        return result;
    }

    @Override
    public void visit(Snow s) {
        result=true;
    }

    @Override
    public void visit(Ice i) {
        result = i.getBroken();
    }

    @Override
    public void visit(Layer l) {
        result = true;
    }

    @Override
    public List<String> init() {
        return List.of();
    }

}
