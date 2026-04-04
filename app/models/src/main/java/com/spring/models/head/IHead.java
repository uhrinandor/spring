package com.spring.models.head;

import com.spring.models.field.IField;
import com.spring.models.vehicle.IInventory;
import com.spring.models.vehicle.IInventoryItem;

/**
 * Ezen interfészt felel az adott mezőn lévő réteggel történő interakcióért,
 * ilyen lehet pl.: a jégtörés, söprés stb.
 */
public interface IHead extends IInventoryItem {
    /**
     * Ez a metódus felel a hókotró és a mezőn lévő réteg interakciójának kezeléséért.
     * Fejtől és rétegtől függően más-más interakció történik.
     * @param field a mező amin a hókotró áll, az adott fejjel
     * @param inventory ez határozza meg, hogy a hókotrónál van-e elég só/biokerozin,
     * hogy tudja a fejeit használni
     * @return false ha nem sikerült letakarítani a kívánt mező(ke)t, true ha sikerült
     */
    public boolean interact(IField field, IInventory inventory);
}
