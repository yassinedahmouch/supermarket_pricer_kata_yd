package com.yd.kata.sp.model;

import java.math.BigDecimal;

/**
 * This class is an implementation of the interface {@link Promotion}.</br>
 * This promotion specify that if you buy X of a goods, you will have Y of the
 * same goods for free.
 * 
 * @author Yassine
 *
 */
public class BuyXGetYFreePromotion implements Promotion {

    private int quantityToGetDiscount;
    private int quantityForFree;

    public BuyXGetYFreePromotion(int quantityToGetDiscount, int quantityForFree) {
        this.quantityToGetDiscount = quantityToGetDiscount;
        this.quantityForFree       = quantityForFree;
    }

    @Override
    public BigDecimal computePriceWithPromotion(int itemQuantity, BigDecimal itemPrice) {

        if (itemQuantity <= quantityToGetDiscount) {
            return itemPrice.multiply(new BigDecimal(itemQuantity));
        }

        int numQualifying = itemQuantity / (quantityToGetDiscount + quantityForFree);

        return itemPrice.multiply(new BigDecimal(itemQuantity - numQualifying * quantityForFree));
    }
}
