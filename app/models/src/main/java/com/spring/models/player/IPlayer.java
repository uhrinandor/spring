package com.spring.models.player;

import java.util.List;

import com.spring.models.vehicle.Vehicle;
import com.spring.models.utils.IEntity;

/**
 * Ezen interfész alatt kezeljük a játékosainkat. Ezen kívül, ez az interfész kezeli a játékosok 
 * pontjait.
 */
public interface IPlayer extends IEntity{
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

    /**
     * @param amount felveszünk egy adott mennyiségű jutalmat.
     * (A SnowPlowPlayer pénzt gyűjt (money), a BusPlayer pontokat gyűjt (point).)
     */
    public void give(int amount);
}