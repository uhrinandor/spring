package com.spring.app.skeleton.models.buildings;

import java.util.List;

public class Station extends Building{

    private Station pair;
    public Station(IField field)
    {
        this.field=field;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'init'");
        
    }

}
