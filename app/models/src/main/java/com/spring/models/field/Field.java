package com.spring.models.field;

import java.util.ArrayList;
import java.util.List;

import com.spring.models.layer.ILayer;
import com.spring.models.layer.ISalt;
import com.spring.models.random.IRandom;
import com.spring.models.utils.Entity;
import com.spring.models.vehicle.IDriver;
import com.spring.models.vehicle.Vehicle;

/**
 * Egy, a járművek által léphető mezőt jelent. Felelőssége a rajta lévő jármű és rétegek közötti 
 * interakció, ezektől függően a jármű kényszerített áthelyezése.
 */
public class Field extends Entity implements IField {
    
    private ILayer layer;
    private Vehicle vehicle;
    private IRoad front;
    private IField left;
    private IField right;
    private IRandom random;
    private boolean underground;
    private ISalt salt;

    
    public Field(ILayer layer, Vehicle vehicle, IRoad front, IField left, IField right, IRandom random, ISalt salt, boolean underground)
    {
        this.layer=layer;
        this.vehicle=vehicle;
        this.front=front;
        this.left=left;
        this.right=right;
        this.random=random;
        this.underground=underground;
        this.salt = salt;
    }
    
    /**
     * @return megadja, hogy mi van a mező előtt. (Lehet CrossRoad is, ezért IRoad-ot ad vissza.)
     */
    @Override
    public IRoad getFront(){
        return front;
    }

    @Override
    public IField getRight(){
        return right;
    }
    
    @Override
    public IField getLeft(){
        return left;
    }

    public void setLeft(IField field)
    {
        left=field;
    }
    
    @Override
    public ILayer getLayer(){
        return layer;
    }

    @Override
    public void setLayer(ILayer layer){
        this.layer = layer;
    }

    public void setRight(IField field)
    {
        right = field;
    }

    public void setFront(IRoad front)
    {
        this.front = front;
    }
    
    @Override
    public boolean isUnderGround()
    {
        return underground;
    }
    
    /**
     * @return visszaadja önmagát egy listában. (Azért egy listában, mert a kereszteződés esetén egynél több
     * mező lehetne a listában.)
    */
    @Override
    public List<IField> getAvailable() {
       return List.of(this);
    }

    /**
     * Egy jármű be szeretne lépni erre a mezőre. Ha már áll rajta jármű, a contact függvény
     * kezeli a járművek ütköztetését. Ha nincs, a réteggel való interaktálást végzi el (enter).
     * Ha kell, megcsúsztatja a járművet. Engedélyezi, hogy a jármű is interaktálhat vele,
     * miután sikeres volt a fellépés.
     * @param v a belépni próbáló jármű
     * @return true ha a jármű sikeresen belépett, különben false
     */
    
    @Override
    public boolean tryEnter(Vehicle v) {
        if(vehicle != null){
            vehicle.contact(v);
            v.contact(vehicle);
            return false;
        }

        layer = layer.enter();
        vehicle = v;
        IDriver driver = v.getDriver();
        driver.setCurrent(this);
        if(layer.slip(v, random)){
            List<IField> available = front.getAvailable();
            
            driver.setNext(available.get(0));
            v.step(true);
            notifyObservers();
            return true;
        }

        
        vehicle.interact(this);
        notifyObservers();
        return true;   
    }
    
    /**
     * A Field-en tartózkodó jármű szeretne lelépni. Először megnézi,
     * hogy legális-e a következő lépés, vagy hogy oldalsó mezőre lép-e.
     * majd ha igen, akkor kezdeményezi a másik mezőnél
     * hogy szeretne belépni a jármű, ha sikeres, akkor leveszi magáról a járművet.
     * @param f a mező amire a jármű lépni szeretne erről a mezőről
     */
    @Override
    public void tryExit(IField f) {
        if(!layer.canExit(vehicle)&& (f != this.right && f != this.left) ){
            return;
        } 

        if((f == this.right && !f.getLayer().canExit(vehicle)) || (f == this.left && !f.getLayer().canExit(vehicle))){
            return;
        }

        if(!f.tryEnter(vehicle)){
            return;
        } 
        vehicle = null;
        notifyObservers();
    }

    /**
     * Lecseréli a réteget, ha van rajta só.
     */
    @Override
    public void melt() {
        if(salt == null) return;

        layer = salt.melt(layer);
        notifyObservers();
    }

    @Override
    public List<String> init() {
       List<String> base =  new ArrayList<>(List.of( "vehicle: " + vehicle,
       "front: " + front, "left: " + left, "right: " + right,
       "random: " + random));

       base.add("layer: "+layer);
       for(String s : layer.init()){
            base.add("  "+s);
       }

       return base;
    }

    @Override
    public void setVehicle(Vehicle v)
    {
        this.vehicle=v;
        notifyObservers();
    }

    @Override
    public void setSalt(ISalt salt) {
        this.salt = salt;
        notifyObservers();
    }

    @Override
    public Vehicle getVehicle() {
        return vehicle;
    }

    
}
