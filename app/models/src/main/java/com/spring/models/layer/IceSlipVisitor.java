package com.spring.models.layer;

import java.util.List;

import com.spring.models.random.IRandom;
import com.spring.models.vehicle.Bus;
import com.spring.models.vehicle.Car;
import com.spring.models.vehicle.IVehicleVisitor;
import com.spring.models.vehicle.Snowplow;
import com.spring.models.utils.Entity;
import com.spring.models.utils.Tracer;

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
        boolean tmp = this.random.nextBool(0.25);
        result = tmp;
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
        boolean tmp = this.random.nextBool(0.25);
        result = tmp;
    }

    @Override
    public List<String> init() {
        return List.of("result: "+ result, "random: " + random);
    }
    
}
