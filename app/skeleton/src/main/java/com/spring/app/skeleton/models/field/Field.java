package com.spring.app.skeleton.models.field;

import java.util.List;

import com.spring.app.skeleton.models.layer.ILayer;
import com.spring.app.skeleton.models.vehicle.IDriver;
import com.spring.app.skeleton.models.vehicle.Vehicle;
import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.IRandom;

public class Field extends Entity implements IField {
    
    private ILayer layer;
    private Vehicle vehicle;
    private IRoad road;
    private IField left;
    private IField right;
    private IRandom random;
    private bool underground;

    public Field(ILayer layer, Vehicle vehicle, IRoad road, IField left, IField right, IRandom random, bool underground)
    {
        this.layer=layer;
        this.vehicle=vehicle;
        this.road=road;
        this.left=left;
        this.right=right;
        this.random=random;
        this.underground=underground;
    }

    public setLeft(IField field)
    {
        left=field;
    }

    public setRight(IField field)
    {
        right=field;
    }

    public setFront(IRoad road)
    {
        this.road=road;
    }

    
    @Override
    public bool isUnderGround()
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'melt'");
    }

    @Override
    public List<String> init() {
       return List.of("layer: " + layer.toString() + "vehicle: " + vehicle.toString() +
       "road: " + road.toString() + "left: " + left.toString() + "right: " + right.toString() +
       "random: " + random.toString());
    }
    
}
