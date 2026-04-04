package com.spring.models.buildings;

import com.spring.models.field.IField;
import com.spring.models.vehicle.Car;
import com.spring.models.utils.Tracer;

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
        Tracer.getInstance().enterFunction(this, "consume",c);
        field.setVehicle(null);
        Tracer.getInstance().exitFunction();
    }
}
