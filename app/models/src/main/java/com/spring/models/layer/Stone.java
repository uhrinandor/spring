package com.spring.models.layer;

import java.util.List;

import com.spring.models.head.Item;
import com.spring.models.random.IRandom;
import com.spring.models.shop.PurchaseContext;
import com.spring.models.shop.ShopItem;
import com.spring.models.vehicle.IInventory;
import com.spring.models.vehicle.IInventoryItem;
import com.spring.models.vehicle.ISnowPlow;
import com.spring.models.vehicle.Vehicle;
import com.spring.models.utils.Entity;

public class Stone extends Entity implements ILayer, IInventoryItem, ShopItem{
    private ILayer previous;
    private int limit;

    public Stone(ILayer p){
        previous = p;
        limit = 10;
    }

    public Stone(){
        limit = 10;
    }

    public ILayer getPrevious(){
        return previous;
    }

    public void setPrevious(ILayer previous){
        this.previous = previous;
    }

    @Override
    public List<String> init() {
        return List.of("previous: " + previous);
    }

    @Override
    public int price() {
        //Ebben még nem allapodtunk meg
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'price'");
    }

    @Override
    public void apply(PurchaseContext ctx, int amount) {
        ISnowPlow sp = ctx.snowPlow();
        IInventory inv = sp.getInventory();
        inv.addItem(this, amount);
    }

    @Override
    public Item key() {
        return Item.STONE;
    }

    @Override
    public ILayer merge(ILayer layer) {
        StoneMergeVisitor visitor = new StoneMergeVisitor(this);
        layer.accept(visitor);
        ILayer result = visitor.getResult();
        return result;
    }

    @Override
    public boolean slip(Vehicle v, IRandom random) {
        return false;
    }

    @Override
    public boolean canExit(Vehicle v) {
        return true;
    }

    @Override
    public ILayer enter() {
        return this;
    }

    @Override
    public void accept(ILayerVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public int limit() {
        return limit;
    }
    
}
