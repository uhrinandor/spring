package com.spring.models.layer;

import com.spring.models.random.IRandom;
import com.spring.models.utils.IEntity;
import com.spring.models.vehicle.Vehicle;

public interface IRLayer extends IEntity{
    /**
     * Ez a függvény meghatározza a réteg alapján, hogy a rálépett Vehicle megcsúszik-e vagy nem.
     * @param v a jármű, amely a rétegre lépett
     * @param random jég esetén meghatározza, hogy mekkora eséllyel csúszik meg rajta az autó/busz
     * @return true, ha a jármű megcsúszik, különben false
     */
    public boolean slip(Vehicle v, IRandom random);

    /**
     * Meghatározza hogy az adott jármű ki tud-e lépni a Layerből.
     * @param v a jármű, amely ki szeretne lépni
     * @return true, ha a jármű kiléphet, különben false (pl. magas hó)
     */
    public boolean canExit(Vehicle v);

    /**
     * Lehetővé teszi a visitor számára, hogy műveletet hajtson végre ezen a rétegen.
     * @param visitor a réteget feldolgozó visitor objektum
     */
    public void accept(ILayerVisitor visitor);
}
