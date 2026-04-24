package com.spring.models.field;


import com.spring.models.layer.ILayer;
import com.spring.models.layer.ISalt;
import com.spring.models.vehicle.Vehicle;

/**
 * Interfész, amely az IField-ek összekötésében biztosít segítséget.
 */  
public interface IField extends IRField {
    
    public void setLayer(ILayer layer);

    /**
     * Az adott jármű be szeretne lépni az adott mezőre.
     * @param v a jármű, ami be szeretne lépni
     * @return true ha sikerült a belépés, false hogyha nem
     */
    public boolean tryEnter(Vehicle v);

    public void setVehicle(Vehicle v);
    
    public void setSalt(ISalt salt);

    /**
     * Lecseréli a Layer-t, ha van rajta só.
     */
    public void melt();

    /**
     * A Field-en tartózkodó jármű szeretne lelépni, és átlépni a megadott mezőre.
     * @param f a következő mező amire a jármű lépni szeretne
     */
    public void tryExit(IField f);

    public void setRight(IField f);

    public void setLeft(IField f);

    public void setFront(IRoad r);
}
