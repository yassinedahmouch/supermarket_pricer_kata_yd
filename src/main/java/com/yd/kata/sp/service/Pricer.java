package com.yd.kata.sp.service;

import static com.yd.kata.sp.util.CheckUtils.ensureNotNull;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import com.yd.kata.sp.model.Item;
import com.yd.kata.sp.model.Promotion;

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
            BigDecimal itemPrice     = item.getPrice().getPriceValue();
            BigDecimal itemQuantity  = item.getQuantity().getQuantityValue();
            Promotion  itemPromotion = item.getPromotion();

            ensureNotNull(itemPrice, "itemPrice");

            if (null != itemPromotion) {
                itemTotalPrice = itemPromotion.computePriceWithPromotion(itemQuantity, itemPrice);
            } else {
                itemTotalPrice = itemPrice.multiply(itemQuantity);
            }

            totalPrice = totalPrice.add(itemTotalPrice, new MathContext(4));
        }

        return totalPrice;
    }
}
