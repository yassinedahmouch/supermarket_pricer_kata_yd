package com.yd.kata.sp.model;

import java.math.BigDecimal;

import com.yd.kata.sp.model.enumeration.UnitType;

/**
 * This class is a representation of an item from a supermarket.
 * 
 * @author Yassine
 *
 */
public class Item {

    private String     itemName;
    private Price      price;
    private BigDecimal quantity;
    private UnitType   measureUnit;
    private Promotion  promotion;

    public Item(String itemName, Price price, BigDecimal quantity, UnitType measureUnit) {
        this.itemName    = itemName;
        this.price       = price;
        this.quantity    = quantity;
        this.measureUnit = measureUnit;
    }

    public Item(String itemName, Price price, BigDecimal quantity, UnitType measureUnit, Promotion promotion) {
        this.itemName    = itemName;
        this.price       = price;
        this.quantity    = quantity;
        this.measureUnit = measureUnit;
        this.promotion   = promotion;
    }

    public String getItemName() {
        return itemName;
    }

    public Price getPrice() {
        return price;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public UnitType getMeasureUnit() {
        return measureUnit;
    }

    public Promotion getPromotion() {
        return promotion;
    }
}
