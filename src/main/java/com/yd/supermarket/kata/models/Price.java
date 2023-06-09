package com.yd.supermarket.kata.models;

import java.math.BigDecimal;

import com.yd.supermarket.kata.enumerations.UnitType;

public class Price {
    private UnitType   priceType;
    private BigDecimal priceValue;

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
