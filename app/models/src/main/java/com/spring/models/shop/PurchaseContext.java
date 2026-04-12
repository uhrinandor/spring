package com.spring.models.shop;

import com.spring.models.player.IPlayer;
import com.spring.models.vehicle.ISnowPlow;
import com.spring.models.vehicle.Vehicle;
import com.spring.models.utils.IEntity;

/**
 * Egy kontextus amit használva lehet vásárolni a boltból ShopItem-eket.
 */
public interface PurchaseContext extends IEntity {
    /**
     * @return Ez a metódus visszaadja a játékost aki végrehajtja a vásárlást.
     */
    public IPlayer player();

    /** 
     * @return Ez a metódus visszaadja azt a hókotrót amire történik a vásárlás.
     */
    public ISnowPlow snowPlow();

    /**
     * Ez függvény vonja le a vásárlás összegét a kontextusból, sikerességét egy bool-lal jelzi.
     * @param amount a vásárlás összege
     * @return a vásárlás sikeressége
     */
    public boolean charge(int amount);

    /**
     * Hozzáad egy járművet a jármű listába.
     * @param v a jármű amit fel kell venni a listába
     */
    public void addVehicle(Vehicle v);
}
