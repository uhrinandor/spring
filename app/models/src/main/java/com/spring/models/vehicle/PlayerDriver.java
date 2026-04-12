package com.spring.models.vehicle;

import java.util.List;

import com.spring.models.field.IField;
import com.spring.models.utils.Entity;
import com.spring.models.utils.Tracer;

/**
 * A PlayerDriver objektum felelőssége a hókotró vagy busz irányítása. Tényleges játékos 
 * irányítja a járművet, nem a számítógép.
 */
public class PlayerDriver extends Entity implements IDriver {
    /**
     * a mező amin a játékos aktuálisan áll
     */
    IField current;

    /**
     * a következő mező, amire a játékos lépni fog
     */
    IField next;

    public PlayerDriver(){}

    /**
     * Ebben a metódusban határozza meg a játékos a következő lépését.
     * @return a játékos következő lépése (tehát nem a megcsúszás miatti kötelező előrelépés)
     */
    @Override
    public IField nextMove() {
        Tracer.getInstance().enterFunction(this, "nextMove");
        //TODO: ezt itt a játékos határozza meg
        Tracer.getInstance().exitFunction(next);
        return next;
    }

    /**
     * Beállítja a játékos következő lépését.
     * @param f a következő lépés mezője
     */
    @Override
    public void setNext(IField f) {
        Tracer.getInstance().enterFunction(this, "setNext",f);
        current = next;
        next = f;
        Tracer.getInstance().exitFunction();
    }

    @Override
    public IField getCurrent() {
        Tracer.getInstance().enterFunction(this, "getCurrent");
        Tracer.getInstance().exitFunction(current);
      return current;
    }

    @Override
    public IField getNext() {
        Tracer.getInstance().enterFunction(this, "getNext");
        Tracer.getInstance().exitFunction(next);
        return next;
    }

    @Override
    public List<String> init() {
        return List.of("current: " + current, "next: " + next);
    }

    @Override
    public void setCurrent(IField f) {
        Tracer.getInstance().enterFunction(this, "setCurrent");
        Tracer.getInstance().exitFunction();
        current = f;
    }
}
