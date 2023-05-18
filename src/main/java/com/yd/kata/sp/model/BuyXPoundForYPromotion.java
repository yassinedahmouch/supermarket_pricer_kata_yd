package com.yd.kata.sp.model;

import java.math.BigDecimal;

import com.yd.kata.sp.util.ConversionUtils;

/**
 * This class is an implementation of the interface {@link Promotion}.</br>
 * This promotion specify that if you buy X pound of a goods, you will have to
 * pay only Y instead of the original price.
 * 
 * @author Yassine
 *
 */
public class BuyXPoundForYPromotion implements Promotion {

    private Quantity   quantityPerPromotion;
    private BigDecimal promotionPrice;

    public BuyXPoundForYPromotion(Quantity quantityPerPromotion, BigDecimal promotionPrice) {
        this.quantityPerPromotion = quantityPerPromotion;
        this.promotionPrice       = promotionPrice;
    }

    @Override
    public BigDecimal computePriceWithPromotion(Quantity itemQuantity, Price itemPrice) {
        itemQuantity.setQuantityValue(ConversionUtils.conversion(itemQuantity, itemPrice.getPriceType()));
        quantityPerPromotion.setQuantityValue(ConversionUtils.conversion(quantityPerPromotion, itemPrice.getPriceType()));
        //
        BigDecimal quantityValue             = itemQuantity.getQuantityValue();
        BigDecimal quantityPerPromotionValue = quantityPerPromotion.getQuantityValue();
        //
        BigDecimal numPromotions             = new BigDecimal(quantityValue.divide(quantityPerPromotionValue).intValue());
        BigDecimal remainingItems            = quantityValue.remainder(quantityPerPromotionValue);

        BigDecimal totalPromotionPrice       = numPromotions.multiply(promotionPrice);
        BigDecimal remainingItemsPrice       = remainingItems.multiply(itemPrice.getPriceValue());

        return totalPromotionPrice.add(remainingItemsPrice);
    }

}
