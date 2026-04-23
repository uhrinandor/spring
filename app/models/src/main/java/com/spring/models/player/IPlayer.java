package com.spring.models.player;

import java.util.List;

import com.spring.models.vehicle.Vehicle;

/**
 * Ezen interfész alatt kezeljük a játékosainkat. Ezen kívül, ez az interfész kezeli a játékosok 
 * pontjait.
 */ 
public interface IPlayer extends IRPlayer, ICollector{
    /**
     * @param amount felveszünk egy adott mennyiségű jutalmat.
     * (A SnowPlowPlayer pénzt gyűjt (money), a BusPlayer pontokat gyűjt (point).)
     */
    public void give(int amount);
}