package com.spring.app.skeleton.models.layer;

import java.util.List;

import com.spring.app.skeleton.models.random.IRandom;
import com.spring.app.skeleton.models.vehicle.Bus;
import com.spring.app.skeleton.models.vehicle.Car;
import com.spring.app.skeleton.models.vehicle.IVehicleVisitor;
import com.spring.app.skeleton.models.vehicle.Snowplow;
import com.spring.app.skeleton.utils.Entity;

/**
 * Megvalósítja a jeges úton történő előre csúszást.
 */
public class IceSlipVisitor extends Entity implements IVehicleVisitor {

    /**
     * Megadja, hogy megcsúszhat-e a jármű a jégen.
     */
    private boolean result;

    /**
     * Valamilyen eséllyel csúsznak meg rajta az autók és buszok,
     * ehhez szükség van egy random generátorra.
     */
    private final IRandom random;

    public IceSlipVisitor(IRandom random){
        this.random = random;
        result = false;
    }

    public boolean getResult(){
        return result;
    }

    /**
     * Ha autó, akkor 25% esély van arra, hogy megcsúszik 
     */
    @Override
    public void visit(Car c) {
        result = this.random.nextBool(0.25);
    }

    /**
     * Ha hókotró, akkor biztosan nem csúszik meg
     */
    @Override
    public void visit(Snowplow s) {
       result = false;
    }

    /**
     * Ha busz, akkor 25% esély van arra, hogy megcsúszik 
     */
    @Override
    public void visit(Bus b) {
        result = this.random.nextBool(0.25);
    }

    @Override
    public List<String> init() {
        return List.of("result: "+ result, "random: " + random);
    }
    
}
