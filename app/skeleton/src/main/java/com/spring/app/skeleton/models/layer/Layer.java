package com.spring.app.skeleton.models.layer;

import java.util.List;

import com.spring.app.skeleton.models.random.IRandom;
import com.spring.app.skeleton.models.vehicle.Vehicle;
import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.Tracer;

public class Layer extends Entity implements ILayer {

    @Override
    public ILayer merge(ILayer layer) {
        Tracer.getInstance().enterFunction(this, "merge",layer);
        Tracer.getInstance().exitFunction(layer);
        return layer;
    }

    @Override
    public void accept(ILayerVisitor visitor) {
        Tracer.getInstance().enterFunction(this, "accept",visitor);
        visitor.visit(this);
        Tracer.getInstance().exitFunction();
    }

    @Override
    public boolean slip(Vehicle v, IRandom random) {
        Tracer.getInstance().enterFunction(this, "slip",v,random);
        Tracer.getInstance().exitFunction(false);
        return false;
    }

    @Override
    public boolean canExit(Vehicle v) {
        Tracer.getInstance().enterFunction(this, "canExit",v);
        Tracer.getInstance().exitFunction(true);
        return true;
    }

    @Override
    public ILayer enter() {
        Tracer.getInstance().enterFunction(this, "enter");
        Tracer.getInstance().exitFunction(this);
        return this;
    }

    @Override
    public List<String> init() {
        return List.of();
    }
    
}
