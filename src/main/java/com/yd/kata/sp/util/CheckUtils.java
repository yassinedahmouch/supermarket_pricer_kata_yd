package com.yd.kata.sp.util;

import java.math.BigDecimal;

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
}
