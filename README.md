## Spring - Szoftver projekt laboratórium

### 2025/26/2

# Skeleton

## Tracer használata

Lesz egy közös Entity osztályunk, amivel az id-kat tudjuk követni.
toString() felül van írva, `ClsName@ID` formátumot követ, így tudjuk majd követni
kire hívunk mit.

### Tracer.info(String message)

Információra szolgál, mindig behúzást követi.

### Tracer.input(String message)

Input előtti template kérdés, csak formáz: `[?] {message}: `

### Tracer.enterFunction(Entity base, String functionName, Object... params)

Függvényhívás előtt meghívandó, base az amin hívni fogjuk a hívást, ezt csak Entity típusokra követjük le, functionName hogy mit hívunk, Object... params pedig további listában megadhatjuk hogy milyen bemenő értékei vannak.

Példa:

```java
IField next = driver.nextMove();
IField current = driver.getCurrent();
Trace.getInstance().enterFunction(current, "tryExit", next); // Ha lenne több param akkor csak felsoroljuk így enterFunction(base, functionName, param1, param2, ...)
current.tryExit(next);
```

### Tracer.exitFunction(Object returnValue)

Amikor egy függvény végzett a dolgával, akkor a végén hívjuk meg ezt, ha van return
érték akkor adjuk meg azt is, Entity-k esetén ez `ClsName@ID` formátumú lesz.
Ha void, akkor van overload `Tracer.exitFunction()`

### Tracer.askInt(String message)

Ha bemenetről szükségünk van intre ezt használjuk.

### Tracer.askBool(String message)

Ha bemenetről szükségünk van bool-ra ezt használjuk.
