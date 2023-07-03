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

    /**
     * 
     * @param itemQuantity    the quantity of the item taken by the client.
     * @param itemMeasureUnit the measure unit used by the client.
     * @param itemPrice       the {@link Price} of the item.
     * @return {@link BigDecimal}
     */
    public abstract BigDecimal computePriceWithPromotion(BigDecimal itemQuantity, UnitType itemMeasureUnit,
            Price itemPrice);
}
