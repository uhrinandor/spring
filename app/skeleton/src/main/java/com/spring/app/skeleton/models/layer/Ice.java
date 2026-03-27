package com.spring.app.skeleton.models.layer;

import java.util.List;

import com.spring.app.skeleton.models.random.IRandom;
import com.spring.app.skeleton.models.vehicle.Vehicle;
import com.spring.app.skeleton.utils.Entity;

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
        return broken;
    }

    public void setBroken(boolean tmp){
        broken = tmp;
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
        visitor.visit(this);
    }

    /**
     * Ez a függvény meghatározza a réteg alapján, hogy a rálépett Vehicle megcsúszik-e vagy nem.
     * @return a visitortól függ az eredmény
     */
    @Override
    public boolean slip(Vehicle v, IRandom random) {
        IceSlipVisitor visitor = new IceSlipVisitor(random);
        v.accept(visitor);
        return visitor.getResult();
    }

    /**
     * Meghatározza hogy az adott jármű ki tud-e lépni a Layerből.
     * @return Jégről mindig ki fogunk tudni.
     */
    @Override
    public boolean canExit(Vehicle v) {
        return true;
    }

    /**
     * @return visszatér saját magával, jeget nem tudunk letaposni.
     * (Ha hó lenne és egy jármű letaposná az itt valósulna meg.)
     */
    @Override
    public ILayer enter() {
        return this;
    }

    @Override
    public List<String> init() {
        return List.of("broken: " + broken);
    }
}
