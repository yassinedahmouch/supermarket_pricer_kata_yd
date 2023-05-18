package com.yd.kata.sp.util;

import java.math.BigDecimal;

import com.yd.kata.sp.model.enumeration.UnitType;

/**
 * Utility class for checks.
 * 
 * @author Yassine
 *
 */
public class CheckUtils {

    private CheckUtils() {
        // Hidden constructor
    }

    public static void ensureNotEqualToZero(BigDecimal parameterToCheck, String parameterDisplay) {
        if (BigDecimal.ZERO == parameterToCheck) {
            throw new IllegalArgumentException("The parameter " + parameterDisplay + " can not be equal to 0.");
        }
    }

    public static void ensureSumNotEqualToZero(BigDecimal firstParameterToCheck, BigDecimal secondParameterTocheck,
            String firstParameterDisplay, String secondParameterDisplay) {
        if (BigDecimal.ZERO == (firstParameterToCheck.add(secondParameterTocheck))) {
            throw new IllegalArgumentException("The sum of parameters : " + firstParameterDisplay + " and "
                    + secondParameterDisplay + " can not be 0.");
        }
    }

    public static void ensureNotNull(Object parameterToCheck, String parameterDisplay) {
        if (null == parameterToCheck) {
            throw new IllegalArgumentException("The parameter " + parameterDisplay + " can not be null.");
        }
    }
    
    public static void ensureCompatibility(UnitType firstUnitToCheck, UnitType secondUnitTocheck,
            String firstUnitDisplay, String secondUnitDisplay) {
        if ((firstUnitToCheck == UnitType.NUMBER && secondUnitTocheck != UnitType.NUMBER)
                || (secondUnitTocheck == UnitType.NUMBER && firstUnitToCheck != UnitType.NUMBER)) {
            throw new IllegalArgumentException("The " + firstUnitDisplay + " : " + firstUnitToCheck + " and the "
                    + secondUnitDisplay + " : " + secondUnitTocheck + " are incompatible.");
        }
    }

    public static void ensureType(UnitType firstUnitToCheck, String firstUnitDisplay) {
        if (firstUnitToCheck != UnitType.NUMBER) {
            throw new IllegalArgumentException(
                    "The parameter " + firstUnitDisplay + " : " + firstUnitToCheck + " should be countable.");
        }
    }
}
