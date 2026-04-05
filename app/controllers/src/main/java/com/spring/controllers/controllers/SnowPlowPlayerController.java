package com.spring.controllers.controllers;

import java.util.ArrayList;
import java.util.List;

import com.spring.controllers.listeners.SnowPlowPlayerListener;
import com.spring.models.head.Broom;
import com.spring.models.head.Brush;
import com.spring.models.head.IHead;
import com.spring.models.player.SnowplowPlayer;
import com.spring.models.random.Random;
import com.spring.models.shop.Shop;
import com.spring.models.vehicle.Inventory;
import com.spring.models.vehicle.PlayerDriver;
import com.spring.models.vehicle.Snowplow;

public class SnowPlowPlayerController extends BaseController {
    List<SnowPlowPlayerListener> snowPlowPlayerListeners = new ArrayList<>();
    CycleController cycleController;
    SnowplowPlayer player;
    public SnowPlowPlayerController(CycleController cycleController, SnowplowPlayer player) {
        this.cycleController = cycleController;
        this.player = player;
    }

    public void addSnowPlowPlayerListener(SnowPlowPlayerListener listener){
        snowPlowPlayerListeners.add(listener);
    }

    public void setPlayer(SnowplowPlayer player) {
        this.player = player;
    }

    public SnowplowPlayer info(){
        return player;
    }

    /**
     * Új hókotró vásárlása a shopból
     */
    public void buySnowPlow(){
        Random rand = new Random();
        PlayerDriver driver = new PlayerDriver();
        Inventory inventory = new Inventory();
        IHead head = rand.nextBool(0.5) ? new Brush() : new Broom();

        Snowplow snowplow = new Snowplow(driver, inventory, head, player);

        Shop shop = new Shop();
        shop.buy(player, snowplow, 1);
    }

    /**
     * Lépések végrehajtása, feltételezzük, hogy már beállította mindre a következő mezőt
     */
    public void stepAll(){
        // TODO:
        // lépések végrehajtása stb.
        cycleController.nextPlayer();
    }

    /**
     * Hókotró kiválasztása
     * @param serial A hókotró sorszáma a készletben
     */
    public void select(int serial){
        if(player.vehicles().size() <= serial){
            error("No such snowplow with serial: " + serial);
            return;
        }
        Snowplow selected = (Snowplow)player.vehicles().get(serial);
        tracer.info("Selected snowplow: "+selected);

        player.setActive(selected);

        for(SnowPlowPlayerListener listener : snowPlowPlayerListeners){
            listener.onSnowPlowSelected();
        }
    }
}
