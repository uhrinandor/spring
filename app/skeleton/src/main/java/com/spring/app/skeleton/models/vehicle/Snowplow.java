package com.spring.app.skeleton.models.vehicle;

import java.util.List;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.models.head.IHead;
import com.spring.app.skeleton.models.player.ICollector;
import com.spring.app.skeleton.utils.Tracer;

public class Snowplow extends Vehicle implements ISnowPlow{
    IInventory inventory;
    IHead activeHead;
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
        Tracer.getInstance().enterFunction(this, "interact",f);
        if(activeHead.interact(f, inventory)) collector.give(1);
        Tracer.getInstance().exitFunction();
    }
    

    @Override
    public void accept(IVehicleVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public IInventory getInventory() {
        return inventory;
    }
    
}
