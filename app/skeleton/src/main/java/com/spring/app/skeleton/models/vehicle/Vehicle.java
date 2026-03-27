package com.spring.app.skeleton.models.vehicle;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.utils.Entity;

/**
 * Absztrakt jármű ősosztály.
 * Megteszi a vezető által megadott lépéseket, belső működését menedzseli.
 */
public abstract class Vehicle extends Entity {
    /**
     * A jármű vezetője.
     */
    IDriver driver;

    public Vehicle(IDriver driver){
        this.driver = driver;
    }

    /**
     * Megadja, hogy az autó képes-e megmozdulni
     */
    abstract boolean canMove();

    /**
     * Ez a metódus felel a Vehicle összetöréséért. Hókotró nem törhet össze.
     * @param v a másik jármű, amivel ez a jármű ütközik
     */
    public abstract void contact(Vehicle v);

    /**
     * Ez a függvény végzi a játékos vagy autó lépését. Sima lépés, nincs megcsúszás, ezért false.
     */
    public void step(){
        step(false);
    }

    /**
     * Ez a függvény végzi a Vehicle csúszás miatt történő kötelező 
     * csúszását.
     * @param forced true esetén a jármű kötelezően előre lép (getNext), false esetén a driver
     * határozza meg a következő lépést (nextMove).
     */
    public void step(boolean forced){
        if(!canMove()) return;

        IField next = forced ? driver.getNext() : driver.nextMove();
        IField current = driver.getCurrent();

        current.tryExit(next);
    }

    /**
     * A Vehicle mezővel történő interakcióját segíti.
     * @param f a mező, amivel a jármű interakcióba kerül.
     */
    public abstract void interact(IField f);

    /**
     * Lehetővé teszi a Visitor számára, hogy műveletet hajtson végre
     * ezen a járművön. (IceSlipVisitor, SnowExitVisitor)
     * @param visitor akinek a jármű engedélyezi a visit műveletet
     */
    public abstract void accept(IVehicleVisitor visitor);
  
    public IDriver getDriver(){
        return driver;
    }
}
