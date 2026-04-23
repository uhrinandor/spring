package com.spring.models.shop;

import com.spring.models.player.IPlayer;
import com.spring.models.utils.IEntity;
import com.spring.models.vehicle.ISnowPlow;

public interface RPurchaseContext extends IEntity{
     /**
     * @return Ez a metódus visszaadja a játékost aki végrehajtja a vásárlást.
     */
    public IPlayer player();

    /** 
     * @return Ez a metódus visszaadja azt a hókotrót amire történik a vásárlás.
     */
    public ISnowPlow snowPlow();
}
