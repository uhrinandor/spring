package com.spring.models.vehicle;

import java.util.List;

import com.spring.models.field.IField;
import com.spring.models.head.IHead;
import com.spring.models.player.ICollector;
import com.spring.models.shop.PurchaseContext;
import com.spring.models.shop.ShopItem;
import com.spring.models.utils.Tracer;

/**
 * Felelőssége az utak tisztán tartása. Ilyen járműveket irányítanak a hókotrós játékosok.
 */
public class Snowplow extends Vehicle implements ISnowPlow, ShopItem{
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
        Tracer.getInstance().enterFunction(this, "switchHead",head);
        if(inventory.removeItem(head, 1)){
            inventory.addItem(activeHead, 1);
            activeHead = head;
            Tracer.getInstance().exitFunction(true);
            return true;
        }
        Tracer.getInstance().exitFunction(false);
        return false;
    }

    public void setCollector(ICollector ic){
        collector = ic;
    }

    public void setInventory(IInventory i){
        inventory = i;
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
    
    /**
     * Lehetővé teszi a Visitor számára, hogy műveletet hajtson végre
     * ezen a hókotrón. (IceSlipVisitor, SnowExitVisitor)
     * @param visitor akinek a hókotró engedélyezi a visit műveletet.
     */
    @Override
    public void accept(IVehicleVisitor visitor) {
        Tracer.getInstance().enterFunction(this, "accept",visitor);
        visitor.visit(this);
        Tracer.getInstance().exitFunction();
    }

    @Override
    public IInventory getInventory() {
        Tracer.getInstance().enterFunction(this, "getInventory");
        Tracer.getInstance().exitFunction(inventory);
        return inventory;
    }

    @Override
    public int price() {
        Tracer.getInstance().enterFunction(this, "price");
        int tmp = Tracer.getInstance().askInt("Mennyibe kerül a Hokotro?", 100);
        Tracer.getInstance().exitFunction(tmp);
        return tmp;
    }

    @Override
    public void apply(PurchaseContext ctx, int amount) {
        Tracer.getInstance().enterFunction(this, "apply",ctx,amount);
        ctx.addVehicle(this);
        Tracer.getInstance().exitFunction();
    }

    public void setHead(IHead h){
        activeHead = h;
    }
    
    @Override
    public IHead getHead(){
        return activeHead;
    }
    
}
