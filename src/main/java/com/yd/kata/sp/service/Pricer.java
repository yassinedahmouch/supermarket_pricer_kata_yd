package com.yd.kata.sp.service;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import com.yd.kata.sp.model.Item;

/**
 * This class is used for pricing a basket of goods.
 * 
 * @author Yassine
 *
 */
public class Pricer {

    public BigDecimal computeTotalPrice(List<Item> items) {

        BigDecimal totalPrice = ZERO;

        if (null == items) {
            return totalPrice;
        }

        for (Item item : items) {
            BigDecimal itemTotalPrice;
            BigDecimal itemPrice    = item.getPrice();
            int        itemQuantity = item.getQuantity();

            itemTotalPrice = itemPrice.multiply(new BigDecimal(itemQuantity));
            totalPrice     = totalPrice.add(itemTotalPrice, new MathContext(4));
        }

        return totalPrice;
    }
}
