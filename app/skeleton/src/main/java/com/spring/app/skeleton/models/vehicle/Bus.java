package com.spring.app.skeleton.models.vehicle;

import java.util.List;

import com.spring.app.skeleton.models.buildings.Station;
import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.models.player.ICollector;
import com.spring.app.skeleton.utils.Tracer;

public class Bus extends Vehicle {
    Station station;
    int immobileTurnsLeft = 0; 
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
        Tracer.getInstance().enterFunction(this, "canMove");
        boolean tmp = Tracer.getInstance().askInt("Mennyi ideig van lerobbanva a busz?") == 0;
        Tracer.getInstance().exitFunction(tmp);
        return tmp;
    }

    /**
     * Összecsattan egy másik járművel, 3 körig nem tud mozogni
     * @param v a másik jármű
     */
    @Override
    public void contact(Vehicle v) {
        Tracer.getInstance().enterFunction(this, "contact",v);
        immobileTurnsLeft = 3;
        Tracer.getInstance().exitFunction();
    }

    /**
     * Ha a busz célba ér, akkor a pályán lévő állomás párját veszi igénybe.
     * Adunk egy pontot a gyűjtőnek.
     */
    @Override
    public void interact(IField f) {
        Tracer.getInstance().enterFunction(this, "interact",f);
        if(station.getField() != f){
            Tracer.getInstance().exitFunction();
            return;
        } 
        
        Station pair = station.getPair();
        station = pair;

        collector.give(1);
        Tracer.getInstance().exitFunction();
    }
    

    @Override
    public void accept(IVehicleVisitor visitor) {
        Tracer.getInstance().enterFunction(this, "accept",visitor);
        visitor.visit(this);
        Tracer.getInstance().exitFunction();
    }


    public void setStation(Station station)
    {
        this.station = station;
    }
}
