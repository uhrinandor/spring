package com.spring.models.field;
import java.util.List;

import com.spring.models.utils.IEntity;

/**
 * Az áthaladó járművek mozgását kezeli.
 */
public interface IRoad extends IEntity{
    /**
     * @return visszaadja a mezőről elérhető Field-eket
     */
    public List<IField> getAvailable();
}
