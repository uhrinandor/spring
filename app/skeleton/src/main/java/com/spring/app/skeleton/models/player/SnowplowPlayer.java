package com.spring.app.skeleton.models.player;

import java.util.ArrayList;
import java.util.List;

import com.spring.app.skeleton.models.shop.PurchaseContext;
import com.spring.app.skeleton.models.vehicle.ISnowPlow;
import com.spring.app.skeleton.models.vehicle.Vehicle;
import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.Tracer;

/**
 * Ez az osztály felel azért, hogy megkülönböztessük melyik játékoshoz melyik hókotrók 
 * tartoznak. Gyűjtik a pénzt, kontextust biztosítanak a vásárláshoz.
 */
public class SnowplowPlayer extends Entity implements PurchaseContext, IPlayer{
    /**
     * A játékoshoz tartozó hókotrók.
     */
    private List<Vehicle> snowPlows = new ArrayList<>();

    /**
     * A játékos által éppen használatban levő, aktív hókotró.
     */
    private ISnowPlow active;

    /**
     * Ebben az attribútumban tárolja el, hogy mennyi pénzt szerzett.
     */
    private int money;

    public void setsnowPlow(List<Vehicle> tmp){
        this.snowPlows = tmp;
    }

    public void setActive(ISnowPlow tmp){
        active = tmp;
    }

    @Override
    public List<Vehicle> vehicles() {
        return snowPlows;
    }

    @Override
    public int getPoints() {
        return money;
    }

    /**
     * Növeli a pénzét a megadott mennyiségével.
     */
    @Override
    public void give(int amount) {
        money += amount;
    }

    /**
     * @return Visszatér önmagával.
     */
    @Override
    public IPlayer player() {
        return this;
    }

    /**
     * Visszaadja az aktív hókotrót
     */
    @Override
    public ISnowPlow snowPlow() {
        return active;
    }

    /**
     * A függvény megpróbálja levonni a játékos pénzét.
     * @param amount a levonni kívánt mennyiség
     * @return ha sikerül, igazzal tér vissza, ha nincs ennyi pénze, hamissal
     */
    @Override
    public boolean charge(int amount) {
        int m = Tracer.getInstance().askInt("Mennyi pénze van?");
        if(m < amount)  return false;

        money -= m - amount;
        return true;
    }

    /**
     * Hozzáad egy járművet a játékos hókotróihoz.
     * @param v a hozzáadandó jármű
     */
    @Override
    public void addVehicle(Vehicle v) {
        snowPlows.add(v);
    }

    @Override
    public List<String> init() {
        StringBuilder sb = new StringBuilder("");
        for(int i=0; i<snowPlows.size(); i++){
            sb.append(snowPlows.get(i).toString());
            if(i != snowPlows.size() - 1) sb.append(", ");
        }

        return List.of("snowPlows: " + sb.toString(), "active: " + active, "money: " + money); 
    }

}
