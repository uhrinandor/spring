package com.spring.prototype.menuitems;

public abstract class MenuItem {
    String title;

    protected MenuItem(String title) {
        this.title = title;
    }
    /**
     * Visszaadja a menüpont címét, ami a parancs egyben
     * @return
     */
    public String getTitle() {
        return title;
    }   

    /**
     * Megpróbálja parsolni a bemeneti stringet, ha sikerül, akkor végrehajtja a parancsot
     * @param input A parancssorról érkezett string
     * @return true, ha a parancs sikeresen végrehajtásra került, különben false
     */
    public boolean tryExecute(String input){
        boolean canExecute = parse(input);
        if(canExecute) execute();
        return canExecute;
    }

    /**
     * Ebben hívjuk meg a hozzátartozó kontrollert, hogy végrehajtsa a parancsot
     */
    public abstract void execute();

    /**
     * Megpróbálja parsolni a bemeneti stringet, ha sikerül, akkor elmenti a szükséges adatokat a parancs végrehajtásához
     * @param input A parancssorról érkezett string
     * @return true, ha a parancs sikeresen parsolásra került, különben false
     */
    public abstract boolean parse(String input);
}
