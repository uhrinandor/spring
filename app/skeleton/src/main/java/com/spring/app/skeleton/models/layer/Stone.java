package com.spring.app.skeleton.models.layer;

import java.util.List;

import com.spring.app.skeleton.models.head.Item;
import com.spring.app.skeleton.models.random.IRandom;
import com.spring.app.skeleton.models.shop.PurchaseContext;
import com.spring.app.skeleton.models.shop.ShopItem;
import com.spring.app.skeleton.models.vehicle.IInventory;
import com.spring.app.skeleton.models.vehicle.IInventoryItem;
import com.spring.app.skeleton.models.vehicle.ISnowPlow;
import com.spring.app.skeleton.models.vehicle.Vehicle;
import com.spring.app.skeleton.utils.Entity;

public class Stone extends Entity implements ILayer, IInventoryItem, ShopItem{
    private ILayer previous;

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
    
}
