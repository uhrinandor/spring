package com.spring.app.skeleton.models.layer;

import java.util.List;

import com.spring.app.skeleton.models.random.IRandom;
import com.spring.app.skeleton.models.vehicle.Vehicle;
import com.spring.app.skeleton.utils.Entity;


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
        visitor.visit(this);
    }

    @Override
    public boolean slip(Vehicle v, IRandom random) {
        return false;
    }

    @Override
    public boolean canExit(Vehicle v) {
        SnowExitVisitor visitor = new SnowExitVisitor(this);
        v.accept(visitor);
        return visitor.getResult();
    }

    @Override
    public ILayer enter() {
        if(level > 1){
            level--;
            return this;
        }
        
        return new Ice(false);
        
    }

    @Override
    public List<String> init() {
        return List.of("level: " + level);
    }

}
