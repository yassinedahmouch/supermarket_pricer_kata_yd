package com.yd.supermarket.kata.enumerations;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * UnitType is an enumeration of units used in this application.
 * 
 * @author Yassine
 *
 */
public enum UnitType {

    NUMBER("NUMBER"),
    GRAM("GRAM"),
    OUNCE("OUNCE"),
    POUND("POUND");
    
    private static final Map<String,UnitType> LOOK_UP = new HashMap<>();
    
    static {
        for (UnitType m : EnumSet.allOf(UnitType.class)) 
            LOOK_UP.put(m.getName(), m);
    }
    private final String name;
    
    UnitType(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }
    
    public static UnitType getUnitType(String name) {
        return LOOK_UP.get(name);
    }
}
