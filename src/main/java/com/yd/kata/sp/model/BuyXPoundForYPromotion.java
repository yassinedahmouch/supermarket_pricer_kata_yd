package com.yd.kata.sp.model;

import java.math.BigDecimal;

public class BuyXPoundForYPromotion implements Promotion {

    private Quantity   quantityPerPromotion;
    private BigDecimal promotionPrice;

    public BuyXPoundForYPromotion(Quantity quantityPerPromotion, BigDecimal promotionPrice) {
        this.quantityPerPromotion = quantityPerPromotion;
        this.promotionPrice       = promotionPrice;
    }

    @Override
    public BigDecimal computePriceWithPromotion(Quantity itemQuantity, Price itemPrice) {
        return BigDecimal.ZERO;
    }

}
