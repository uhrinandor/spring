package com.spring.app.skeleton.models.layer;

import java.util.List;

import com.spring.app.skeleton.models.random.IRandom;
import com.spring.app.skeleton.models.vehicle.Vehicle;
import com.spring.app.skeleton.utils.Entity;

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
        return layer;
    }

    @Override
    public void accept(ILayerVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Ez a függvény meghatározza a réteg alapján, 
     * hogy a rálépett Vehicle megcsúszik-e vagy nem.
     * A layer osztály esetében nem fog sose megcsúszni a jármű.
     */
    @Override
    public boolean slip(Vehicle v, IRandom random) {
        return false;
    }

    /**
     * Meghatározza hogy az adott jármű ki tud-e lépni a Layerből. 
     * Ebből mindenki ki tud lépni független a járműtől.
     */
    @Override
    public boolean canExit(Vehicle v) {
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
        return this;
    }

    @Override
    public List<String> init() {
        return List.of();
    }
    
}
