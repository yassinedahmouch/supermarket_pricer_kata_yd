package com.yd.supermarket.kata.models;

import java.math.BigDecimal;

import com.yd.supermarket.kata.enumerations.UnitType;

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

    public Item() {
        super();
    }

    public Item(String itemName, Price price, BigDecimal quantity, UnitType measureUnit) {
        this.itemName    = itemName;
        this.price       = price;
        this.quantity    = quantity;
        this.measureUnit = measureUnit;
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
}
