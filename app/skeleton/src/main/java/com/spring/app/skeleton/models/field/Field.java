package com.spring.app.skeleton.models.field;

import java.util.List;

import com.spring.app.skeleton.models.layer.ILayer;
import com.spring.app.skeleton.models.layer.ISalt;
import com.spring.app.skeleton.models.random.IRandom;
import com.spring.app.skeleton.models.vehicle.IDriver;
import com.spring.app.skeleton.models.vehicle.Vehicle;
import com.spring.app.skeleton.utils.Entity;

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

    public IRoad getFront(){
        return front;
    }

    public IField getRight(){
        return right;
    }

    public void setLeft(IField field)
    {
        left=field;
    }

    public ILayer getLayer(){
        return layer;
    }

    public void setLayer(ILayer layer){
        this.layer = layer;
    }

    public void setRight(IField field)
    {
        right=field;
    }

    public void setFront(IRoad front)
    {
        this.front=front;
    }
    
    @Override
    public boolean isUnderGround()
    {
        return underground;
    }

    @Override
    public List<IField> getAvailable() {
       return List.of(this);
    }


    @Override
    public boolean tryEnter(Vehicle v) {
        if(vehicle != null){
            vehicle.contact(v);
            v.contact(vehicle);
            return false;
        }

        layer = layer.enter();

        if(layer.slip(v, random)){
            IDriver driver = v.getDriver();
            List<IField> available = front.getAvailable();
            
            driver.setNext(available.get(0));
            v.step(true);
            return true;
        }

        vehicle = v;
        vehicle.interact(this);
        
        return true;   
    }

    @Override
    public void tryExit(IField f) {
        if(!layer.canExit(vehicle)) return;

        if(!f.tryEnter(vehicle)) return;
        
        vehicle = null;
        return;
    }

    // FLAG: EZ MÁR NEM AKTUÁLIS
    @Override
    public void interact() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'interact'");
    }

    // FLAG: EZ MÁR NEM AKTUÁLIS
    @Override
    public void slip() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'slip'");
    }

    @Override
    public void melt() {
        if(salt == null) return;

        layer = salt.melt(layer);
    }

    @Override
    public List<String> init() {
       return List.of("layer: " + layer.toString() + "vehicle: " + vehicle.toString() +
       "road: " + front.toString() + "left: " + left.toString() + "right: " + right.toString() +
       "random: " + random.toString());
    }
    public void setVehicle(Vehicle v)
    {
        
    }

    @Override
    public void setSalt(ISalt salt) {
        this.salt = salt;
    }

    
}
