package com.yd.supermarket.kata.utils;

import java.math.BigDecimal;

import com.yd.supermarket.kata.enumerations.UnitType;

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

    /**
     * This method is to check that the parameterToCheck is not equal to zero.
     * 
     * @param parameterToCheck
     * @param parameterDisplay
     */
    public static void ensureNotEqualToZero(BigDecimal parameterToCheck, String parameterDisplay) {
        if (BigDecimal.ZERO == parameterToCheck) {
            throw new IllegalArgumentException("The parameter " + parameterDisplay + " can not be equal to 0.");
        }
    }

    /**
     * This method is to check that the sum of the firstParameterToCheck and
     * secondParameterTocheck is not equal to zero.
     * 
     * @param firstParameterToCheck
     * @param secondParameterTocheck
     * @param firstParameterDisplay
     * @param secondParameterDisplay
     */
    public static void ensureSumNotEqualToZero(BigDecimal firstParameterToCheck, BigDecimal secondParameterTocheck,
            String firstParameterDisplay, String secondParameterDisplay) {
        if (BigDecimal.ZERO == (firstParameterToCheck.add(secondParameterTocheck))) {
            throw new IllegalArgumentException("The sum of parameters : " + firstParameterDisplay + " and "
                    + secondParameterDisplay + " can not be 0.");
        }
    }

    /**
     * This method is to ensure that the parameterToCheck is not null.
     * 
     * @param parameterToCheck
     * @param parameterDisplay
     */
    public static void ensureNotNull(Object parameterToCheck, String parameterDisplay) {
        if (null == parameterToCheck) {
            throw new IllegalArgumentException("The parameter " + parameterDisplay + " can not be null.");
        }
    }

    /**
     * This method is to check that the parameters (firstUnitToCheck,
     * secondUnitTocheck) are not a mix of countable and uncountable units.
     * 
     * @param firstUnitToCheck
     * @param secondUnitTocheck
     * @param firstUnitDisplay
     * @param secondUnitDisplay
     */
    public static void ensureCompatibility(UnitType firstUnitToCheck, UnitType secondUnitTocheck,
            String firstUnitDisplay, String secondUnitDisplay) {
        if ((firstUnitToCheck == UnitType.NUMBER && secondUnitTocheck != UnitType.NUMBER)
                || (secondUnitTocheck == UnitType.NUMBER && firstUnitToCheck != UnitType.NUMBER)) {
            throw new IllegalArgumentException("The " + firstUnitDisplay + " : " + firstUnitToCheck + " and the "
                    + secondUnitDisplay + " : " + secondUnitTocheck + " are incompatible.");
        }
    }

    /**
     * This method is to check that the firstUnitToCheck is countable.
     * 
     * @param firstUnitToCheck
     * @param firstUnitDisplay
     */
    public static void ensureType(UnitType firstUnitToCheck, String firstUnitDisplay) {
        if (firstUnitToCheck != UnitType.NUMBER) {
            throw new IllegalArgumentException(
                    "The parameter " + firstUnitDisplay + " : " + firstUnitToCheck + " should be countable.");
        }
    }
    
    /**
     * This method is to check that the parameterToCheck is negative.
     * 
     * @param parameterToCheck
     * @param parameterDisplay
     */
    public static void ensureNotNegative(BigDecimal parameterToCheck, String parameterDisplay) {
        if (parameterToCheck.signum() == -1) {
            throw new IllegalArgumentException(
                    "The parameter " + parameterToCheck + " : " + parameterDisplay + " should be countable.");
        }
    }
}
