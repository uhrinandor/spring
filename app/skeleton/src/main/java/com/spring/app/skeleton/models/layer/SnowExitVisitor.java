package com.spring.app.skeleton.models.layer;

import java.util.List;

import com.spring.app.skeleton.models.vehicle.Bus;
import com.spring.app.skeleton.models.vehicle.Car;
import com.spring.app.skeleton.models.vehicle.IVehicleVisitor;
import com.spring.app.skeleton.models.vehicle.Snowplow;
import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.Tracer;

public class SnowExitVisitor extends Entity implements IVehicleVisitor{
    private boolean result;
    private Snow base;

    public SnowExitVisitor(Snow base) {
        this.base = base;
        this.result = false;
    }

    public boolean getResult() {
        return result;
    }

    /**
     * Az autó magas hóban nem tud kijutni
     */
    @Override
    public void visit(Car c) {
        result = Tracer.getInstance().askInt("Milyen magas a hó?") <= 1;
    }

    /**
     * Az hókotró minden hóból ki tud jönni
     */
    @Override
    public void visit(Snowplow s) {
        result = false;
    }

    /**
     * Az busz magas hóban nem tud kijutni
     */
    @Override
    public void visit(Bus b) {
        result = Tracer.getInstance().askInt("Milyen magas a hó?") <= 1;
    }

    @Override
    public List<String> init() {
        return List.of("result: "+result, "base: "+base);
    }
    
}
