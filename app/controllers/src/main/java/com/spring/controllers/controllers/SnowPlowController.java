package com.spring.controllers.controllers;

import java.util.ArrayList;
import java.util.List;

import com.spring.controllers.listeners.SnowPlowListener;
import com.spring.models.field.IField;
import com.spring.models.head.Broom;
import com.spring.models.head.Brush;
import com.spring.models.head.Dragon;
import com.spring.models.head.IHead;
import com.spring.models.head.IceBreaker;
import com.spring.models.head.SaltSpreader;
import com.spring.models.head.StoneSplasher;
import com.spring.models.layer.Biokerosene;
import com.spring.models.layer.Salt;
import com.spring.models.layer.Stone;
import com.spring.models.player.SnowplowPlayer;
import com.spring.models.shop.Shop;
import com.spring.models.shop.ShopItem;
import com.spring.models.vehicle.IInventoryItem;
import com.spring.models.vehicle.ISnowPlow;
import com.spring.models.vehicle.Vehicle;

public class SnowPlowController extends BaseController {
    SnowplowPlayer player;
    List<SnowPlowListener> snowPlowListeners = new ArrayList<>();

    public void setPlayer(SnowplowPlayer player) {
        this.player = player;
    }

    public void addSnowPlowListener(SnowPlowListener listener){
        snowPlowListeners.add(listener);
    }

    /**
     * Visszaadja az aktív hókotró adatait
     */
    public ISnowPlow info(){
        return player.snowPlow();
    }

    /**
     * A shopban megvásárolható elemeket adja vissza
     * @return
     */
    public List<ShopItem> shop(){
        List<ShopItem> shopItems = new ArrayList<>(); 

        shopItems.addAll(List.of(
            new Dragon(),
            new Broom(),
            new Brush(),
            new IceBreaker(),
            new StoneSplasher(),
            new SaltSpreader(),
            new Salt(),
            new Stone(),
            new Biokerosene()
        ));

        return shopItems;
    }

    /**
     * Az engedélyezett listából tudunk vásárolni
     * @param serial a vásárolni kívánt elem sorszáma a shop listában
     */
    public void buy(int serial){
        List<ShopItem> shopItems = shop();
        if(serial < 0 || serial >= shopItems.size()){
            error("Invalid shop item serial: "+serial);
            return;
        }
        
        ShopItem item = shopItems.get(serial);
        Shop shop = new Shop();
        shop.buy(player, item, 1);
        //TODO: itt nem tudunk visszajelezni hogy mizu
    }

    /**
     * Lecseréli a hókotró fejét a megadott fejre
     * @param head a fej, amire cserélni szeretnénk
     */
    public boolean switchHead(IHead head){
        return player.snowPlow().switchHead(head);
    }

    /**
     * Legális lépések listázása a busz kontextusában
     * @return Egy lista a legális lépésekről
     */
    public List<IField> listAvailable(){
        Vehicle snowplow = player.vehicles().get(0);
        IField current = snowplow.getDriver().getCurrent();
        IField next = snowplow.getDriver().getNext();

        if(current != null && current.getVehicle().equals(snowplow)) return listAvailable(current);
        else if(next != null && next.getVehicle().equals(snowplow)) return listAvailable(next);
        return List.of();
    }

    /**
     * Vissazaadja a legális lépéseket egy adott mezőről
     * @param field A mező
     * @return A mezők listája
     */
    private List<IField> listAvailable(IField field){
        List<IField> front = field.getFront().getAvailable();
        List<IField> result = new ArrayList<>();
        result.addAll(front);
        if(field.getRight() != null) result.add(field.getRight());
        if(field.getLeft() != null) result.add(field.getLeft());

        return result;
    }

    /**
     * Kilépteti a hókotró menüből vissza a játékosra
     */
    public void back(){
        player.setActive(null);
        for(SnowPlowListener listener : snowPlowListeners){
            listener.onExit();
        }
    }

    /**
     * Beállítja a következő mezőt a sorszám alapján
     * @param serial A sorszám
     */
    public IField setNext(int serial){
        if(serial < 0 || serial >= listAvailable().size()){
            error("Invalid field serial: "+serial);
            return null;
        }
        IField next = listAvailable().get(serial);
        Vehicle vehicle = (Vehicle)player.snowPlow();
        vehicle.getDriver().setNext(next);
        return next;
    }

    /**
     * Hozzáad egy tárgyat a hókotró készletéhez
     * @param item a tárgy
     * @return sikeres volt-e a hozzáadás
     */
    public boolean addInventory(IInventoryItem item){
        return player.snowPlow().getInventory().addItem(item, 1);
    }
}
