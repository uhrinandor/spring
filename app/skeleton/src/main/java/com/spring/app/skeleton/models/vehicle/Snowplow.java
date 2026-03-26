package com.spring.app.skeleton.models.vehicle;

import java.util.List;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.models.head.IHead;

public class Snowplow extends Vehicle implements ISnowPlow{

    public Snowplow(IDriver driver) {
        super(driver);
        //TODO Auto-generated constructor stub
    }

    @Override
    public boolean switchHead(IHead head) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'switchHead'");
    }

    @Override
    public List<String> init() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'init'");
    }

    @Override
    boolean canMove() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canMove'");
    }

    @Override
    public void contact(Vehicle v) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contact'");
    }

    @Override
    public  void interact(IField f) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'interact'");
    }
    

    @Override
    public void accept(IVehicleVisitor visitor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'accept'");
    }
    
}
