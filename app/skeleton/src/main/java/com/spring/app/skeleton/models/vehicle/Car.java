package com.spring.app.skeleton.models.vehicle;

import java.util.List;

import com.spring.app.skeleton.models.buildings.Office;
import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.utils.Tracer;

/**
 * Az autó a pálya által irányított objektum amely egy random Home mellett generálódik, célja, 
 * hogy elérje a hozzá tartozó Office-t.
 */
public class Car extends Vehicle {
    /**
     * Az autó célja.
     */
    Office destination;

    /**
     * Számláló, azt számolja, hogy ütközés után még hány körön keresztül mozgásképtelen az autó.
     */
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

    /**
     * Ez a metódus felel az autó összetöréséért. Beállítja a számlálót.
     * @param v a másik jármű, amivel ez az autó ütközik.
     */
    @Override
    public void contact(Vehicle v) {
        //TODO: ez itt biztos hogy 3?
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
    
    /**
     * Lehetővé teszi a Visitor számára, hogy műveletet hajtson végre
     * ezen az autón. (IceSlipVisitor, SnowExitVisitor)
     * @param visitor akinek a autó engedélyezi a visit műveletet.
     */
    @Override
    public void accept(IVehicleVisitor visitor) {
        visitor.visit(this);
    }

    public void setDestination(Office destination  )
    {
        this.destination = destination;
    }
}
