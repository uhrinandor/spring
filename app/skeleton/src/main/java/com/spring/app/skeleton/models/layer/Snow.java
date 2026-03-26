package com.spring.app.skeleton.models.layer;

import java.util.List;

import com.spring.app.skeleton.models.vehicle.Vehicle;
import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.IRandom;

public class Snow extends Entity implements ILayer {
    private int level;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'slip'");
    }

    @Override
    public boolean canExit(Vehicle v) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canExit'");
    }

    @Override
    public ILayer enter() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'enter'");
    }

    @Override
    public List<String> init() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'init'");
    }
    
}
