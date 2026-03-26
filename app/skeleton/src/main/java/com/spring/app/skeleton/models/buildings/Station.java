package com.spring.app.skeleton.models.buildings;

import java.util.List;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.models.vehicle.Bus;

public class Station extends Building{

    private Station pair;
    public Station(IField field)
    {
        super(field);
    }

    public Station getPair()
    {
        return pair;
    }

    public void put(Bus bus)
    {
        getField().setVehicle(bus);
        bus.setStation(getField());
    }

    @Override
    public List<String> init() {
       return List.of("field: " + field);
    }

}
