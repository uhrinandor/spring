package com.spring.models.layer;

import com.spring.models.utils.IEntity;

/**
 * Ez az interfész felel a só kezeléséért, mezőről történő eltűnéséért.
 */
public interface ISalt extends IEntity {
    /**
     * Ez a függvény végzi a hó vagy jég, só általi olvasztását. 
     * Paraméternek egy ILayer-t fogad és azzal a réteggel tér vissza
     * ami belőle lesz a só olvasztása után.
     * @param layer amire a só került
     * @return Az eredmény réteg
     */
    public ILayer melt(ILayer layer);

    public int getTimer();
}
