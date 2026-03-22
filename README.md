## Spring - Szoftver projekt laboratórium

### 2025/26/2

## Projects

### Model

A `com.spring.app.model` tartalmazza a teljes modellt. Hivatkozik a `com.spring.app.utils` package-re, amiben a Tracer osztály található.

### Utils

A `com.spring.app.utils` package-ben a Tracer van, ami a szekvencia logolást segíti.

### Skeleton

A `com.spring.app.skeleton` indítható package, ami egy CLI-t ad, amiben futtatni tudjuk a tesztjeinket.

## Setup

Van egy aggregátor build project, az `app/build` mappában, itt futtassátok:

```
> mvn clean package
```

Ez minden dependenciát elkészít, és utána, ha skeleton felületet akarjuk indítani.

```
> java -jar <gitRoot>/app/skeleton/target/skeleton.jar
```

A skeleton.jar egy fat jar fájl, minden plusz dependecia bele van telepítve, standalone indítható.

Csak a skeleton indítható, utils és models csak az osztályokat tartalmazzák.
