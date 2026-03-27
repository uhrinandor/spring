package com.spring.app.skeleton.models.field;


import com.spring.app.skeleton.models.layer.ILayer;
import com.spring.app.skeleton.models.layer.ISalt;
import com.spring.app.skeleton.models.vehicle.Vehicle;

public interface IField extends IRoad {
    public IRoad getFront();
    public IField getRight();
    public ILayer getLayer();
    public void setLayer(ILayer layer);
    public boolean isUnderGround();
    public boolean tryEnter(Vehicle v);
    public void tryExit(IField f);
    public void setVehicle(Vehicle v);
    public void setSalt(ISalt salt);

    public void melt();
}
