package com.yd.kata.sp.model;

import java.math.BigDecimal;

public class Item {

    private String     itemName;
    private BigDecimal price;
    private int        quantity;
    private Promotion  promotion;

    public Item(String itemName, BigDecimal price, int quantity) {
        this.itemName = itemName;
        this.price    = price;
        this.quantity = quantity;
    }

    public Item(String itemName, BigDecimal price, int quantity, Promotion promotion) {
        this.itemName  = itemName;
        this.price     = price;
        this.quantity  = quantity;
        this.promotion = promotion;
    }

    public String getItemName() {
        return itemName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Promotion getPromotion() {
        return promotion;
    }
}
