package com.spring.controllers.controllers;

import java.util.ArrayList;
import java.util.List;

import com.spring.controllers.listeners.GameStartedListener;
import com.spring.controllers.utils.GameContext;
import com.spring.models.buildings.Station;
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

    int rounds;

    List<SnowplowPlayer> snowplowPlayers = new ArrayList<>();
    List<BusPlayer> busPlayers = new ArrayList<>();
    
    GameContext ctx = new GameContext();

    public void addListener(GameStartedListener listener){
        initListeners.add(listener);
    }

    /**
     * Beállítja a játék körök számát. Legalább 1 körnek kell lennie, különben hibaüzenetet küldünk a nézetnek.
     * @param rounds A körszám
     */
    public void rounds(int rounds){
        if(rounds < 1) {
            error("Number of rounds must be at least 1");
            return;
        }
        tracer.info("Rounds set to " + rounds);
        this.rounds = rounds;
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

        snowplowPlayers.add(player);
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

        busPlayers.add(player);
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

        GameContext context = new GameContext(rounds, snowplowPlayers, busPlayers);
        for(GameStartedListener listener : initListeners){
            listener.onGameStarted(context);
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

    public List<IRoad> listCrossRoads(){
        return ctx.getCrossRoads();
    }

    public List<IField> listFields(){
        return ctx.getFields();
    }

    public void setSide(boolean right, int field1, int field2){
        if(right){
            listFields().get(field1).setRight(listFields().get(field2));
            listFields().get(field2).setLeft(listFields().get(field1));
        }
        else{
            listFields().get(field1).setLeft(listFields().get(field2));
            listFields().get(field2).setRight(listFields().get(field1));
        }
    }

    public void setFrontCrossRoad(int f, int r){
        ctx.getFields().get(f).setFront(ctx.getFields().get(r));
    }

    public void setFrontField(int f1, int f2){
        listFields().get(f1).setFront(listFields().get(f2));
    }

    public void placeBus(int busplayer, int field){
        
        ctx.getFields().get(field).setVehicle(ctx.getBusPlayers().get(busplayer).vehicles().get(0));
        ctx.getBusPlayers().get(busplayer).vehicles().get(0).getDriver().setNext(ctx.getFields().get(field));

        List<IField> fields = ctx.getFields();
        IField currentField = ctx.getFields().get(field);
        Bus bus = (Bus)ctx.getBusPlayers().get(busplayer).vehicles().get(0);
        List<Station> stations = ctx.getStations();

        for(int i = 0; i < stations.size(); i++){
            if(stations.get(i).getField() == currentField){
                bus.setStation(stations.get(i));
            }
        }
    }

    public void placeSp(int snowPlowplayer, int field){
        List<IField> fields = ctx.getFields();
        SnowplowPlayer spp = ctx.getSnowplowPlayers().get(snowPlowplayer);
        Snowplow sp = (Snowplow)spp.vehicles().get(0);
        spp.addVehicle(sp);
        PlayerDriver playerDriver = (PlayerDriver)sp.getDriver();
        playerDriver.setNext(fields.get(field));

    }
}
