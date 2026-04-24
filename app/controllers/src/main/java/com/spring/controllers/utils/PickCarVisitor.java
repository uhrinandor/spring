package com.spring.controllers.utils;

import java.util.List;

import com.spring.models.utils.Entity;
import com.spring.models.vehicle.Bus;
import com.spring.models.vehicle.Car;
import com.spring.models.vehicle.IVehicleVisitor;
import com.spring.models.vehicle.Snowplow;

public class PickCarVisitor extends Entity implements IVehicleVisitor{
    Car picked;

    @Override
    public void visit(Car c) {
        picked = c;
    }

    @Override
    public void visit(Snowplow s) {
    }

    @Override
    public void visit(Bus b) {
    }

    public Car getPicked() {
        return picked;
    }

    @Override
    public List<String> init() {
        return List.of("picked: "+picked);
    }
    
}
