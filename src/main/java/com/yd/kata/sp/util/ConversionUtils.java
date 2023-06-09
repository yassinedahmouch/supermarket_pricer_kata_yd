package com.yd.kata.sp.util;

import static com.yd.kata.sp.model.enumeration.UnitType.GRAM;
import static com.yd.kata.sp.model.enumeration.UnitType.NUMBER;
import static com.yd.kata.sp.model.enumeration.UnitType.OUNCE;
import static com.yd.kata.sp.model.enumeration.UnitType.POUND;

import java.math.BigDecimal;

import com.yd.kata.sp.model.enumeration.UnitType;

/**
 * Utility class for weight conversion.
 * 
 * @author Yassine
 *
 */
public class ConversionUtils {

    private ConversionUtils() {

    }

    public static BigDecimal conversion(BigDecimal itemQuantity, UnitType itemMeasureUnit, UnitType targetType) {
        BigDecimal resultQuantity = BigDecimal.ZERO;

        if (NUMBER == itemMeasureUnit && NUMBER == targetType) {
            return itemQuantity;
        }

        if (GRAM == itemMeasureUnit && OUNCE == targetType) {
            resultQuantity = itemQuantity.divide(BigDecimal.valueOf(28.3495));
        } else if (OUNCE == itemMeasureUnit && GRAM == targetType) {
            resultQuantity = itemQuantity.multiply(BigDecimal.valueOf(28.3495));
        } else if (POUND == itemMeasureUnit && OUNCE == targetType) {
            resultQuantity = itemQuantity.multiply(new BigDecimal(16));
        } else if (OUNCE == itemMeasureUnit && POUND == targetType) {
            resultQuantity = itemQuantity.divide(new BigDecimal(16));
        } else if (POUND == itemMeasureUnit && GRAM == targetType) {
            resultQuantity = itemQuantity.multiply(BigDecimal.valueOf(453.592));
        } else if (GRAM == itemMeasureUnit && POUND == targetType) {
            resultQuantity = itemQuantity.divide(BigDecimal.valueOf(453.592));
        }

        return resultQuantity;
    }

}
