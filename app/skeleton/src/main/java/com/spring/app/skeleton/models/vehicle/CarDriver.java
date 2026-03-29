package com.spring.app.skeleton.models.vehicle;

import java.util.List;

import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.utils.Entity;
import com.spring.app.skeleton.utils.Tracer;

/**
 * Ez az osztály felel az autó irányításáért, ez számolja ki a legrövidebb utat a munkahelyéig.
 * Ebben az esetben a számítógép irányítja a járművet, nem játékos.
 */
public class CarDriver extends Entity implements IDriver {
    /**
     * A jelenlegi mező, amin áll.
     */
    IField current;
    /**
     * A következő mező, amire lépni szeretne.
     */
    IField next;

    /**
     * Ez a metódus generálja a legrövidebb utat és meghatározza az autó következő lépését.
     * @return vissza is adja ezt a mezőt
     */
    @Override
    public IField nextMove() {
        Tracer.getInstance().enterFunction(this, "nextMove");
        // TODO: ez itt a kalkulált
        Tracer.getInstance().exitFunction(next);
        return next;
    }

    /**
     * Be tudjuk állítani kívülről a következő lépést.
     * Kényszerített lépésnél tudja használni a Field.
     * @param a következő lépés
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
        current = f;
    }
}
