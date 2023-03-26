package com.yd.kata.sp.model;

import static com.yd.kata.sp.util.CheckUtils.ensureNotEqualToZero;
import static com.yd.kata.sp.util.CheckUtils.ensureSumNotEqualToZero;

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

        ensureNotEqualToZero(quantityToGetDiscount, "quantityToGetDiscount");
        ensureNotEqualToZero(quantityForFree, "quantityForFree");
        ensureSumNotEqualToZero(quantityToGetDiscount, quantityForFree, "quantityToGetDiscount", "quantityForFree");

        if (itemQuantity <= quantityToGetDiscount) {
            return itemPrice.multiply(new BigDecimal(itemQuantity));
        }

        int numQualifying = itemQuantity / (quantityToGetDiscount + quantityForFree);

        return itemPrice.multiply(new BigDecimal(itemQuantity - numQualifying * quantityForFree));
    }
}
