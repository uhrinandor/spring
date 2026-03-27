package com.spring.app.skeleton.models.buildings;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.models.vehicle.Car;

/**
 * Célként szolgálnak az autóknak, emellett felelősségük az autók elfogyasztása.
 */
public class Office extends Building{

    public Office(IField field)
    {
        super(field);
    }
    
    /**
     * A kocsit leszedi a pályáról, csak arról tudja, amelyik melletti field-en van.
     * @param c a kocsi amit az Office leszed a pályáról
     */
    public void consume(Car c)
    {
        field.setVehicle(null);
    }
}
