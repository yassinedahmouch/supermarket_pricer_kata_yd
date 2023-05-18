package com.yd.kata.sp.model;

import java.math.BigDecimal;

public interface Promotion {

    public abstract BigDecimal computePriceWithPromotion(Quantity itemQuantity, Price itemPrice);
}
