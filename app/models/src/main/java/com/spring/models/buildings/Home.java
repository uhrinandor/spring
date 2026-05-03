package com.spring.models.buildings;

import com.spring.models.field.IField;
import com.spring.models.vehicle.Car;
import com.spring.models.vehicle.CarDriver;

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

    public void generateCar(Office destination)
    {
        
        CarDriver driver = new CarDriver();
        
        driver.setCurrent(field);

        driver.setDestination(destination);
        Car c = new Car(driver, destination);
        c.setDestination(destination);
        getField().setVehicle(c);
    }
}
