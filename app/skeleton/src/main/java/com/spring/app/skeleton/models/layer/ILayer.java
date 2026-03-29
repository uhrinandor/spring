package com.spring.app.skeleton.models.layer;

import com.spring.app.skeleton.models.random.IRandom;
import com.spring.app.skeleton.models.vehicle.Vehicle;
import com.spring.app.skeleton.utils.IEntity;

/**
 * A pályán található rétegek viselkedését leíró interfész.
 * Meghatározza a rétegek egymásra helyezésének eredményét,
 * valamint a járművekkel való kölcsönhatásukat.
 */
public interface ILayer extends IEntity {
    /**
     * Ez a metódus határozza meg, hogy milyen réteg lesz az eredmény,
     * két réteg egymásra helyezése után.
     * @param layer a jelenlegi rétegre kerülő másik réteg
     * @return eredmény réteg
     */
    public ILayer merge(ILayer layer);

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
     * @return A jármű belépésének a rétegre való hatását eredményezi.
     */
    public ILayer enter();

    /**
     * Lehetővé teszi a visitor számára, hogy műveletet hajtson végre ezen a rétegen.
     * @param visitor a réteget feldolgozó visitor objektum
     */
    public void accept(ILayerVisitor visitor);
}
