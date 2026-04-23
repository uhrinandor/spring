package com.spring.models.player;

import java.util.List;

import com.spring.models.utils.IEntity;
import com.spring.models.vehicle.Vehicle;

public interface IRPlayer extends IEntity{
    /**
     * @return Ez a metódus visszaadja a játékos által vezetett Vehicle-ket egy lista formában.
     * (Csak a SnowPlowPlayer-nél lehet itt egynél több jármű,
     * a BusPlayer-nél csak egy busz van a listában.)
     */
    public List<Vehicle> vehicles();

    /**
     * @return Ez a metódus visszaadja a játékos pontszámát.
     */
    public int getPoints();
}
