package com.spring.models.vehicle;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.spring.models.field.IField;
import com.spring.models.field.IRoad;
import com.spring.models.utils.Entity;
import com.spring.models.utils.Tracer;
import com.spring.models.buildings.Building;

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


    Building destination;  // Building elég, Office-specifikus metódust nem hívunk

    @Override
    public void setDestination(Building b){
        destination = b;
    }

    /**
     * Ez a metódus generálja a legrövidebb utat és meghatározza az autó következő lépését.
     * @return vissza is adja ezt a mezőt
     */
    /**
     * BFS-sel megkeresi a legrövidebb utat a current mezőtől a destination
     * melletti mezőig, és beállítja a next-et az első lépésre.
     * Ha nincs elérhető út (pl. minden sáv járhatatlan), next változatlan marad.
     */
    @Override
    public IField nextMove() {
        Tracer.getInstance().enterFunction(this, "nextMove");

        IField target = destination.getField();

        if (current == null || target == null || current == target) {
            Tracer.getInstance().exitFunction(next);
            return next;
        }

        // BFS: IField -> honnan jutottunk ide
        Map<IField, IField> cameFrom = new LinkedHashMap<>();
        Queue<IField> queue = new ArrayDeque<>();

        cameFrom.put(current, null);
        queue.add(current);

        outer:
        while (!queue.isEmpty()) {
            IField node = queue.poll();

            for (IField neighbor : getNeighbors(node)) {
                if (cameFrom.containsKey(neighbor)) continue;
                cameFrom.put(neighbor, node);

                if (neighbor == target) break outer;
                queue.add(neighbor);
            }
        }

        // Ha nem találtuk meg a célt, next marad
        if (!cameFrom.containsKey(target)) {
            Tracer.getInstance().exitFunction(next);
            return next;
        }

        // Visszakövetjük az utat, hogy megkapjuk a current utáni első lépést
        IField step = target;
        while (cameFrom.get(step) != current) {
            step = cameFrom.get(step);
        }

        current = next;
        next = step;

        Tracer.getInstance().exitFunction(next);
        return next;
    }

    /**
     * Összegyűjti a node-ból egy lépéssel elérhető szomszédos mezőket.
     * Előre: node.getFront().getAvailable() (CrossRoad-on keresztül is)
     * Oldalra: node.getRight()
     */
    private List<IField> getNeighbors(IField node) {
        List<IField> neighbors = new ArrayList<>();

        IRoad front = node.getFront();
        if (front != null) {
            neighbors.addAll(front.getAvailable());
        }

        IField right = node.getRight();
        if (right != null) {
            neighbors.add(right);
        }

        return neighbors;
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
