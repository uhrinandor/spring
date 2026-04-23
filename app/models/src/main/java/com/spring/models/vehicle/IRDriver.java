package com.spring.models.vehicle;

import com.spring.models.field.IField;
import com.spring.models.utils.IEntity;

public interface IRDriver extends IEntity{
    /**
     * @return Ez a metódus megadja, hogy a játékos melyik mezőn áll.
     */
    public IField getCurrent();

    /**
     * @return Ez a metódus visszaadja a játékos következő lépését.
     */
    public IField getNext();
}
