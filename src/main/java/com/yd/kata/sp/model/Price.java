package com.yd.kata.sp.model;

import java.math.BigDecimal;

import com.yd.kata.sp.model.enumeration.UnitType;

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
