package com.yd.kata.sp.model;

import java.math.BigDecimal;

import com.yd.kata.sp.model.enumeration.UnitType;

/**
 * This interface contain a method that compute the price with discount in case
 * of a promotion.
 * 
 * @author Yassine
 *
 */
public interface Promotion {

    public abstract BigDecimal computePriceWithPromotion(BigDecimal itemQuantity, UnitType itemMeasureUnit,
            Price itemPrice);
}
