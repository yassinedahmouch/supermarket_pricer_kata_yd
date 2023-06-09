package com.yd.supermarket.kata.services;

import static com.yd.supermarket.kata.utils.CheckUtils.ensureNotEqualToZero;
import static com.yd.supermarket.kata.utils.CheckUtils.ensureNotNull;
import static com.yd.supermarket.kata.utils.CheckUtils.ensureType;
import static com.yd.supermarket.kata.utils.ConversionUtils.conversion;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.yd.supermarket.kata.enumerations.UnitType;
import com.yd.supermarket.kata.models.Price;
import com.yd.supermarket.kata.models.Promotion;

/**
 * This class is an implementation of the interface {@link Promotion}.</br>
 * This promotion specify that if you buy X of a goods, you will have to pay
 * only Y instead of the original price.
 * 
 * @author Yassine
 *
 */
public class BuyXForYPromotion implements Promotion {

    private BigDecimal quantityPerPromotion;
    private UnitType   measureUnitPerPromotion;
    private BigDecimal promotionPrice;

    public BuyXForYPromotion(BigDecimal quantityPerPromotion, UnitType measureUnitPerPromotion,
            BigDecimal promotionPrice) {
        this.quantityPerPromotion    = quantityPerPromotion;
        this.measureUnitPerPromotion = measureUnitPerPromotion;
        this.promotionPrice          = promotionPrice;
    }

    @Override
    public BigDecimal computePriceWithPromotion(BigDecimal itemQuantity, UnitType itemMeasureUnit, Price itemPrice) {
        itemQuantity = conversion(itemQuantity, itemMeasureUnit, itemPrice.getPriceType());
        //
        ensureType(measureUnitPerPromotion, "Quantity per promotion type");
        ensureNotEqualToZero(quantityPerPromotion, "quantityPerPromotion");
        ensureNotNull(promotionPrice, "promotionPrice");

        BigDecimal itemPriceValue      = itemPrice.getPriceValue();
        //
        BigDecimal numPromotions       = itemQuantity.divide(quantityPerPromotion, 0, RoundingMode.HALF_UP);
        BigDecimal remainingItems      = itemQuantity.remainder(quantityPerPromotion);

        BigDecimal totalPromotionPrice = promotionPrice.multiply(numPromotions);
        BigDecimal remainingItemsPrice = itemPriceValue.multiply(remainingItems);

        return totalPromotionPrice.add(remainingItemsPrice);
    }
}
