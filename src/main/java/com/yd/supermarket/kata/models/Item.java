package com.yd.supermarket.kata.models;

import static com.yd.supermarket.kata.utils.AtomicIdCounter.nextId;

import java.math.BigDecimal;

import com.yd.supermarket.kata.enumerations.UnitType;

/**
 * This class is a representation of an item from a supermarket.
 * 
 * @author Yassine
 *
 */
public class Item {

    private Long       id;
    private String     itemName;
    private Price      price;
    private BigDecimal quantity;
    private UnitType   measureUnit;

    public Item() {
        super();
    }

    public Item(String itemName, Price price, BigDecimal quantity, UnitType measureUnit) {
        id               = nextId();
        this.itemName    = itemName;
        this.price       = price;
        this.quantity    = quantity;
        this.measureUnit = measureUnit;
    }

    public Long getId() {
        return id;
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
