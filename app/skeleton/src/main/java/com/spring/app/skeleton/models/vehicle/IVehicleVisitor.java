package com.spring.app.skeleton.models.vehicle;

import com.spring.app.skeleton.utils.IEntity;

/**
 * Járművenként különböző módokon kezel egy adott interakciót egy réteggel.
 * pl.: IceSlipVisitor, SnowExitVisitor
 */
public interface IVehicleVisitor extends IEntity {
    /**
     * @param c Car típusú argumentum esetén meghívott függvény
     */
    public void visit(Car c);

    /**
     * @param s SnowPlow típusú argumentum esetén meghívott függvény
     */
    public void visit(Snowplow s);

    /**
     * @param b Bus típusú argumentum esetén meghívott függvény
     */
    public void visit(Bus b);
}
