package com.spring.app.skeleton.models.vehicle;

import java.util.List;

import com.spring.app.skeleton.models.buildings.Office;
import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.utils.Tracer;

public class Car extends Vehicle {
    Office destination;
    int immobileTurnsLeft = 0;

    public Car(IDriver driver, Office destination) {
        super(driver);
        this.destination = destination;
    }

    @Override
    public List<String> init() {
       return List.of("driver: " + driver, "destination: " + destination);  
    }

    /**
     * Megadja, hogy az autó képes-e megmozdulni
     */
    @Override
    boolean canMove() {
        return Tracer.getInstance().askInt("Mennyi ideig van lerobbanva az autó?") == 0;
    }

    @Override
    public void contact(Vehicle v) {
        immobileTurnsLeft = 3;
    }

    /**
     * Ha célba ér, akkor a célállomás leszedi a pályáról.
     */
    @Override
    public void interact(IField f) {
        if(destination.getField() != f) return;

        destination.consume(this);
    }
    

    @Override
    public void accept(IVehicleVisitor visitor) {
        visitor.visit(this);
    }

    public void setDestination(Office destination  )
    {
        this.destination = destination;
    }
}
