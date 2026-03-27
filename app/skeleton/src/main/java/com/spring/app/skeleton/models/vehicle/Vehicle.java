package com.spring.app.skeleton.models.vehicle;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.Tracer;

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
        Tracer.getInstance().enterFunction(this, "step",forced);
        
        if(!canMove()){
            Tracer.getInstance().exitFunction();
            return;
        } 

        IField next = forced ? driver.getNext() : driver.nextMove();
        IField current = driver.getCurrent();
        current.tryExit(next);
        Tracer.getInstance().exitFunction();
    }

    public abstract void interact(IField f);
    public abstract void accept(IVehicleVisitor visitor);
  
    public IDriver getDriver(){
        Tracer.getInstance().enterFunction(this, "getDriver");
        Tracer.getInstance().exitFunction(driver);
        return driver;
    }
}
