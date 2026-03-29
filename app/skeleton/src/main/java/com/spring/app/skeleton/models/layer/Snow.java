package com.spring.app.skeleton.models.layer;

import java.util.List;

import com.spring.app.skeleton.models.random.IRandom;
import com.spring.app.skeleton.models.vehicle.Vehicle;
import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.Tracer;

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
        Tracer.getInstance().enterFunction(this, "getLevel");
        Tracer.getInstance().exitFunction(level);
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
        Tracer.getInstance().enterFunction(this, "merge",layer);
        SnowMergeVisitor visitor = new SnowMergeVisitor(this);
        Tracer.getInstance().newObject(visitor);
        layer.accept(visitor);
        ILayer result = visitor.getResult();
        Tracer.getInstance().exitFunction(result);
        return result;
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
     * Ez a függvény meghatározza a réteg alapján, hogy a rálépett Vehicle megcsúszik-e vagy nem.
     * @return A Snow osztály esetében nem tudnak megcsúszni a járművek.
     */
    @Override
    public boolean slip(Vehicle v, IRandom random) {
        Tracer.getInstance().enterFunction(this, "slip",v, random);
        Tracer.getInstance().exitFunction(false);
        return false;
    }

    /**
     * @return Meghatározza hogy az adott jármű ki tud-e lépni a Layerből. 
     * (Autó és busz magas hóban elakad, de a hókotrót nem akadályozza.)
     */
    @Override
    public boolean canExit(Vehicle v) {
        Tracer.getInstance().enterFunction(this, "canExit",v);
        SnowExitVisitor visitor = new SnowExitVisitor(this);
        Tracer.getInstance().newObject(visitor);
        v.accept(visitor);
        boolean result = visitor.getResult();
        Tracer.getInstance().exitFunction(result);
        return result;
    }

    /**
     * Az autók rálépésének hatására csökkenti a szintjét, ha a legalacsonyabb, akkor jéggé változik.
     * @return az eredmény réteg
     */
    @Override
    public ILayer enter() {
        Tracer.getInstance().enterFunction(this, "enter");

        if(Tracer.getInstance().askInt("Milyen magas a hó?") > 1){
            level--;
            Tracer.getInstance().exitFunction(this);
            return this;
        }
        Ice tmp = new Ice();
        Tracer.getInstance().newObject(tmp);
        Tracer.getInstance().exitFunction(tmp);
        return tmp;
        
    }

    @Override
    public List<String> init() {
        return List.of("level: " + level);
    }

}
