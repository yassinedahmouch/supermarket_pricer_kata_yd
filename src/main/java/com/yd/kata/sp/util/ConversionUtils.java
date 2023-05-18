package com.yd.kata.sp.util;

import static com.yd.kata.sp.model.enumeration.UnitType.GRAM;
import static com.yd.kata.sp.model.enumeration.UnitType.NUMBER;
import static com.yd.kata.sp.model.enumeration.UnitType.OUNCE;
import static com.yd.kata.sp.model.enumeration.UnitType.POUND;

import java.math.BigDecimal;

import com.yd.kata.sp.model.Quantity;
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

    public static BigDecimal conversion(Quantity quantity, UnitType targetType) {
        BigDecimal resultQuantity = BigDecimal.ZERO;

        UnitType   sourceType     = quantity.getQuantityType();
        BigDecimal itemQuantity   = quantity.getQuantityValue();
        
        if (NUMBER == sourceType && NUMBER == targetType) {
            return itemQuantity;
        }

        if (GRAM == sourceType && OUNCE == targetType) {
            resultQuantity = itemQuantity.divide(BigDecimal.valueOf(28.3495));
        } else if (OUNCE == sourceType && GRAM == targetType) {
            resultQuantity = itemQuantity.multiply(BigDecimal.valueOf(28.3495));
        } else if (POUND == sourceType && OUNCE == targetType) {
            resultQuantity = itemQuantity.multiply(new BigDecimal(16));
        } else if (OUNCE == sourceType && POUND == targetType) {
            resultQuantity = itemQuantity.divide(new BigDecimal(16));
        } else if (POUND == sourceType && GRAM == targetType) {
            resultQuantity = itemQuantity.multiply(BigDecimal.valueOf(453.592));
        } else if (GRAM == sourceType && POUND == targetType) {
            resultQuantity = itemQuantity.divide(BigDecimal.valueOf(453.592));
        }
        
        return resultQuantity;
    }

}
