package com.spring.app.skeleton.models.vehicle;

import com.spring.app.skeleton.models.head.IHead;
import com.spring.app.skeleton.utils.IEntity;

/**
 * Ez az interfész felel a hókotró fejcseréléséért.
 */
public interface ISnowPlow extends IEntity {
    /**
     * Ez a metódus kezeli a hókotró fejének a cserélését.
     * A leszedett fejet visszahelyezi a hókotró inventory-jába
     * és az újat kiveszi onnan és ráteszi a hókotróra.
     * @param head a fej amire szeretnénk lecserélni az aktuális hókotrófejet
     * @return true ha sikerült, false ha nem volt ilyen hókotrófej az Inventory-ban
     */
    public boolean switchHead(IHead head);

    /**
     * @return visszaadja a hókotró Inventory-ját
     */
    public IInventory getInventory();
}
