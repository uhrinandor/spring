package com.spring.app.skeleton.models.head;

import java.util.List;

import com.spring.app.skeleton.models.layer.ILayerVisitor;
import com.spring.app.skeleton.models.layer.Ice;
import com.spring.app.skeleton.models.layer.Layer;
import com.spring.app.skeleton.models.layer.Snow;
import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.Tracer;

public class BroomClearLayerVisitor extends Entity implements ILayerVisitor{
    private boolean result;

    public boolean getResult(){
        Tracer.getInstance().enterFunction(this, "getResult");
        Tracer.getInstance().exitFunction(result);
        return result;
    }

    @Override
    public void visit(Snow s) {
        Tracer.getInstance().enterFunction(this, "visit",s);
        result = true;
        Tracer.getInstance().exitFunction();
    }

    @Override
    public void visit(Ice i) {
        Tracer.getInstance().enterFunction(this, "visit",i);
        result = Tracer.getInstance().askBool("Torott-e a jeg?");
        Tracer.getInstance().exitFunction();
    }

    @Override
    public void visit(Layer l) {
        Tracer.getInstance().enterFunction(this, "visit",l);
        result = true;
        Tracer.getInstance().exitFunction();
    }

    @Override
    public List<String> init() {
        return List.of();
    }

}
