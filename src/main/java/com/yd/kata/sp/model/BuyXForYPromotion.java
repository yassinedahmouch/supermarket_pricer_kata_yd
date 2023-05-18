package com.yd.kata.sp.model;

import static com.yd.kata.sp.util.CheckUtils.ensureNotEqualToZero;
import static com.yd.kata.sp.util.CheckUtils.ensureNotNull;
import static com.yd.kata.sp.util.CheckUtils.ensureType;
import static com.yd.kata.sp.util.ConversionUtils.conversion;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.yd.kata.sp.model.enumeration.UnitType;

/**
 * This class is an implementation of the interface {@link Promotion}.</br>
 * This promotion specify that if you buy X of a goods, you will have to pay
 * only Y instead of the original price.
 * 
 * @author Yassine
 *
 */
public class BuyXForYPromotion implements Promotion {

    private Quantity   quantityPerPromotion;
    private BigDecimal promotionPrice;

    public BuyXForYPromotion(Quantity quantityPerPromotion, BigDecimal promotionPrice) {
        this.quantityPerPromotion = quantityPerPromotion;
        this.promotionPrice       = promotionPrice;
    }

    @Override
    public BigDecimal computePriceWithPromotion(Quantity itemQuantity, Price itemPrice) {
        itemQuantity.setQuantityValue(conversion(itemQuantity, itemPrice.getPriceType()));
        //
        UnitType quantityPerPromotionType = quantityPerPromotion.getQuantityType();
        ensureType(quantityPerPromotionType, "Quantity per promotion type");

        BigDecimal quantityPerPromotionValue = quantityPerPromotion.getQuantityValue();

        ensureNotEqualToZero(quantityPerPromotionValue, "quantityPerPromotion");
        ensureNotNull(promotionPrice, "promotionPrice");

        BigDecimal itemQuantityValue   = itemQuantity.getQuantityValue();
        BigDecimal itemPriceValue      = itemPrice.getPriceValue();
        //
        BigDecimal numPromotions       = itemQuantityValue.divide(quantityPerPromotionValue, 0, RoundingMode.HALF_UP);
        BigDecimal remainingItems      = itemQuantityValue.remainder(quantityPerPromotionValue);

        BigDecimal totalPromotionPrice = promotionPrice.multiply(numPromotions);
        BigDecimal remainingItemsPrice = itemPriceValue.multiply(remainingItems);

        return totalPromotionPrice.add(remainingItemsPrice);
    }
}
