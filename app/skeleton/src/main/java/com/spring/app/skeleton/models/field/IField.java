package com.spring.app.skeleton.models.field;

import java.util.List;

import com.spring.app.skeleton.models.vehicle.Vehicle;
import com.spring.app.skeleton.utils.IEntity;

public interface IField extends IEntity {
    public List<IField> getAvailable();
    public boolean isUnderGround();
    public boolean tryEnter(Vehicle v);
    public boolean tryExit(IField f);

    // FLAG: EZ MÁR NEM AKTUÁLIS
    public void interact();
    // FLAG: EZ MÁR NEM AKTUÁLIS
    public void slip();

    public void melt();
}
