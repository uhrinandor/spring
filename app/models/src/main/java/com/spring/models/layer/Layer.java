package com.spring.models.layer;

import java.util.List;

import com.spring.models.random.IRandom;
import com.spring.models.vehicle.Vehicle;
import com.spring.models.utils.Entity;
import com.spring.models.utils.Tracer;

/**
 * Aszfaltként interaktál a járművekkel.
 */
public class Layer extends Entity implements ILayer {

    /**
     * Ez a metódus határozza meg, hogy milyen réteg lesz az eredmény,
     * két réteg egymásra helyezése után.
     */
    @Override
    public ILayer merge(ILayer layer) {
        Tracer.getInstance().enterFunction(this, "merge",layer);
        Tracer.getInstance().exitFunction(layer);
        return layer;
    }

    /**
     * Lehetővé teszi a visitor számára, hogy műveletet hajtson végre ezen a rétegen.
     * @param visitor a réteget feldolgozó visitor objektum
     */
    @Override
    public void accept(ILayerVisitor visitor) {
        Tracer.getInstance().enterFunction(this, "accept",visitor);
        visitor.visit(this);
        Tracer.getInstance().exitFunction();
    }

    /**
     * Ez a függvény meghatározza a réteg alapján, 
     * hogy a rálépett Vehicle megcsúszik-e vagy nem.
     * A layer osztály esetében nem fog sose megcsúszni a jármű.
     */
    @Override
    public boolean slip(Vehicle v, IRandom random) {
        Tracer.getInstance().enterFunction(this, "slip",v,random);
        Tracer.getInstance().exitFunction(false);
        return false;
    }

    /**
     * Meghatározza hogy az adott jármű ki tud-e lépni a Layerből. 
     * Ebből mindenki ki tud lépni független a járműtől.
     */
    @Override
    public boolean canExit(Vehicle v) {
        Tracer.getInstance().enterFunction(this, "canExit",v);
        Tracer.getInstance().exitFunction(true);
        return true;
    }

    /**
     * A jármű belépésének a rétegre való hatását eredményezi.
     * @return Nincs változás, visszatér saját magával.
     * (Egy jármű más esetben ebben a metódusban taposná le, ha hó lenne.
     * Aszfaltot nem lehet letaposni.)
     */
    @Override
    public ILayer enter() {
        Tracer.getInstance().enterFunction(this, "enter");
        Tracer.getInstance().exitFunction(this);
        return this;
    }

    @Override
    public List<String> init() {
        return List.of();
    }
    
}
