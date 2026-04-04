package com.spring.models.player;

import java.util.List;

import com.spring.models.vehicle.Bus;
import com.spring.models.vehicle.Vehicle;
import com.spring.models.utils.Entity;
import com.spring.models.utils.Tracer;

/**
 * Ez az osztály felel azért, hogy megkülönböztessük melyik játékoshoz melyik busz tartozik. 
 * Gyűjti a pontokat.
 */
public class BusPlayer extends Entity implements IPlayer, ICollector{
    /**
     * A játékoshoz tartozó busz.
     */
    private Bus bus;

    /**
     * Ebben az attribútumban tárolja el, hogy hány pontot szerzett az általa 
     * vezetett buszt. A dupláját tárolja, a busz minden célállomáshoz éréskor ad neki egy 
     * pontot.
     */
    private int point;

    @Override
    public List<String> init() {
        return List.of("bus: " + bus + ", point: " + getPoints());
    }

    /**
     * @return Ez a metódus visszaadja a játékos által vezetett buszt.
     * (Azért listát ad vissza, mert a SnowPlowPlayer esetében egynél több hókotró is lehetne a listában.)
     */
    @Override
    public List<Vehicle> vehicles() {
        return List.of(bus);
    }

    /**
     * @return Ez a metódus visszaadja a játékos pontszámát. Lefelezi a tárolt pontok 
     * számát, mivel a busz minden célállomáshoz éréskor ad neki egy pontot.
     */
    @Override
    public int getPoints() {
        return point/2;
    }

    /**
     * Növeli a játékos pontszámát a megadott mennyiséggel.
     * @param amount a mennyiség amivel növelni kell a pontszámot
     */
    @Override
    public void give(int amount) {
        Tracer.getInstance().enterFunction(this, "give",amount);
        point += amount;
        Tracer.getInstance().exitFunction();
    }

}
