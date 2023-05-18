package com.yd.kata.sp.model;

import java.math.BigDecimal;

import com.yd.kata.sp.model.enumeration.UnitType;

public class Quantity {
    private UnitType   quantityType;
    private BigDecimal quantityValue;

    public Quantity(UnitType quantityType, BigDecimal quantityValue) {
        super();
        this.quantityType  = quantityType;
        this.quantityValue = quantityValue;
    }

    public UnitType getQuantityType() {
        return quantityType;
    }

    public BigDecimal getQuantityValue() {
        return quantityValue;
    }

    public void setQuantityValue(BigDecimal quantityValue) {
        this.quantityValue = quantityValue;
    }

}
