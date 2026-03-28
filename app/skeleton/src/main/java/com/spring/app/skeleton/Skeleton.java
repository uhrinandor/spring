package com.spring.app.skeleton;

import java.util.ArrayList;
import java.util.List;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.models.field.IRoad;
import com.spring.app.skeleton.models.head.Broom;
import com.spring.app.skeleton.models.head.Brush;
import com.spring.app.skeleton.models.head.Dragon;
import com.spring.app.skeleton.models.head.IceBreaker;
import com.spring.app.skeleton.models.head.SaltSpreader;
import com.spring.app.skeleton.models.layer.ILayer;
import com.spring.app.skeleton.models.layer.ISalt;
import com.spring.app.skeleton.models.layer.Ice;
import com.spring.app.skeleton.models.layer.Snow;
import com.spring.app.skeleton.models.player.SnowplowPlayer;
import com.spring.app.skeleton.models.random.IRandom;
import com.spring.app.skeleton.models.random.Random;
import com.spring.app.skeleton.models.vehicle.Inventory;
import com.spring.app.skeleton.models.vehicle.PlayerDriver;
import com.spring.app.skeleton.models.vehicle.Snowplow;
import com.spring.app.skeleton.models.vehicle.Vehicle;
import com.spring.app.skeleton.models.field.Field;
import com.spring.app.skeleton.utils.MenuItem;
import com.spring.app.skeleton.utils.Tracer;

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

    private IField getSnowPlow(){

        //alapobjektumok létrehozása
        Random r = new Random();
        Field f1 = new Field(null, null, null, null, null, r, null, false);
        Field f2 = new Field(null, null, null, null, null, r, null, false);
        Field f3 = new Field(null, null, null, null, null, r, null, false);
        Field f4 = new Field(null, null, null, null, null, r, null, false);
        Field f5 = new Field(null, null, null, null, null, r, null, false);
        Field f6 = new Field(null, null, null, null, null, r, null, false);
        Field f7 = new Field(null, null, null, null, null, r, null, false);
        Field f8 = new Field(null, null, null, null, null, r, null, false);
        Field f9 = new Field(null, null, null, null, null, r, null, false);

        //Layerek beállítása
        f1.setLayer(new Snow(2));
        f2.setLayer(new Snow(2));
        f3.setLayer(new Ice());
        f4.setLayer(new Ice());
        f5.setLayer(new Ice());
        f6.setLayer(new Snow(2));
        f7.setLayer(new Snow(2));
        f8.setLayer(new Ice());
        f9.setLayer(new Ice());

        //A Fieldek egymáshoz való viszony beállítása
        f1.setFront(f2);
        f2.setFront(f3);
        f3.setFront(f4);
        f8.setFront(f9);

        f1.setRight(f5);
        f5.setRight(f6);
        f6.setRight(f7);
        f7.setRight(f8);

        //PlayerDriver-ek létrehozása
        PlayerDriver d1 = new PlayerDriver();
        PlayerDriver d2 = new PlayerDriver();
        //Aktuális pozíció beállítása
        d1.setCurrent(f1);
        d1.setCurrent(f8);
        //Következő lépés beállítása
        d1.setNext(f2);
        d2.setNext(f9);

        //Hókotrók létrehozása
        Snowplow s1 = new Snowplow(null);
        Snowplow s2 = new Snowplow(null);
        Snowplow s3 = new Snowplow(null);
        Snowplow s4 = new Snowplow(null);
        Snowplow s5 = new Snowplow(null);

        //A hókotrókhoz a vezetők beállítása
        s1.setDriver(d1);
        s5.setDriver(d2);

        f1.setVehicle(s1);
        f5.setVehicle(s2);
        f6.setVehicle(s3);
        f7.setVehicle(s4);
        f8.setVehicle(s5);

        SnowplowPlayer p1 = new SnowplowPlayer();
        SnowplowPlayer p2 = new SnowplowPlayer();
        SnowplowPlayer p3 = new SnowplowPlayer();
        SnowplowPlayer p4 = new SnowplowPlayer();
        SnowplowPlayer p5 = new SnowplowPlayer();

        s1.setCollector(p1);
        s2.setCollector(p2);
        s3.setCollector(p3);
        s4.setCollector(p4);
        s5.setCollector(p5);

        Inventory i1 = new Inventory();

        s1.setInventory(i1);
        s2.setInventory(i1);
        s3.setInventory(i1);
        s4.setInventory(i1);
        s5.setInventory(i1);

        s1.setHead(new Dragon());
        s2.setHead(new Broom());
        s3.setHead(new Brush());
        s4.setHead(new SaltSpreader());
        s5.setHead(new IceBreaker());

        return f1;
    }
    //private IField getBus(){}
   // private IField getCar(){}

}
