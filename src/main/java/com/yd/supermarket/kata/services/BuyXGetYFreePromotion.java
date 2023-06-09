package com.yd.supermarket.kata.services;

import static com.yd.supermarket.kata.utils.CheckUtils.ensureNotEqualToZero;
import static com.yd.supermarket.kata.utils.CheckUtils.ensureSumNotEqualToZero;
import static com.yd.supermarket.kata.utils.CheckUtils.ensureType;
import static com.yd.supermarket.kata.utils.ConversionUtils.conversion;

import java.math.BigDecimal;

import com.yd.supermarket.kata.enumerations.UnitType;
import com.yd.supermarket.kata.models.Price;
import com.yd.supermarket.kata.models.Promotion;

/**
 * This class is an implementation of the interface {@link Promotion}.</br>
 * This promotion specify that if you buy X of a goods, you will have Y of the
 * same goods for free.
 * 
 * @author Yassine
 *
 */
public class BuyXGetYFreePromotion implements Promotion {

    private BigDecimal quantityToGetDiscount;   // Number of items to buy to get the discount
    private UnitType   measureUnitToGetDiscount;
    private BigDecimal quantityForFree;         // Number of items to get for free
    private UnitType   measureUnitForFree;

    public BuyXGetYFreePromotion(BigDecimal quantityToGetDiscount, UnitType measureUnitToGetDiscount,
            BigDecimal quantityForFree, UnitType measureUnitForFree) {
        this.quantityToGetDiscount    = quantityToGetDiscount;
        this.measureUnitToGetDiscount = measureUnitToGetDiscount;
        this.quantityForFree          = quantityForFree;
        this.measureUnitForFree       = measureUnitForFree;
    }

    @Override
    public BigDecimal computePriceWithPromotion(BigDecimal itemQuantity, UnitType itemMeasureUnit, Price itemPrice) {
        itemQuantity = conversion(itemQuantity, itemMeasureUnit, itemPrice.getPriceType());
        //
        ensureType(measureUnitToGetDiscount, "Quantity to get discount type");
        ensureType(measureUnitForFree, "Quantity for free type");

        ensureNotEqualToZero(quantityToGetDiscount, "quantityToGetDiscount");
        ensureNotEqualToZero(quantityForFree, "quantityForFree");
        ensureSumNotEqualToZero(quantityToGetDiscount, quantityForFree, "quantityToGetDiscount", "quantityForFree");

        BigDecimal itemPriceValue = itemPrice.getPriceValue();

        if (itemQuantity.compareTo(quantityToGetDiscount) <= 0) {
            return itemPriceValue.multiply(itemQuantity);
        }

        BigDecimal numQualifying = itemQuantity.divide((quantityToGetDiscount.add(quantityForFree)));

        return itemPriceValue.multiply(itemQuantity.subtract(numQualifying.multiply(quantityForFree)));
    }
}
