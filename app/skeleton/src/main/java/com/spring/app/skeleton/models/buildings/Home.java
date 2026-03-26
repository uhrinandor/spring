package com.spring.app.skeleton.models.buildings;

import java.util.List;

public class Home extends Building{

    public Home(IField field)
    {
        this.field=field;
    }
    
    public void generateCar(Office destination)
    {
        Car c=new Car();
        c.setDestination(destination);
        getField().setVehicle(c);
    }


    @Override
    public List<String> init() {
       return List.of("field: " + field);
    }

}
