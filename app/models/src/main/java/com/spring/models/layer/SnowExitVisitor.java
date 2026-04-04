package com.spring.models.layer;

import java.util.List;

import com.spring.models.vehicle.Bus;
import com.spring.models.vehicle.Car;
import com.spring.models.vehicle.IVehicleVisitor;
import com.spring.models.vehicle.Snowplow;
import com.spring.models.utils.Entity;
import com.spring.models.utils.Tracer;

/**
 * Ellenőrzi, hogy egy jármű le tud-e lépni arról a havas mezőről amin áll.
 */
public class SnowExitVisitor extends Entity implements IVehicleVisitor{
    /**
     * Az eredményt, hogy a jármű leléphet-e.
     */
    private boolean result;

    /**
     * Az alap, amiről a jármű le akar lépni.
     */
    private Snow base;

    public SnowExitVisitor(Snow base) {
        this.base = base;
        this.result = false;
    }

    public boolean getResult() {
        Tracer.getInstance().enterFunction(this, "getResult");
        Tracer.getInstance().exitFunction(result);
        return result;
    }

    /**
     * Az autó magas hóban nem tud kijutni
     */
    @Override
    public void visit(Car c) {
        Tracer.getInstance().enterFunction(this, "visit", c);
        result = Tracer.getInstance().askInt("Milyen magas a hó?") <= 1;
        Tracer.getInstance().exitFunction();
    }

    /**
     * Az hókotró minden hóból ki tud jönni
     */
    @Override
    public void visit(Snowplow s) {
        Tracer.getInstance().enterFunction(this, "visit", s);
        result = true;
        Tracer.getInstance().exitFunction();
    }

    /**
     * Az busz magas hóban nem tud kijutni
     */
    @Override
    public void visit(Bus b) {
        Tracer.getInstance().enterFunction(this, "visit", b);
        result = Tracer.getInstance().askInt("Milyen magas a hó?") <= 1;
        Tracer.getInstance().exitFunction();
    }

    @Override
    public List<String> init() {
        return List.of("result: "+result, "base: "+base);
    }
    
}
