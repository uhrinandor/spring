package com.spring.models.field;

import com.spring.models.layer.ILayer;
import com.spring.models.vehicle.Vehicle;

public interface IRField extends IRoad{
    /**
     * @return megadja, hogy mi van a mező előtt. (Lehet CrossRoad is, ezért IRoad-ot ad vissza.)
     */
    public IRoad getFront();
    public IField getRight();
    public ILayer getLayer();

    /**
     * @return megadja hogy a mező föld alatt van-e
     */
    public boolean isUnderGround();

    public Vehicle getVehicle();

}
