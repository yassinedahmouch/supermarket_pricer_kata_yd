package com.yd.kata.sp.util;

public class CheckUtils {

    private CheckUtils() {
        // Hidden constructor
    }

    public static void ensureNotEqualToZero(int parameterToCheck, String parameterDisplay) {
        if (0 == parameterToCheck) {
            throw new IllegalArgumentException("The parameter " + parameterDisplay + " can not be equal to 0.");
        }
    }

    public static void ensureSumNotEqualToZero(int firstParameterToCheck, int secondParameterTocheck,
            String firstParameterDisplay, String secondParameterDisplay) {
        if (0 == (firstParameterToCheck + secondParameterTocheck)) {
            throw new IllegalArgumentException("The sum of parameters : " + firstParameterDisplay + " and "
                    + secondParameterDisplay + " can not be 0.");
        }
    }
}
