package com.spring.app.skeleton.models.vehicle;

import java.util.List;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.utils.Entity;

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

    /**
     * Ebben a metódusban határozza meg a játékos a következő lépését.
     * @return a játékos következő lépése (tehát nem a megcsúszás miatti kötelező előrelépés)
     */
    @Override
    public IField nextMove() {
        //TODO: ezt itt nem a játékos határozza meg?
        return next;
    }

    /**
     * Beállítja a játékos következő lépését.
     * @param f a következő lépés mezője
     */
    @Override
    public void setNext(IField f) {
        current = next;
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
}
