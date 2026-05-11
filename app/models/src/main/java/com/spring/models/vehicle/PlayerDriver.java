package com.spring.models.vehicle;

import java.util.List;

import com.spring.models.field.IField;
import com.spring.models.utils.Entity;
import com.spring.models.utils.Tracer;
import com.spring.models.buildings.Building;

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
        return next;
    }

    /**
     * Beállítja a játékos következő lépését.
     * @param f a következő lépés mezője
     */
    @Override
    public void setNext(IField f) {
        next = f;
    }

    @Override
    public IField getCurrent() {
      return current;
    }

    @Override
    public IField getNext() {
        return next;
    }

    @Override
    public List<String> init() {
        return List.of("current: " + current, "next: " + next);
    }

    @Override
    public void setCurrent(IField f) {
        current = f;
    }

    @Override
    public void setDestination(Building b){}
}
