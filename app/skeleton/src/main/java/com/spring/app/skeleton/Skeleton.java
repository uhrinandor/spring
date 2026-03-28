package com.spring.app.skeleton;

import java.util.ArrayList;
import java.util.List;

import com.spring.app.skeleton.models.player.SnowplowPlayer;
import com.spring.app.skeleton.models.shop.IShop;
import com.spring.app.skeleton.models.shop.Shop;
import com.spring.app.skeleton.utils.MenuItem;
import com.spring.app.skeleton.utils.Tracer;
import com.spring.app.skeleton.models.vehicle.*;

public class Skeleton {
    private final List<MenuItem> menuItems = new ArrayList<>();
    private boolean end = false;
    Tracer tracer = Tracer.getInstance();

    public Skeleton() {
        menuItems.add(new MenuItem("Exit", this::exit));
    }

    /**
     * Elindítja a skeleton programot, megjeleníti a menüt és kezeli a felhasználói inputot.
     */
    public void start(){
        tracer.info("Skeleton started");
        while(!end){
            printMenu();
            int choice = tracer.askInt("Choose an option");
            if(choice < 0 || choice >= menuItems.size()){
                tracer.info("Invalid option, try again.");
                continue;
            }
            menuItems.get(choice).execute();
        }
    }

    /**
     * Kiíratja a menü lehetőségeket a konzolra
     */
    private void printMenu(){
        tracer.info("Options:");
        for(int i=0; i<menuItems.size(); i++){  
            tracer.info(String.format("%d. %s", i, menuItems.get(i).getTitle()));
        }
    }

    /**
     * Kilép a programból
     */
    private void exit(){
        tracer.info("Exiting...");
        end = true;
    }

    public static IShop initShop(){
        /*
            Inicializál egy Shop objektumot és visszaadja annak IShop interface-t.
        */
        Shop shop = new Shop();
        return shop;
    }

    public static SnowplowPlayer getPurchasContext(){
        var spp = new SnowplowPlayer();
        var sp = new Snowplow(spp);
    }
}
