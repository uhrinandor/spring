package com.spring.app.skeleton.models.buildings;

import java.util.List;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.models.vehicle.Car;

public class Home extends Building{

    public Home(IField field)
    {
        super(field);
    }

    //TODO: Ezt most nem hasznaljuk és változtatasra szorul
    public void generateCar(Office destination)
    {
        Car c=new Car(null);
        c.setDestination(destination);
        getField().setVehicle(c);
    }


    @Override
    public List<String> init() {
       return List.of("field: " + field);
    }

}
