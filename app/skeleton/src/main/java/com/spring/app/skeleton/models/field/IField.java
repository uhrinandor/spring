package com.spring.app.skeleton.models.field;


import com.spring.app.skeleton.models.layer.ILayer;
import com.spring.app.skeleton.models.layer.ISalt;
import com.spring.app.skeleton.models.vehicle.Vehicle;

/**
 * Interfész, amely az IField-ek összekötésében biztosít segítséget.
 */
public interface IField extends IRoad {
    /**
     * @return megadja, hogy mi van a mező előtt. (Lehet CrossRoad is, ezért IRoad-ot ad vissza.)
     */
    public IRoad getFront();

    public IField getRight();
    public ILayer getLayer();
    public void setLayer(ILayer layer);

    /**
     * @return megadja hogy a mező föld alatt van-e
     */
    public boolean isUnderGround();

    /**
     * Az adott jármű be szeretne lépni az adott mezőre.
     * @param v a jármű, ami be szeretne lépni
     * @return true ha sikerült a belépés, false hogyha nem
     */
    public boolean tryEnter(Vehicle v);

    /**
     * A Field-en tartózkodó jármű szeretne lelépni, és átlépni a megadott mezőre.
     * @param f a következő mező amire a jármű lépni szeretne
     */
    public void tryExit(IField f);

    public void setVehicle(Vehicle v);
    public Vehicle getVehicle();
    public void setSalt(ISalt salt);

    /**
     * Lecseréli a Layer-t, ha van rajta só.
     */
    public void melt();
}
