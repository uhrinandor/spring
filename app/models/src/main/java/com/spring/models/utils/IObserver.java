package com.spring.models.utils;

/**
 * A View-nak ezt kell implementálnia, hogy figyelje az Entity változtatásait
 */
public interface IObserver {
    public void notifyChange(IEntity entity);
}
