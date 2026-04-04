package com.spring.models.layer;

import java.util.List;

import com.spring.models.random.IRandom;
import com.spring.models.vehicle.Vehicle;
import com.spring.models.utils.Entity;
import com.spring.models.utils.Tracer;

/**
 * Jégréteg. Autók és buszok megcsúszhatnak rajta.
 */
public class Ice extends Entity implements ILayer {

    /**
     * Megadja hogy a jég össze van-e törve.
     */
    private boolean broken;

    public Ice(boolean broken){
        this.broken = broken;
    }

    public Ice(){
        this.broken = false;
    }

    public boolean getBroken(){
        Tracer.getInstance().enterFunction(this, "getBroken");
        Tracer.getInstance().exitFunction(broken);
        return broken;
    }

    public void setBroken(boolean tmp){
        Tracer.getInstance().enterFunction(this, "setBroken",tmp);
        broken = tmp;
        Tracer.getInstance().exitFunction();
    }

    /**
     * Ez a metódus határozza meg, hogy milyen réteg lesz az eredmény,
     * két réteg egymásra helyezése után.
     * @param a másik réteg
     * @return az eredmény réteg
     */
    @Override
    public ILayer merge(ILayer layer) {
        IceMergeVisitor visitor = new IceMergeVisitor(this);
        layer.accept(visitor);
        return visitor.getResult();
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
     * @return a visitortól függ az eredmény
     */
    @Override
    public boolean slip(Vehicle v, IRandom random) {
        Tracer.getInstance().enterFunction(this, "slip",v,random);

        IceSlipVisitor visitor = new IceSlipVisitor(random);
        Tracer.getInstance().newObject(visitor);
        
        v.accept(visitor);
        boolean result = visitor.getResult();
        Tracer.getInstance().exitFunction(result);
        return result;
    }

    /**
     * Meghatározza hogy az adott jármű ki tud-e lépni a Layerből.
     * @return Jégről mindig ki fogunk tudni.
     */
    @Override
    public boolean canExit(Vehicle v) {
        Tracer.getInstance().enterFunction(this, "canExit",v);
        Tracer.getInstance().exitFunction(true);
        return true;
    }

    /**
     * @return visszatér saját magával, jeget nem tudunk letaposni.
     * (Ha hó lenne és egy jármű letaposná az itt valósulna meg.)
     */
    @Override
    public ILayer enter() {
        Tracer.getInstance().enterFunction(this, "enter");
        Tracer.getInstance().exitFunction(this);
        return this;
    }

    @Override
    public List<String> init() {
        return List.of("broken: " + broken);
    }
}
