package com.yd.kata.sp.model;

import java.math.BigDecimal;

import com.yd.kata.sp.util.ConversionUtils;

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
