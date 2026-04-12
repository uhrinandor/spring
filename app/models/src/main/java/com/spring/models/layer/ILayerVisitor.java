package com.spring.models.layer;

import com.spring.models.utils.IEntity;

/**
 * Megvalósítja az útfelszín és a rá kerülő réteg interakcióját.
 * Külön esetre tudja bontani az ILayer interfészt megvalósító osztályokat.
 */
public interface ILayerVisitor extends IEntity {
    /**
     * @param s Snow típusú argumentum esetén meghívott függvény
     */
    public void visit(Snow s);

    /**
     * @param i Ice típusú argumentum esetén meghívott függvény
     */
    public void visit(Ice i);

    /**
     * @param l Layer típusú argumentum esetén meghívott függvény
     */
    public void visit(Layer l);

    /**
     * @param s Stone típusú argumentum esetén meghívott függvény
     */
    public void visit(Stone s);
}