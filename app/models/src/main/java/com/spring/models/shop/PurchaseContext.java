package com.spring.models.shop;
import com.spring.models.vehicle.Vehicle;

/** 
 * Egy kontextus amit használva lehet vásárolni a boltból ShopItem-eket.
 */
public interface PurchaseContext extends RPurchaseContext {
   

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
