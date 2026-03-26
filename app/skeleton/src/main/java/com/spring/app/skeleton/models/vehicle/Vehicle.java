package com.spring.app.skeleton.models.vehicle;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.utils.Entity;

public abstract class Vehicle extends Entity {
    IDriver driver;

    public Vehicle(IDriver driver){
        this.driver = driver;
    }

    abstract boolean canMove();

    public abstract void contact(Vehicle v);

    public void step(){
        step(false);
    }

    public void step(boolean forced){
        if(!canMove()) return;

        IField next = forced ? driver.getNext() : driver.nextMove();
        IField current = driver.getCurrent();

        current.tryExit(next);
    }

    public abstract void interact(IField f);
    public abstract void accept(IVehicleVisitor visitor);
  
    public IDriver getDriver(){
        return driver;
    }
}
