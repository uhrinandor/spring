package com.spring.app.skeleton.models.head;

import java.util.List;

import com.spring.app.skeleton.models.layer.ILayerVisitor;
import com.spring.app.skeleton.models.layer.Ice;
import com.spring.app.skeleton.models.layer.Layer;
import com.spring.app.skeleton.models.layer.Snow;
import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.Tracer;

public class IceBreakerClearLayerVisitor extends Entity implements ILayerVisitor{
    Ice result = null; 


    /**
     * Jégtörő hóval nem tud intekraktálni
     */
    @Override
    public void visit(Snow s) {}

    /**
     * Összetöri a jeget
     */
    @Override
    public void visit(Ice i) {
        Tracer.getInstance().enterFunction(this, "visit",i);
        i.setBroken(true);
        result = i;
        Tracer.getInstance().exitFunction();
    }

    /**
     * Nem tud interaktálni a réteggel
     */
    @Override
    public void visit(Layer l) {}

    public Ice getResult() {
        Tracer.getInstance().enterFunction(this, "gerResult");
        Tracer.getInstance().exitFunction(result);
        return result;
    }

    @Override
    public List<String> init() {
        return List.of();
    }
    
}
