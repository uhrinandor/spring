package com.spring.models.field;

import java.util.ArrayList;
import java.util.List;

import com.spring.models.layer.ILayer;
import com.spring.models.layer.ISalt;
import com.spring.models.random.IRandom;
import com.spring.models.utils.Entity;
import com.spring.models.utils.Tracer;
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
        Tracer.getInstance().enterFunction(this,"getFront");
        Tracer.getInstance().exitFunction(front);
        return front;
    }

    @Override
    public IField getRight(){
        Tracer.getInstance().enterFunction(this, "getRight");
        Tracer.getInstance().exitFunction(right);
        return right;
    }
    
    @Override
    public IField getLeft(){
        Tracer.getInstance().enterFunction(this, "getLeft");
        Tracer.getInstance().exitFunction(left);
        return left;
    }

    public void setLeft(IField field)
    {
        left=field;
    }
    
    @Override
    public ILayer getLayer(){
        Tracer.getInstance().enterFunction(this, "getLayer");
        Tracer.getInstance().exitFunction(layer);
        return layer;
    }

    @Override
    public void setLayer(ILayer layer){
        Tracer.getInstance().enterFunction(this, "setLayer",layer);
        this.layer = layer;
        Tracer.getInstance().exitFunction();
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
        Tracer.getInstance().enterFunction(this, "getAvailable");
        Tracer.getInstance().exitFunction(List.of(this));
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
        Tracer.getInstance().enterFunction(this, "tryEnter", v);
        if(vehicle != null){
            vehicle.contact(v);
            v.contact(vehicle);
            Tracer.getInstance().exitFunction(false);
            return false;
        }

        layer = layer.enter();
        vehicle = v;
        
        if(layer.slip(v, random)){
            IDriver driver = v.getDriver();
            List<IField> available = front.getAvailable();
            
            driver.setNext(available.get(0));
            v.step(true);
            Tracer.getInstance().exitFunction(true);
            return true;
        }

        
        vehicle.interact(this);
        Tracer.getInstance().exitFunction(true);
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
        Tracer.getInstance().enterFunction(this, "tryExit",f);
        if(!layer.canExit(vehicle)&&(f != this.right && f != this.left)){
            Tracer.getInstance().exitFunction();
            return;
        } 

        if(!f.tryEnter(vehicle)){
            Tracer.getInstance().exitFunction();
            return;
        } 
        Tracer.getInstance().exitFunction();
        vehicle = null;
    }

    /**
     * Lecseréli a réteget, ha van rajta só.
     */
    @Override
    public void melt() {
        if(salt == null) return;

        layer = salt.melt(layer);
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
        Tracer.getInstance().enterFunction(this, "setVehicle",v);
        this.vehicle=v;
        Tracer.getInstance().exitFunction();
    }

    @Override
    public void setSalt(ISalt salt) {
        Tracer.getInstance().enterFunction(this, "setSalt",salt);
        this.salt = salt;
        Tracer.getInstance().exitFunction();
    }

    @Override
    public Vehicle getVehicle() {
        return vehicle;
    }

    
}
