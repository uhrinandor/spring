package com.spring.app.skeleton.models.player;

import java.util.ArrayList;
import java.util.List;

import com.spring.app.skeleton.models.shop.PurchaseContext;
import com.spring.app.skeleton.models.vehicle.ISnowPlow;
import com.spring.app.skeleton.models.vehicle.Snowplow;
import com.spring.app.skeleton.models.vehicle.Vehicle;

public class SnowplowPlayer implements PurchaseContext, IPlayer{
    private List<Vehicle> snowPlows = new ArrayList<>();
    private Snowplow active;
    private int money;

    public void setsnowPlow(List<Vehicle> tmp){
        this.snowPlows = tmp;
    }

    public void setActive(Snowplow tmp){
        active = tmp;
    }

    public int getMoney(){
        return money;
    }

    public void setMoney(int tmp){
        money = tmp;
    }

    @Override
    public int getId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }

    @Override
    public List<Vehicle> vehicles() {
        return snowPlows;
    }

    @Override
    public int getPoints() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPoints'");
    }

    @Override
    public void give(int amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'give'");
    }

    @Override
    public IPlayer player() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'player'");
    }

    @Override
    public ISnowPlow snowPlow() {
        return active;
    }

    @Override
    public boolean charge(int amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'charge'");
    }

    @Override
    public void addVehicle(Vehicle v) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addVehicle'");
    }

}
