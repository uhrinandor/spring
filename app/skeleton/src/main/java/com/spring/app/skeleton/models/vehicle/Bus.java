package com.spring.app.skeleton.models.vehicle;

import java.util.List;

import com.spring.app.skeleton.models.buildings.Station;
import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.models.player.ICollector;
import com.spring.app.skeleton.utils.Tracer;

/**
 * Célja minél több kört megtenni a két végállomása között.
 */
public class Bus extends Vehicle {
    /**
     * A végállomás, ami fele tart.
     */
    Station station;

    /**
     * Számláló, azt számolja, hogy ütközés után még hány körön keresztül mozgásképtelen a busz.
     */
    int immobileTurnsLeft = 0; 

    /**
     * Neki tudja átadni a pontszámait.
     */
    ICollector collector;

    public Bus(IDriver driver, Station station, ICollector collector) {
        super(driver);
        this.station = station;
        this.collector = collector;
    }

    @Override
    public List<String> init() {
        return List.of("driver: "+driver, "station: " + station);
    }

    /**
     * Megadja, hogy a busz képes-e megmozdulni.
     */
    @Override
    boolean canMove() {
        return Tracer.getInstance().askInt("Mennyi ideig van lerobbanva a busz?") == 0;
    }

    /**
     * Összecsattan egy másik járművel, 3 körig nem tud mozogni
     * @param v a másik jármű
     */
    @Override
    public void contact(Vehicle v) {
        immobileTurnsLeft = 3;
    }

    /**
     * Ha a busz célba ér, akkor a pályán lévő állomás párját veszi igénybe.
     * Adunk egy pontot a gyűjtőnek.
     */
    @Override
    public void interact(IField f) {
        if(station.getField() != f) return;

        Station pair = station.getPair();
        station = pair;

        collector.give(1);
    }
    
    /**
     * Lehetővé teszi a Visitor számára, hogy műveletet hajtson végre
     * ezen a buszon. (IceSlipVisitor, SnowExitVisitor)
     * @param visitor akinek a busz engedélyezi a visit műveletet.
     */
    @Override
    public void accept(IVehicleVisitor visitor) {
        visitor.visit(this);
    }


    public void setStation(Station station)
    {
        this.station = station;
    }
}
