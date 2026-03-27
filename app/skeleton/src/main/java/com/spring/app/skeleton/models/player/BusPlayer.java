package com.spring.app.skeleton.models.player;

import java.util.List;

import com.spring.app.skeleton.models.vehicle.Bus;
import com.spring.app.skeleton.models.vehicle.Vehicle;
import com.spring.app.skeleton.utils.Entity;

public class BusPlayer extends Entity implements IPlayer{
    private Bus bus;
    private int point;

    

    @Override
    public List<String> init() {
        return List.of("bus: " + bus + ", point: " + getPoints());
    }

    @Override
    public List<Vehicle> vehicles() {
        return List.of(bus);
    }

    @Override
    public int getPoints() {
        return point/2;
    }

    @Override
    public void give(int amount) {
        point += amount;
    }

}
