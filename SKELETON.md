# Tracer guide

Az `utils` projektben elérhető néhány segéd dolog, ami kizárólag a Skeleton-hoz szükséges, valamint a szekvencia diagramokhoz, viszont mégis bele kell építeni a modellbe a feladat miatt, sajnos el van cseszve a feladat, a proxy megoldás lehet, de fel fogom vázolni mindkét lehetőséget.

## Megvalósítási ötletek

### 1. opció: Manuális hívások

Semmilyen overengineering hülyeség, szimplán meghívjuk a Tracer dolgait amikor kell
**Előny:**

- egyszerű, valószínűleg átláthatatóbb

**Hátrány:**

- nagyon, nagyon egyszerű elfelejteni
- bele kell nyúlni a

### 2. opció: Proxy/Decorator Pattern

Mivel ennyi interfészünk van, ezért akár ki is használhatjuk azzal, hogy wrapper-eket csinálunk, amik implementálják ugyanazt az interfészt, és paraméterként egy olyan service-t fogadnak, amik a valódi logikát megvalósítják. Példa:

Van egy IHead interfészünk, amit implementálni fog DragonHead, stb...

Kb így nézne ki:

```java
class DragonHead implements IHead{
    void interact(IInventory inventory, IField field){
        // Itt van valami üzleti logika
    }
}
```

Ezután írunk egy HeadTracer osztályt, amit elég egyszer megcsinálni, azt az összes IHead interfészt megvalósító osztály használni tudja(tehát a Broom, Brush, stb...)

Kb így nézne ki:

```java

class HeadTracer implements IHead{
    IHead service;

    public HeadTracer(IHead service){
        this.service = service;
    }
    void interact(IInventory inventory, IField field){
        Tracer.getInstance().enterFunction("interact("+inventory.getId()+", "+field.getId()+")");
        service.interact(inventory, field);
        Tracer.getInstance().exitFunction();
    }
}

class FieldTracer implements IField{
    IField service;
}

```

Inicializálosknál:

```java

public void test(){
    // ... preq-ből jött változók
    IHead head = new HeadTracer(new DragonHead());

    head.interact(inventory, field);
}

public IField getThreeField(){
    Field f1 = new FieldTracer(new Field());

    return f1;
}

```

**Előnyök:**

- átláthatóbb, kezelhetőbb
- interfészek miatt simán kezelhető
- üzleti logika leválasztva, amikor már a UI-al fogl
  **Hátrányok:**
- további boilerplate
- object inicializálást továbbra is manuál kell megcsinálni
- ha egy objektumon belül incializálunk egy másikat megint manuál módszer épül be, hiszen a modell-ben ezeket nem használhatjuk(visitorknál problémás csak kb.)
