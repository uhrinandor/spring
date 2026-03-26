package com.spring.app.skeleton.models.buildings;

import java.util.List;

public class Office extends Building{

    public Office(IField field)
    {
        this.field=field;
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
