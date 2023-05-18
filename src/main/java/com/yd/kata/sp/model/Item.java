package com.yd.kata.sp.model;

/**
 * This class is a representation of an item from a supermarket.
 * 
 * @author Yassine
 *
 */
public class Item {

    private String    itemName;
    private Price     price;
    private Quantity  quantity;
    private Promotion promotion;

    public Item(String itemName, Price price, Quantity quantity) {
        this.itemName = itemName;
        this.price    = price;
        this.quantity = quantity;
    }

    public Item(String itemName, Price price, Quantity quantity, Promotion promotion) {
        this.itemName  = itemName;
        this.price     = price;
        this.quantity  = quantity;
        this.promotion = promotion;
    }

    public String getItemName() {
        return itemName;
    }

    public Price getPrice() {
        return price;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public Promotion getPromotion() {
        return promotion;
    }
}
