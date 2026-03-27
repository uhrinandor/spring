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
        broken = tmp;
    }

    @Override
    public ILayer merge(ILayer layer) {
        IceMergeVisitor visitor = new IceMergeVisitor(this);
        layer.accept(visitor);
        return visitor.getResult();
    }

    @Override
    public void accept(ILayerVisitor visitor) {
        visitor.visit(this);
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
        return true;
    }

    @Override
    public ILayer enter() {
        return this;
    }

    @Override
    public List<String> init() {
        return List.of("broken: " + broken);
    }
}
