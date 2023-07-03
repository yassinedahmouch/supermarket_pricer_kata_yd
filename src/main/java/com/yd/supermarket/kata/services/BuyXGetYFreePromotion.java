package com.yd.supermarket.kata.services;

import static com.yd.supermarket.kata.utils.CheckUtils.ensureNotEqualToZero;
import static com.yd.supermarket.kata.utils.CheckUtils.ensureSumNotEqualToZero;
import static com.yd.supermarket.kata.utils.CheckUtils.ensureType;
import static com.yd.supermarket.kata.utils.ConversionUtils.conversion;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

import com.yd.supermarket.kata.enumerations.UnitType;
import com.yd.supermarket.kata.models.Price;
import com.yd.supermarket.kata.models.Promotion;

/**
 * This class is the buy X get Y free implementation of the interface {@link Promotion}.
 * 
 * @author Yassine
 *
 */
@Component
public class BuyXGetYFreePromotion implements Promotion {

    // Number of items to buy to get the discount
    private BigDecimal quantityToGetDiscount;
    private UnitType   measureUnitToGetDiscount;
    // Number of items to get for free
    private BigDecimal quantityForFree;
    private UnitType   measureUnitForFree;
    
    public BuyXGetYFreePromotion() {}

    public BuyXGetYFreePromotion(BigDecimal quantityToGetDiscount, UnitType measureUnitToGetDiscount,
            BigDecimal quantityForFree, UnitType measureUnitForFree) {
        this.quantityToGetDiscount    = quantityToGetDiscount;
        this.measureUnitToGetDiscount = measureUnitToGetDiscount;
        this.quantityForFree          = quantityForFree;
        this.measureUnitForFree       = measureUnitForFree;
    }

    /**
     * {@inheritDoc} </br>
     * This method calculate the price using the promotion that specify that if you
     * buy X of a goods, you will have Y of the same goods for free.
     */
    @Override
    public BigDecimal computePriceWithPromotion(BigDecimal itemQuantity, UnitType itemMeasureUnit, Price itemPrice) {
        itemQuantity = conversion(itemQuantity, itemMeasureUnit, itemPrice.getPriceType());
        
        // We do some checks before starting the process.
        ensureType(measureUnitToGetDiscount, "Quantity to get discount type");
        ensureType(measureUnitForFree, "Quantity for free type");
        ensureNotEqualToZero(quantityToGetDiscount, "quantityToGetDiscount");
        ensureNotEqualToZero(quantityForFree, "quantityForFree");
        ensureSumNotEqualToZero(quantityToGetDiscount, quantityForFree, "quantityToGetDiscount", "quantityForFree");

        BigDecimal itemPriceValue = itemPrice.getPriceValue();

        // If the item quantity is less than the quantity to get discount, so there isn't enough quantity to apply the promotion.
        if (itemQuantity.compareTo(quantityToGetDiscount) <= 0) {
            return itemPriceValue.multiply(itemQuantity);
        }

        // Calculate the number of qualifying items.
        BigDecimal numQualifying = itemQuantity.divide((quantityToGetDiscount.add(quantityForFree)), 0, RoundingMode.DOWN);

        return itemPriceValue.multiply(itemQuantity.subtract(numQualifying.multiply(quantityForFree)));
    }
}
