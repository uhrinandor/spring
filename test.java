package test

class Skeleton{
    void testAutoWreck(){
        Field start = Prerequisite.generateAutoWreckMap();
        PlayerDriver pd1 = new PlayerDriver();
        Car c1 = new Car(pd1);
        Car c2 = new Car(null);
        start.setVehicle(c1);
        start.getForward().getAvailable()[0].setVehicle(c2);
        pd1.setNext(start.getAvailbale()[0]);
        c1.step(); // Ki tud-e lépni(nem törött az autó, nincs magas hó), ha igen, akkor megpróbál a következőre lépni, de ott van egy másik autó
        // ezért Field meghívja a contact(Vehicle other) 
        // c1.contact(c2)
        // c2.contact(c1)
        

    }
}

class Prerequisite{
    Field generateAutoWreckMap(){
        Field f1 = new Field(new Snow(1));
        Field f2 = new Field(new Snow(3));
        Field f3 = new Field(new Snow(1));

        f1.setForward(f2);
        f2.setForward(f3);
        return f1;
    }
}