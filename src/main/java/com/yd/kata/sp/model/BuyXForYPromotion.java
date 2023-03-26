package com.yd.kata.sp.model;

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

    @SuppressWarnings("unused")
    private int        quantityPerPromotion;
    @SuppressWarnings("unused")
    private BigDecimal promotionPrice;

    public BuyXForYPromotion(int quantityPerPromotion, BigDecimal promotionPrice) {
        this.quantityPerPromotion = quantityPerPromotion;
        this.promotionPrice       = promotionPrice;
    }

    @Override
    public BigDecimal computePriceWithPromotion(int itemQuantity, BigDecimal itemPrice) {
        return null;
    }
}
