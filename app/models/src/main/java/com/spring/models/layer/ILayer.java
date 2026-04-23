package com.spring.models.layer;

import com.spring.models.random.IRandom;
import com.spring.models.vehicle.Vehicle;
import com.spring.models.utils.IEntity;

/**
 * A pályán található rétegek viselkedését leíró interfész.
 * Meghatározza a rétegek egymásra helyezésének eredményét,
 * valamint a járművekkel való kölcsönhatásukat.
 */
public interface ILayer extends IRLayer {
    /**
     * Ez a metódus határozza meg, hogy milyen réteg lesz az eredmény,
     * két réteg egymásra helyezése után.
     * @param layer a jelenlegi rétegre kerülő másik réteg
     * @return eredmény réteg
     */
    public ILayer merge(ILayer layer);

    /**
     * @return A jármű belépésének a rétegre való hatását eredményezi.
     */
    public ILayer enter();

    
}
