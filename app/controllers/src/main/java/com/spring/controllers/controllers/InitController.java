package com.spring.controllers.controllers;

import java.util.ArrayList;
import java.util.List;

import com.spring.controllers.listeners.GameStartedListener;
import com.spring.controllers.utils.GameContext;
import com.spring.models.buildings.Building;
import com.spring.models.buildings.Home;
import com.spring.models.buildings.Office;
import com.spring.models.buildings.Station;
import com.spring.models.field.CrossRoad;
import com.spring.models.field.Field;
import com.spring.models.field.IField;
import com.spring.models.field.IRField;
import com.spring.models.field.IRoad;
import com.spring.models.head.Broom;
import com.spring.models.head.Brush;
import com.spring.models.head.IHead;
import com.spring.models.layer.ILayer;
import com.spring.models.player.BusPlayer;
import com.spring.models.player.SnowplowPlayer;
import com.spring.models.random.Random;
import com.spring.models.utils.Tracer;
import com.spring.models.vehicle.Bus;
import com.spring.models.vehicle.Inventory;
import com.spring.models.vehicle.PlayerDriver;
import com.spring.models.vehicle.Snowplow;

public class InitController extends BaseController {
    List<GameStartedListener> initListeners = new ArrayList<>();
    
    GameContext ctx = new GameContext();

    public void addListener(GameStartedListener listener){
        initListeners.add(listener);
    }

    /**
     * Beállítja a játék körök számát. Legalább 1 körnek kell lennie, különben hibaüzenetet küldünk a nézetnek.
     * @param rounds A körszám
     */
    public void rounds(int rounds){
        ctx.setRounds(rounds);
        tracer.info("Rounds set to " + rounds);
    }

    /**
     * Hozzáad egy új hókotrós játékost egy hókotróval, random fejjel és üres készlettel
     */
    public void addSnowplowPlayer(){
        SnowplowPlayer player = new SnowplowPlayer();

        Random rand = new Random();
        PlayerDriver driver = new PlayerDriver();
        Inventory inventory = new Inventory();
        IHead head = rand.nextBool(0.5) ? new Brush() : new Broom();

        Snowplow snowplow = new Snowplow(driver, inventory, head, player);
        player.addVehicle(snowplow);

        ctx.getSnowplowPlayers().add(player);
        tracer.info(String.format("%s added", player));
    }

    /**
     * Hozzáad egy új buszos játékost egy busszal
     */
    public void addBusPlayer(){
        PlayerDriver driver = new PlayerDriver();
        BusPlayer  player = new BusPlayer();
        Bus bus = new Bus(driver, null, player);
        player.setBus(bus);

        ctx.getBusPlayers().add(player);
        tracer.info(String.format("%s added", player));
    }

    /**
     * Elindítja a játékot, ha minden feltétel teljesül. Ezek az alábbiak:
     * - Legalább egy hókotrós játékos
     * - Legalább egy buszos játékos
     * - Kör beállítva
     * 
     * Ezután legenerálja a játék térképet, letelepsíti a járműveket és létrehozza a GameContext-et, 
     * amit átad a CycleControllernek, hogy átvegye a játék irányítását.
     */
    public void start(boolean deterministicMode){
        // TODO:
        // Validation
        // Generate map
        // Place vehicles
        // Create game context
        // Pass the responsibility to the CycleController

        if(deterministicMode){
            tracer.info("Starting game in deterministic mode");
            Tracer.changeDeterministicMode(deterministicMode);
        }

        for(GameStartedListener listener : initListeners){
            listener.onGameStarted(ctx);
        }
    }

    /**
     * Egy field-et ad a pályához
     * @param layer a layer amit rárakunk
     * @param underGround aluljáró-e a mező
     */
    public IRField addField(ILayer layer, boolean underGround){
        IField field = new Field(layer, null, null, null, null, null, null, underGround);
        ctx.getFields().add(field);
        return field;
    }

    /**
     * Egy kereszteződést ad a pályához a megadott kimenő mezőkkel. A kimenő mezők indexét kapja meg, és ellenőrzi, hogy érvényesek-e.
     * @param outFields a kimenő mezők indexei
     * @return létrehozott CrossRoad
     */
    public IRoad addCrossRoad(List<Integer> outFields){
        if(!outFields.stream().allMatch(i -> i < ctx.getFields().size())){
            error("Invalid field index");
            return null;
        }

        List<IField> fields = outFields.stream().map(i -> ctx.getFields().get(i)).toList();
        IRoad crossRoad = new CrossRoad(fields);
        ctx.getCrossRoads().add(crossRoad);
        return crossRoad;
    }

    /**
     * Egy otthont ad a pályához a megadott indexű mező mellé
     * @param serial
     * @return
     */
    public Building addHome(int serial){
        if(serial >= ctx.getFields().size()){
            error("Invalid field index");
            return null;
        }

        Home home = new Home(ctx.getFields().get(serial));
        ctx.getHomes().add(home);
        return home;
    }

    /**
     * Egy irodát ad a pályához a megadott indexű mező mellé
     * @param serial A mező sorszáma
     * @return Létrehozott Office
     */
    public Building addOffice(int serial){
        if(serial >= ctx.getFields().size()){
            error("Invalid field index");
            return null;
        }

        Office office = new Office(ctx.getFields().get(serial));
        ctx.getOffices().add(office);
        return office;
    }

    /**
     * Két végállomást ad a pályához a megadott indexű mezők mellé, és összepárosítja őket. A buszok ezek között ingáznak majd.
     * @param serial1 Az első mező sorszáma
     * @param serial2 A második mező sorszáma
     * @return
     */
    public List<Building> addStations(int serial1, int serial2){
        if(serial1 >= ctx.getFields().size() || serial2 >= ctx.getFields().size()){
            error("Invalid field index");
            return null;
        }

        Station station1 = new Station(ctx.getFields().get(serial1));
        Station station2 = new Station(ctx.getFields().get(serial2));
        station1.setPair(station2);
        station2.setPair(station1);
        ctx.getStations().add(station1);
        ctx.getStations().add(station2);
        return List.of(station1, station2);
    }
}
