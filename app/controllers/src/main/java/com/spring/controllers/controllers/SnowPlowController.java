package com.spring.controllers.controllers;

import java.util.ArrayList;
import java.util.List;

import com.spring.controllers.listeners.SnowPlowListener;
import com.spring.models.field.IField;
import com.spring.models.head.IHead;
import com.spring.models.player.SnowplowPlayer;
import com.spring.models.shop.Shop;
import com.spring.models.shop.ShopItem;
import com.spring.models.vehicle.ISnowPlow;

public class SnowPlowController extends BaseController {
    SnowplowPlayer player;
    List<SnowPlowListener> snowPlowListeners = new ArrayList<>();

    private static final List<ShopItem> shopItems = List.of();

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
        return shopItems;
    }

    /**
     * Az engedélyezett listából tudunk vásárolni
     * @param serial a vásárolni kívánt elem sorszáma a shop listában
     */
    public void buy(int serial){
        if(serial < 0 || serial >= shopItems.size()){
            error("Invalid shop item serial: "+serial);
            return;
        }
        
        ShopItem item = shopItems.get(serial);
        Shop shop = new Shop();
        shop.buy(player, item, 1);
    }

    /**
     * Lecseréli a hókotró fejét a megadott fejre
     * @param head a fej, amire cserélni szeretnénk
     */
    public void switchHead(IHead head){
        player.snowPlow().switchHead(head);
    }

    /**
     * Visszaadja a legális lépéseit a hókotrónak
     * @return
     */
    public List<IField> listAvailable(){
        return List.of(); // TODO:
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
    public void setNext(int serial){
        //TODO: mező beállítása
    }
}
