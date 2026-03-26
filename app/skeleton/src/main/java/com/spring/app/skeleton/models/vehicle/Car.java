package com.spring.app.skeleton.models.vehicle;

import java.util.List;

import com.spring.app.skeleton.models.buildings.Building;
import com.spring.app.skeleton.models.field.IField;

public class Car extends Vehicle {

    public Car(IDriver driver) {
        super(driver);
        //TODO Auto-generated constructor stub
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
    public void interact(IField f) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'interact'");
    }
    

    @Override
    public void accept(IVehicleVisitor visitor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'accept'");
    }
    public void setDestination(Building building)
    {

    }
}
