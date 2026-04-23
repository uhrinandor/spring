package com.spring.models.player;

import com.spring.models.utils.IEntity;

/**
 * Feladata a jutalmak összegyűjtése, pl.: busz pontjai vagy hókotró által szerzett pénz.
 */
public interface ICollector extends IEntity{

    /**
     * Felveszünk egy adott mennyiségű jutalmat az adott játékosnál.
     * @param amount a mennyiség amivel a játékos pontjait/pénzét növelni kell
     */
    public void give(int amount);
}
