package com.yd.kata.sp.model;

import static com.yd.kata.sp.util.CheckUtils.ensureNotEqualToZero;
import static com.yd.kata.sp.util.CheckUtils.ensureSumNotEqualToZero;
import static com.yd.kata.sp.util.CheckUtils.ensureType;
import static com.yd.kata.sp.util.ConversionUtils.conversion;

import java.math.BigDecimal;

import com.yd.kata.sp.model.enumeration.UnitType;

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
    public BigDecimal computePriceWithPromotion(Quantity itemQuantity, Price itemPrice) {
        itemQuantity.setQuantityValue(conversion(itemQuantity, itemPrice.getPriceType()));
        //
        UnitType quantityToGetDiscountType = quantityToGetDiscount.getQuantityType();
        ensureType(quantityToGetDiscountType, "Quantity to get discount type");

        UnitType quantityForFreeType = quantityForFree.getQuantityType();
        ensureType(quantityForFreeType, "Quantity for free type");

        BigDecimal quantityToGetDiscountValue = quantityToGetDiscount.getQuantityValue();
        BigDecimal quantityForFreeValue       = quantityForFree.getQuantityValue();

        ensureNotEqualToZero(quantityToGetDiscountValue, "quantityToGetDiscount");
        ensureNotEqualToZero(quantityForFreeValue, "quantityForFree");
        ensureSumNotEqualToZero(quantityToGetDiscountValue, quantityForFreeValue, "quantityToGetDiscount",
                "quantityForFree");

        BigDecimal itemQuantityValue = itemQuantity.getQuantityValue();
        BigDecimal itemPriceValue    = itemPrice.getPriceValue();

        if (itemQuantityValue.compareTo(quantityToGetDiscountValue) <= 0) {
            return itemPriceValue.multiply(itemQuantityValue);
        }

        BigDecimal numQualifying = itemQuantityValue.divide((quantityToGetDiscountValue.add(quantityForFreeValue)));

        return itemPriceValue.multiply(itemQuantityValue.subtract(numQualifying.multiply(quantityForFreeValue)));
    }
}
