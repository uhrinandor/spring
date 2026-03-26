package com.spring.app.skeleton.models.buildings;

import java.util.List;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.models.vehicle.Car;

public class Office extends Building{

    public Office(IField field)
    {
        super(field);
    }
    
    public void consume(Car c)
    {
        c=null;
        getField().setVehicle(null);
    }


    @Override
    public List<String> init() {
       return List.of("field: " + field);
    }

}
