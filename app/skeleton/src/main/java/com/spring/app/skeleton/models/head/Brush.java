package com.spring.app.skeleton.models.head;

import java.util.List;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.models.layer.ILayer;
import com.spring.app.skeleton.models.layer.Layer;
import com.spring.app.skeleton.models.shop.PurchaseContext;
import com.spring.app.skeleton.models.shop.ShopItem;
import com.spring.app.skeleton.models.vehicle.IInventory;
import com.spring.app.skeleton.models.vehicle.ISnowPlow;
import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.Tracer;

public class Brush extends Entity implements IHead, ShopItem{

    @Override
    public boolean interact(IField field, IInventory inventory) {
        ILayer current = field.getLayer();
        BrushClearLayerVisitor visitor = new BrushClearLayerVisitor();
        current.accept(visitor);

        if(!visitor.getResult()) return false;

        field.setLayer(new Layer());
        IField right = field.getRight();
        ILayer rightLayer = right.getLayer();
        rightLayer = rightLayer.merge(current);
        right.setLayer(rightLayer);
        return true;
    }

    @Override
    public List<String> init() {
        return List.of();
    }

    @Override
    public Item key() {
        return Item.BRUSH;
    }

    @Override
    public int price() {
        return Tracer.getInstance().askInt("Mennyibe kerül a Brush?");
    }

    @Override
    public void apply(PurchaseContext ctx, int amount) {
        ISnowPlow sp = ctx.snowPlow();
        IInventory inventory = sp.getInventory();
        inventory.addItem(this, amount);
    }
    
}
