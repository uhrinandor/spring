package com.spring.app.skeleton.models.vehicle;

import java.util.List;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.models.head.IHead;
import com.spring.app.skeleton.models.player.ICollector;

/**
 * Felelőssége az utak tisztán tartása. Ilyen járműveket irányítanak a hókotrós játékosok.
 */
public class Snowplow extends Vehicle implements ISnowPlow{
    /**
     * Rendelkezik egy inventory-val, amiben a hókotrófejeket tartja.
     */
    IInventory inventory;

    /**
     * A fej, amely éppen fel van szerelve rá.
     */
    IHead activeHead;

    /**
     * Neki tudja átadni a pontokat, amiket szerzett.
     */
    ICollector collector;

    public Snowplow(IDriver driver, IInventory inventory, IHead defaultHead, ICollector collector) {
        super(driver);
        this.inventory = inventory;
        this.activeHead = defaultHead;
        this.collector = collector;
    }

    public Snowplow(IDriver driver) {
        super(driver);
    }

    /**
     * Kicseréli a fejet a hókotrón, a régit visszateszi az inventoryba, és kiveszi az újat(ha tudja)
     */
    @Override
    public boolean switchHead(IHead head) {
        if(inventory.removeItem(head, 1)){
            inventory.addItem(activeHead, 1);
            activeHead = head;
            return true;
        }
        return false;
    }

    @Override
    public List<String> init() {
        return List.of("inventory: " + inventory);
    }

    /**
     * Hókotró mindig tud mozogni
     */
    @Override
    boolean canMove() {
        return true;
    }

    /**
     * Hókotró nem tud összetörni
     */
    @Override
    public void contact(Vehicle v) {}

    /**
     * Hókotró interaktálás, meghívja a fejet, ha sikeres akkor a gyűjtőnek ad egy pontot
     */
    @Override
    public  void interact(IField f) {
        if(activeHead.interact(f, inventory)) collector.give(1);
    }
    
    /**
     * Lehetővé teszi a Visitor számára, hogy műveletet hajtson végre
     * ezen a hókotrón. (IceSlipVisitor, SnowExitVisitor)
     * @param visitor akinek a hókotró engedélyezi a visit műveletet.
     */
    @Override
    public void accept(IVehicleVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public IInventory getInventory() {
        return inventory;
    }
    
}
