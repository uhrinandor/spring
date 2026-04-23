package com.spring.controllers.utils;

import java.util.ArrayList;
import java.util.List;

import com.spring.models.buildings.Home;
import com.spring.models.buildings.Office;
import com.spring.models.buildings.Station;
import com.spring.models.field.IField;
import com.spring.models.field.IRoad;
import com.spring.models.player.BusPlayer;
import com.spring.models.player.SnowplowPlayer;
import com.spring.models.vehicle.Car;

public class GameContext {
    int rounds;
    int currentRound;
    
    List<IField> fields;
    List<IRoad> crossRoads = new ArrayList<>();
    List<Home> homes = new ArrayList<>();
    List<Office> offices = new ArrayList<>();
    // Itt elég, ha csak az egyiket tároljuk a párból
    List<Station> stations = new ArrayList<>();
    List<SnowplowPlayer> snowplowPlayers = new ArrayList<>();
    List<BusPlayer> busPlayers = new ArrayList<>();
    List<Car> cars = new ArrayList<>();

    /**
     * Ez volt az eredeti ctr, nem tudom hogy van-e valami ami használja ilyen formátumban, úgyhogy egyelőre benthagyom
     */

    public GameContext(int rounds, List<SnowplowPlayer> snowplowPlayers, List<BusPlayer> busPlayers) {
        this.fields = new ArrayList<>();
        this.rounds = rounds;
        this.currentRound = 1;
        this.snowplowPlayers = snowplowPlayers;
        this.busPlayers = busPlayers;
    }

    public GameContext(int rounds, List<IField> fields, List<SnowplowPlayer> snowplowPlayers, List<BusPlayer> busPlayers, List<Car> cars) {
        this.rounds = rounds;
        this.currentRound = 1;
        this.fields = fields;
        this.snowplowPlayers = snowplowPlayers;
        this.busPlayers = busPlayers;
        this.cars = cars;
    }


    public GameContext() {
        this.fields = new ArrayList<>();
        this.currentRound = 1;
        //TODO Auto-generated constructor stub
    }

    public List<IField> getFields() {
        return fields;
    }

    public List<IRoad> getCrossRoads() {
        return crossRoads;
    }

    public List<Office> getOffices() {
        return offices;
    }

    public List<Station> getStations() {
        return stations;
    }

    public List<SnowplowPlayer> getSnowplowPlayers() {
        return snowplowPlayers;
    }

    public List<BusPlayer> getBusPlayers() {
        return busPlayers;
    }

    public List<Home> getHomes() {
        return homes;
    }

    public void setRounds(int rounds){
        this.rounds = rounds;
    }
    
    /**
     * Növeli a körszámot, és visszaadja, hogy van-e még kör
     * @return true, ha van még kör, false, ha vége a játéknak
     */
    public boolean increaseRound(){
        if(currentRound < rounds) {
            currentRound++;
            return true;
        }
        return false;
    }

//TODO: 

    private void subscribeToAll(){}

    private void subscribeToCars(){}

    private void subscribeToBusPlayers(){}

    private void subscribeToSnowPlowPlayers(){}
}
