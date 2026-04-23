package com.spring.models.vehicle;

import com.spring.models.field.IField;

/**
 * Ez az interfész határozza meg a Vehicle következő lépésének helyét. Ez autó esetében egy
 * kiszámolt érték, más esetben a játékos adja meg.
 */
public interface IDriver extends IRDriver {
    /**
     * Ebben a metódusban határozza meg a játékos a következő lépését.
     * Autó esetében ez a metódus generálja a legrövidebb utat.
     * @return visszaadja a következő lépés mezőjét
     */
    public IField nextMove();

    /**
     * Csúszás esetén beállítjuk a Vehicle következő lépését.
     * @param f az a mező, amire a jármű a következő lépésben menni fog
     */
    public void setNext(IField f);

    /**
     * Ez a metódus beállítja, hogy a játékos melyik mezőn áll.
     * @param f
     */
    public void setCurrent(IField f);
}
