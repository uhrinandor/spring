package com.spring.app.skeleton.models.field;

import java.util.List;

import com.spring.app.skeleton.models.layer.ILayer;
import com.spring.app.skeleton.models.layer.ISalt;
import com.spring.app.skeleton.models.vehicle.Vehicle;
import com.spring.app.skeleton.utils.IEntity;

public interface IField extends IEntity {
    public IRoad getFront();
    public IField getRight();
    public ILayer getLayer();
    public void setLayer(ILayer layer);
    public List<IField> getAvailable();
    public boolean isUnderGround();
    public boolean tryEnter(Vehicle v);
    public void tryExit(IField f);
    public void setVehicle(Vehicle v);
    public void setSalt(ISalt salt);
    // FLAG: EZ MÁR NEM AKTUÁLIS
    public void interact();
    // FLAG: EZ MÁR NEM AKTUÁLIS
    public void slip();

    public void melt();
}
