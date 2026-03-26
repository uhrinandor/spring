package com.spring.app.skeleton.models.shop;

import com.spring.app.skeleton.models.player.IPlayer;
import com.spring.app.skeleton.models.vehicle.ISnowplow;
import com.spring.app.skeleton.models.vehicle.Vehicle;
import com.spring.app.skeleton.utils.IEntity;

public interface PurchaseContext extends IEntity {
    public IPlayer player();
    public ISnowplow snowPlow();
    public boolean charge(int amount);
    public void addVehicle(Vehicle v);
}
