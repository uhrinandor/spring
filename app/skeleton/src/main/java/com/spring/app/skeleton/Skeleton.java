package com.spring.app.skeleton;

import java.util.ArrayList;
import java.util.List;

import com.spring.app.skeleton.models.buildings.Station;
import com.spring.app.skeleton.models.buildings.Office;
import com.spring.app.skeleton.models.buildings.Station;
import com.spring.app.skeleton.models.field.CrossRoad;
import com.spring.app.skeleton.models.field.Field;
import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.models.head.Broom;
import com.spring.app.skeleton.models.head.Brush;
import com.spring.app.skeleton.models.head.Dragon;
import com.spring.app.skeleton.models.head.IceBreaker;
import com.spring.app.skeleton.models.head.SaltSpreader;
import com.spring.app.skeleton.models.layer.Ice;
import com.spring.app.skeleton.models.layer.Layer;
import com.spring.app.skeleton.models.layer.Snow;
import com.spring.app.skeleton.models.player.BusPlayer;
import com.spring.app.skeleton.models.player.SnowplowPlayer;
import com.spring.app.skeleton.models.random.Random;
import com.spring.app.skeleton.models.shop.IShop;
import com.spring.app.skeleton.models.shop.Shop;
import com.spring.app.skeleton.models.vehicle.Bus;
import com.spring.app.skeleton.models.vehicle.Car;
import com.spring.app.skeleton.models.vehicle.CarDriver;
import com.spring.app.skeleton.models.vehicle.Inventory;
import com.spring.app.skeleton.models.vehicle.PlayerDriver;
import com.spring.app.skeleton.models.vehicle.Snowplow;
import com.spring.app.skeleton.models.vehicle.Vehicle;
import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.MenuItem;
import com.spring.app.skeleton.utils.Tracer;

public class Skeleton {
    private final List<MenuItem> menuItems = new ArrayList<>();
    private boolean end = false;
    Tracer tracer = Tracer.getInstance();

    public Skeleton() {
        menuItems.add(new MenuItem("Exit", this::exit));
        menuItems.add(new MenuItem("StepCarFromSnow", this::stepCarFromSnow));
        menuItems.add(new MenuItem( "StepBusFromSnow", this::StepBusFromSnow));
        menuItems.add(new MenuItem("StepBusToIce", this::StepBusToIce));
        menuItems.add(new MenuItem("StepBusWreck", this::StepBusWreck));
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
        Shop shop = new Shop();
        return shop;
    }

    public static SnowplowPlayer getPurchasContext(){
  
        var spp = new SnowplowPlayer();
        var inv = new Inventory();
        var sp = new Snowplow(null, inv, null, spp);

        
        spp.addVehicle(sp);
        spp.setActive(sp);
        return spp;
    }
    private void stepCarFromSnow(){
        Tracer.hide();
        IField start = getCar();
        
        Vehicle car = start.getVehicle();
        Tracer.show();

        car.step();
        Entity.reset();
    }

    private void StepBusFromSnow(){
        Tracer.hide();
        IField start = getBus();
        
        Vehicle bus = start.getVehicle();
        Tracer.show();

        bus.step();
        Entity.reset();
    }

    private void StepBusToIce(){
        Tracer.hide();
        IField start = getBus();
        
        IField field=start.getRight();
        Vehicle bus = field.getVehicle();
        Tracer.show();

        bus.step();
        Entity.reset();
    }

    private void StepBusWreck(){
        Tracer.hide();
        IField start = getBus();

        IField field=start.getRight().getRight();
        Vehicle bus = field.getVehicle();
        Tracer.show();

        bus.step();
        Entity.reset();
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
    private IField getBus(){
        Random r = new Random();
        Field f1 = new Field(null, null, null, null, null, r, null, false);
        Field f2 = new Field(null, null, null, null, null, r, null, false);
        Field f3 = new Field(null, null, null, null, null, r, null, false);
        Field f4 = new Field(null, null, null, null, null, r, null, false);
        Field f5 = new Field(null, null, null, null, null, r, null, false);
        Field f6 = new Field(null, null, null, null, null, r, null, false);
        Field f7 = new Field(null, null, null, null, null, r, null, false);

        f1.setLayer(new Snow(2));
        f2.setLayer(new Snow(2));
        f3.setLayer(new Ice());
        f4.setLayer(new Ice());
        f5.setLayer(new Snow(2));
        f6.setLayer(new Layer());
        f7.setLayer(new Layer());

        f1.setFront(f2);
        f3.setFront(f4);
        f4.setFront(f5);
        f6.setFront(f7);

        f1.setRight(f3);
        f3.setRight(f6);
        
        PlayerDriver d1 = new PlayerDriver();
        PlayerDriver d2 = new PlayerDriver();
        PlayerDriver d3 = new PlayerDriver();

        d1.setNext(f2);
        d2.setNext(f4);
        d3.setNext(f7);

        d1.setCurrent(f1);
        d2.setCurrent(f3);
        d3.setCurrent(f6);

        Station s1 = new Station(f2);
        Station s2 = new Station(f6);

        s1.setPair(s2);
        s2.setPair(s1);

        BusPlayer bp1 = new BusPlayer();
        BusPlayer bp2 = new BusPlayer();
        BusPlayer bp3 = new BusPlayer();

        Bus b1 = new Bus(null, null, null);
        Bus b2 = new Bus(null, null, null);
        Bus b3 = new Bus(null, null, null);
        Car c1 = new Car(null, null);

        b1.setDriver(d1);
        b2.setDriver(d2);
        b3.setDriver(d3);

        b1.setStation(s1);
        b2.setStation(s1);
        b3.setStation(s1);

        f1.setVehicle(b1);
        f3.setVehicle(b2);
        f6.setVehicle(b3);
        f7.setVehicle(c1);

        b1.setCollector(bp1);
        b2.setCollector(bp2);
        b3.setCollector(bp3);

        return f1;
    }
   private IField getCar(){
        Random r = new Random();
        Field f1 = new Field(null, null, null, null, null, r, null, false);
        Field f2 = new Field(null, null, null, null, null, r, null, false);
        Field f3 = new Field(null, null, null, null, null, r, null, false);
        Field f4 = new Field(null, null, null, null, null, r, null, false);
        Field f5 = new Field(null, null, null, null, null, r, null, false);
        Field f6 = new Field(null, null, null, null, null, r, null, false);
        Field f7 = new Field(null, null, null, null, null, r, null, false);
        CrossRoad cr1 = new CrossRoad(List.of(f5));

        f1.setLayer(new Snow(2));
        f2.setLayer(new Snow(2));
        f3.setLayer(new Ice());
        f4.setLayer(new Ice());
        f5.setLayer(new Snow(2));
        f6.setLayer(new Layer());
        f7.setLayer(new Layer());   

        f1.setFront(f2);
        f2.setFront(cr1);
        f3.setFront(f4);
        f4.setFront(f5);
        f6.setFront(f7);

        f1.setRight(f3);
        f3.setRight(f6);

        CarDriver d1 = new CarDriver();
        CarDriver d2 = new CarDriver();
        CarDriver d3 = new CarDriver();

        d1.setNext(f2);
        d2.setNext(f4);
        d3.setNext(f7);

        d1.setCurrent(f1);
        d2.setCurrent(f3);
        d3.setCurrent(f6);

        Office o1 = new Office(f2);

        Car c1 = new Car(null, null);
        Car c2 = new Car(null, null);
        Car c3 = new Car(null, null);

        c1.setDriver(d1);
        c2.setDriver(d2);
        c3.setDriver(d3);

        c1.setDestination(o1);
        c2.setDestination(o1);
        c3.setDestination(o1);

        f1.setVehicle(c1);
        f3.setVehicle(c2);
        f6.setVehicle(c3);
        
        return f1;
   }

}
