package com.spring.app.skeleton.models.field;

import java.util.List;

import com.spring.app.skeleton.models.layer.ILayer;
import com.spring.app.skeleton.models.vehicle.IDriver;
import com.spring.app.skeleton.models.vehicle.Vehicle;
import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.IRandom;

public class Field extends Entity implements IField {
    ILayer layer;
    Vehicle vehicle;
    IRoad front;
    IRandom random;

    @Override
    public List<String> init() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<IField> getAvailable() {
       return List.of(this);
    }

    @Override
    public boolean isUnderGround() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isUnderGrund'");
    }

    @Override
    public boolean tryEnter(Vehicle v) {
        if(vehicle != null){
            vehicle.contact(v);
            v.contact(vehicle);
            return false;
        }

        layer = layer.enter();

        if(layer.slip(v, random)){
            IDriver driver = v.getDriver();
            List<IField> available = front.getAvailable();
            
            driver.setNext(available.get(0));
            v.step(true);
            return true;
        }

        vehicle = v;
        vehicle.interact(this);
        
        return true;   
    }

    @Override
    public boolean tryExit(IField f) {
        if(!layer.canExit(vehicle)) return false;

        if(!f.tryEnter(vehicle)) return false;
        
        vehicle = null;
        return true;
    }

    // FLAG: EZ MÁR NEM AKTUÁLIS
    @Override
    public void interact() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'interact'");
    }

    // FLAG: EZ MÁR NEM AKTUÁLIS
    @Override
    public void slip() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'slip'");
    }

    @Override
    public void melt() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'melt'");
    }
    
}
