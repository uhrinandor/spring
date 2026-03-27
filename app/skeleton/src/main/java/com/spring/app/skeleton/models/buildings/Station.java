package com.spring.app.skeleton.models.buildings;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.models.vehicle.Bus;
import com.spring.app.skeleton.utils.Tracer;

public class Station extends Building{

    private Station pair;
    public Station(IField field)
    {
        super(field);
    }

    public Station getPair()
    {
        Tracer.getInstance().enterFunction(this, "getPair");
        Tracer.getInstance().exitFunction(pair);
        return pair;
    }

    /**
     * Lerakja a megadott buszt maga mellé
     * @param bus A busz, amit le akarunk rakni
     */
    public void put(Bus bus)
    {
        bus.getDriver().setCurrent(field);
        field.setVehicle(bus);
        
    }
}
