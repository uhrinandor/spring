package com.spring.app.skeleton.models.vehicle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import com.spring.app.skeleton.models.buildings.Office;
import com.spring.app.skeleton.models.field.IField;
import com.spring.app.skeleton.models.field.IRoad;
import com.spring.app.skeleton.utils.Tracer;

/**
 * Az autó a pálya által irányított objektum amely egy random Home mellett generálódik, célja, 
 * hogy elérje a hozzá tartozó Office-t.
 */
public class Car extends Vehicle {
    /**
     * Az autó célja.
     */
    Office destination;

    /**
     * Számláló, azt számolja, hogy ütközés után még hány körön keresztül mozgásképtelen az autó.
     */
    int immobileTurnsLeft = 0;

    public Car(IDriver driver, Office destination) {
        super(driver);
        this.destination = destination;
    }

    @Override
    public List<String> init() {
       return List.of("driver: " + driver, "destination: " + destination);  
    }

    /**
     * Megadja, hogy az autó képes-e megmozdulni
     */
    @Override
    boolean canMove() {
        Tracer.getInstance().enterFunction(this, "canMove");
        boolean tmp = Tracer.getInstance().askInt("Mennyi ideig van lerobbanva az autó?") == 0;
        Tracer.getInstance().exitFunction(tmp);
        return tmp;
    }

    /**
     * Ez a metódus felel az autó összetöréséért. Beállítja a számlálót.
     * @param v a másik jármű, amivel ez az autó ütközik.
     */
    @Override
    public void contact(Vehicle v) {
        Tracer.getInstance().enterFunction(this, "contact",v);
        immobileTurnsLeft = 1;
        Tracer.getInstance().exitFunction();
    }

    /**
     * Ha célba ér, akkor a célállomás leszedi a pályáról.
     */
    @Override
    public void interact(IField f) {
        Tracer.getInstance().enterFunction(this, "interact",f);
        if(destination.getField() != f){
            Tracer.getInstance().exitFunction();
            return;
        } 
        destination.consume(this);
        Tracer.getInstance().exitFunction();
    }
    
    /**
     * Lehetővé teszi a Visitor számára, hogy műveletet hajtson végre
     * ezen az autón. (IceSlipVisitor, SnowExitVisitor)
     * @param visitor akinek a autó engedélyezi a visit műveletet.
     */
    @Override
    public void accept(IVehicleVisitor visitor) {
        Tracer.getInstance().enterFunction(this, "accept",visitor);
        visitor.visit(this);
        Tracer.getInstance().exitFunction();
    }

    public void setDestination(Office destination)
    {
        this.destination = destination;
    }
/**
 * AI SLOP BE CAREFUL
 * @param currentField
 * @return
 */
    public List<IField> calculateRoute(IField currentField) {
        if (currentField == null || destination == null) return List.of();

        IField target = destination.getField();
        if (currentField.equals(target)) return List.of(currentField);

        Queue<List<IField>> queue = new LinkedList<>();
        Set<IField> visited = new HashSet<>();

        queue.add(new ArrayList<>(List.of(currentField)));
        visited.add(currentField);

        while (!queue.isEmpty()) {
            List<IField> path = queue.poll();
            IField last = path.get(path.size() - 1);

            for (IField neighbor : getNeighbors(last)) {
                if (visited.contains(neighbor)) continue;
                visited.add(neighbor);

                List<IField> newPath = new ArrayList<>(path);
                newPath.add(neighbor);

                if (neighbor.equals(target)) return newPath;

                queue.add(newPath);
            }
        }

        return List.of();
    }

    /**
     * Visszaadja az adott mezőről elérhető szomszédos mezőket.
     * getFront().getAvailable() kezeli mind a sima Field, mind a CrossRoad esetét.
     */
    private List<IField> getNeighbors(IField field) {
        List<IField> neighbors = new ArrayList<>();

        IRoad front = field.getFront();
        if (front != null) {
            neighbors.addAll(front.getAvailable());
        }

        IField right = field.getRight();
        if (right != null) {
            neighbors.add(right);
        }

        return neighbors;
    }
}

