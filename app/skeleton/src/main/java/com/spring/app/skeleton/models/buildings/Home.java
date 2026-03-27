package com.spring.app.skeleton.models.buildings;

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
        Car c=new Car(null, null);
        c.setDestination(destination);
        getField().setVehicle(c);
    }
}
