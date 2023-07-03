package com.yd.supermarket.kata.models;

import java.math.BigDecimal;

import com.yd.supermarket.kata.enumerations.UnitType;

/**
 * This class is a representation of the price of an item.
 * 
 * @author Yassine
 *
 */
public class Price {
    private UnitType   priceType;
    private BigDecimal priceValue;

    public Price() {
        super();
    }

    public Price(UnitType priceType, BigDecimal priceValue) {
        super();
        this.priceType  = priceType;
        this.priceValue = priceValue;
    }

    public UnitType getPriceType() {
        return priceType;
    }

    public BigDecimal getPriceValue() {
        return priceValue;
    }
}
