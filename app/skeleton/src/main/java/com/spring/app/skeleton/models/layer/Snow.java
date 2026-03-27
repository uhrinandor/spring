package com.spring.app.skeleton.models.layer;

import java.util.List;

import com.spring.app.skeleton.models.random.IRandom;
import com.spring.app.skeleton.models.vehicle.Vehicle;
import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.Tracer;


public class Snow extends Entity implements ILayer {
    private int level;

    public Snow(int level){
        this.level = level;
    }

    public int getLevel(){
        return level;
    }

    public void setLevel(int l){
        level = l;
    }

    @Override
    public ILayer merge(ILayer layer) {
        SnowMergeVisitor visitor = new SnowMergeVisitor(this);
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
        Tracer.getInstance().enterFunction(this, "slip",v, random);
        Tracer.getInstance().exitFunction(false);
        return false;
    }

    @Override
    public boolean canExit(Vehicle v) {
        Tracer.getInstance().enterFunction(this, "canExit",v);
        SnowExitVisitor visitor = new SnowExitVisitor(this);
        Tracer.getInstance().newObject(visitor);
        v.accept(visitor);
        Tracer.getInstance().exitFunction(visitor.getResult());
        return visitor.getResult();
    }

    @Override
    public ILayer enter() {
        Tracer.getInstance().enterFunction(this, "enter");

        if(Tracer.getInstance().askInt("Milyen magas a hó?") > 1){
            level--;
            Tracer.getInstance().exitFunction(this);
            return this;
        }
        Ice tmp = new Ice();
        Tracer.getInstance().newObject(tmp);
        Tracer.getInstance().exitFunction();
        return tmp;
        
    }

    @Override
    public List<String> init() {
        return List.of("level: " + level);
    }

}
