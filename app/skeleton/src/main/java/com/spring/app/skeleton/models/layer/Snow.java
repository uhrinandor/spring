package com.spring.app.skeleton.models.layer;

import java.util.List;

import com.spring.app.skeleton.models.random.IRandom;
import com.spring.app.skeleton.models.vehicle.Vehicle;
import com.spring.app.skeleton.utils.Entity;

/**
 * Hóréteg. Autók és buszok elakadhatnak benne.
 */
public class Snow extends Entity implements ILayer {
    /**
     * Megadja hogy a hó hány réteges.
     */
    private int level;

    public Snow(int level){
        this.level = level;
    }

    public int getLevel(){
        return level;
    }

    public void setLevel(int l){
        level = l;
    }

    /**
     * Ez a metódus határozza meg, hogy milyen réteg lesz az eredmény,
     * két réteg egymásra helyezése után.
     * @param a másik réteg
     * @return az eredmény réteg
     */
    @Override
    public ILayer merge(ILayer layer) {
        SnowMergeVisitor visitor = new SnowMergeVisitor(this);
        layer.accept(visitor);
        return visitor.getResult();
    }

    /**
     * Lehetővé teszi a visitor számára, hogy műveletet hajtson végre ezen a rétegen.
     * @param visitor a réteget feldolgozó visitor objektum
     */
    @Override
    public void accept(ILayerVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Ez a függvény meghatározza a réteg alapján, hogy a rálépett Vehicle megcsúszik-e vagy nem.
     * @return A Snow osztály esetében nem tudnak megcsúszni a járművek.
     */
    @Override
    public boolean slip(Vehicle v, IRandom random) {
        return false;
    }

    /**
     * @return Meghatározza hogy az adott jármű ki tud-e lépni a Layerből. 
     * (Autó és busz magas hóban elakad, de a hókotrót nem akadályozza.)
     */
    @Override
    public boolean canExit(Vehicle v) {
        SnowExitVisitor visitor = new SnowExitVisitor(this);
        v.accept(visitor);
        return visitor.getResult();
    }

    /**
     * Az autók rálépésének hatására csökkenti a szintjét, ha a legalacsonyabb, akkor jéggé változik.
     * @return az eredmény réteg
     */
    @Override
    public ILayer enter() {
        if(level > 1){
            level--;
            return this;
        }
        
        return new Ice(false);
        
    }

    @Override
    public List<String> init() {
        return List.of("level: " + level);
    }

}
