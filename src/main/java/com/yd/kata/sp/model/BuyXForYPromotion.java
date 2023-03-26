package com.yd.kata.sp.model;

import static com.yd.kata.sp.util.CheckUtils.ensureNotEqualToZero;

import java.math.BigDecimal;

/**
 * This class is an implementation of the interface {@link Promotion}.</br>
 * This promotion specify that if you buy X of a goods, you will have to pay
 * only Y instead of the original price.
 * 
 * @author Yassine
 *
 */
public class BuyXForYPromotion implements Promotion {

    private int        quantityPerPromotion;
    private BigDecimal promotionPrice;

    public BuyXForYPromotion(int quantityPerPromotion, BigDecimal promotionPrice) {
        this.quantityPerPromotion = quantityPerPromotion;
        this.promotionPrice       = promotionPrice;
    }

    @Override
    public BigDecimal computePriceWithPromotion(int itemQuantity, BigDecimal itemPrice) {

        ensureNotEqualToZero(quantityPerPromotion, "quantityPerPromotion");

        if (null == promotionPrice) {
            throw new IllegalArgumentException("The parameter promotionPrice can not be null.");
        }

        int        numPromotions       = itemQuantity / quantityPerPromotion;
        int        remainingItems      = itemQuantity % quantityPerPromotion;

        BigDecimal totalPromotionPrice = promotionPrice.multiply(new BigDecimal(numPromotions));
        BigDecimal remainingItemsPrice = itemPrice.multiply(new BigDecimal(remainingItems));

        return totalPromotionPrice.add(remainingItemsPrice);
    }
}
