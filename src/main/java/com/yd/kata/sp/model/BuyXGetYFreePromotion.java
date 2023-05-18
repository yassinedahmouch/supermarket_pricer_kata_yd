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

    private Quantity quantityToGetDiscount;
    private Quantity quantityForFree;

    public BuyXGetYFreePromotion(Quantity quantityToGetDiscount, Quantity quantityForFree) {
        this.quantityToGetDiscount = quantityToGetDiscount;
        this.quantityForFree       = quantityForFree;
    }

    @Override
    public BigDecimal computePriceWithPromotion(BigDecimal itemQuantity, BigDecimal itemPrice) {

        BigDecimal quantityToGetDiscountValue = quantityToGetDiscount.getQuantityValue();
        BigDecimal quantityForFreeValue       = quantityForFree.getQuantityValue();

        ensureNotEqualToZero(quantityToGetDiscountValue, "quantityToGetDiscount");
        ensureNotEqualToZero(quantityForFreeValue, "quantityForFree");
        ensureSumNotEqualToZero(quantityToGetDiscountValue, quantityForFreeValue, "quantityToGetDiscount", "quantityForFree");

        if (itemQuantity.compareTo(quantityToGetDiscountValue) <= 0) {
            return itemPrice.multiply(itemQuantity);
        }

        BigDecimal numQualifying = itemQuantity.divide((quantityToGetDiscountValue.add(quantityForFreeValue)));

        return itemPrice.multiply(itemQuantity.subtract(numQualifying.multiply(quantityForFreeValue)));
    }
}
