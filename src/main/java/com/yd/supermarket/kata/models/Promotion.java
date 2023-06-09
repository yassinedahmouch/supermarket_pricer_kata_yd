package com.yd.supermarket.kata.models;

import java.math.BigDecimal;

import com.yd.supermarket.kata.enumerations.UnitType;

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
