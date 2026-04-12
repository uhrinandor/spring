package com.spring.models.buildings;

import com.spring.models.field.IField;
import com.spring.models.vehicle.Car;

/**
 * A játék kezdetekor minden autó egy Home objektum mellett fog megjelenni, a Home hozza 
 * őket létre. Ha a játék során szükség lesz új autóra akkor az is egy ilyen Home mellett fog 
 * megjelenni.
 */
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
