package com.spring.app.skeleton.models.layer;

import java.util.List;

import com.spring.app.skeleton.models.vehicle.Vehicle;
import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.IRandom;

public class Ice extends Entity implements ILayer {

    private boolean broken;

    public boolean getBroken(){
        return broken;
    }

    public void setBroken(boolean tmp){
        broken = tmp;
    }

    @Override
    public ILayer merge(ILayer layer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'merge'");
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
