package com.spring.app.skeleton.models.player;

import java.util.ArrayList;

import com.spring.app.skeleton.models.vehicle.Vehicle;
import com.spring.app.skeleton.utils.IEntity;

public interface IPlayer extends IEntity{
    public ArrayList<Vehicle> vehicles();
    public int getPoints();
    public void give(int amount);
}