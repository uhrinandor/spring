package com.spring.models.random;

import com.spring.models.utils.IEntity;

/**
 * Ez az interfész egy valószínűség bekövetkezésének kiszámolásáért felel.
 */
public interface IRandom extends IEntity{
    /**
     * Ez a metódus paraméterként kap egy tört számot,
     * és ekkora valószínűséggel tér vissza igazzal, egyébként hamissal.
     * @param probability a kapott tört szám
     * @return az eredmény
     */
    public boolean nextBool(double probability);
}
