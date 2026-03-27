package com.spring.app.skeleton.models.layer;

import java.util.List;

import com.spring.app.skeleton.models.random.IRandom;
import com.spring.app.skeleton.models.vehicle.Vehicle;
import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.Tracer;

public class Ice extends Entity implements ILayer {

    private boolean broken;

    public Ice(boolean broken){
        this.broken = broken;
    }

    public Ice(){
        this.broken = false;
    }

    public boolean getBroken(){
        return broken;
    }

    public void setBroken(boolean tmp){
        Tracer.getInstance().enterFunction(this, "setBroken",tmp);
        broken = tmp;
        Tracer.getInstance().exitFunction();
    }

    @Override
    public ILayer merge(ILayer layer) {
        IceMergeVisitor visitor = new IceMergeVisitor(this);
        layer.accept(visitor);
        return visitor.getResult();
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

        IceSlipVisitor visitor = new IceSlipVisitor(random);
        Tracer.getInstance().newObject(visitor);
        
        v.accept(visitor);
        Tracer.getInstance().exitFunction(visitor.getResult());
        return visitor.getResult();
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
        return List.of("broken: " + broken);
    }
}
