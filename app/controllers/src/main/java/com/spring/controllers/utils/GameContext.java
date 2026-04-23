package com.spring.controllers.utils;

import java.util.ArrayList;
import java.util.List;

import com.spring.models.buildings.Home;
import com.spring.models.buildings.Office;
import com.spring.models.buildings.Station;
import com.spring.models.field.Field;
import com.spring.models.player.BusPlayer;
import com.spring.models.player.SnowplowPlayer;
import com.spring.models.vehicle.Car;
import com.spring.models.vehicle.CarDriver;

// TODO: átdolgozandó, csak játék flow-hoz használtam, kibővíthető, újragondolható, két játékos listának kell itt maradnia és az increaseRound-nak
public class GameContext {
    int rounds;
    int currentRound;

    //Field startingField;
    List<Field> fields = new ArrayList<>();
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
        this.rounds = rounds;
        this.currentRound = 1;
        this.snowplowPlayers = snowplowPlayers;
        this.busPlayers = busPlayers;
    }

    public GameContext(int rounds, List<Field> fields, List<SnowplowPlayer> snowplowPlayers, List<BusPlayer> busPlayers, List<Car> cars) {
        this.rounds = rounds;
        this.currentRound = 1;
        this.fields = fields;
        this.snowplowPlayers = snowplowPlayers;
        this.busPlayers = busPlayers;
        this.cars = cars;
    }


    public List<SnowplowPlayer> getSnowplowPlayers() {
        return snowplowPlayers;
    }

    public List<BusPlayer> getBusPlayers() {
        return busPlayers;
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
