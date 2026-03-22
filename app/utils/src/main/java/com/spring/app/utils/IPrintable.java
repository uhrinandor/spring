package com.spring.app.utils;

import java.util.List;

public interface IPrintable {
    // Feladata: visszaadjon egy string listát, ami a példány adatait tartalmazza
    /* Formátum:
    - property: value
    - property: value
     */
    List<String> init();
}
