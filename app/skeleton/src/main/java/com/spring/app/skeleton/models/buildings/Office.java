package com.spring.app.skeleton.models.buildings;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.models.vehicle.Car;

public class Office extends Building{

    public Office(IField field)
    {
        super(field);
    }
    
    /**
     * A kocsit leszedi a pályáról, csak arról tudja, amelyik melletti field-en van.
     * @param c
     */
    public void consume(Car c)
    {
        field.setVehicle(null);
    }
}
