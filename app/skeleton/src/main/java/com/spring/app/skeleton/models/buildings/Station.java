package com.spring.app.skeleton.models.buildings;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.models.vehicle.Bus;

/**
 * A játék kezdetekor a buszok legenerálásáért felel, ilyenkor a buszhoz rendelt két végállomás 
 * egyikén megjelenik egy busz. A buszok ezen két végállomás között ingáznak, ezzel szerzik a 
 * pontjaikat.
 */
public class Station extends Building{

    /**
     * A megálló párja. (Ami és eközött a busz ingázik.)
     */
    private Station pair;

    public Station(IField field)
    {
        super(field);
    }

    public Station getPair()
    {
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
