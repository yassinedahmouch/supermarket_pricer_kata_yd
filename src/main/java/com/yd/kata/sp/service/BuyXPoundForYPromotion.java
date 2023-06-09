package com.yd.kata.sp.service;

import java.math.BigDecimal;

import com.yd.kata.sp.model.Price;
import com.yd.kata.sp.model.Promotion;
import com.yd.kata.sp.model.enumeration.UnitType;
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

    private BigDecimal quantityPerPromotion;
    private UnitType   measureUnitPerPromotion;
    private BigDecimal promotionPrice;

    public BuyXPoundForYPromotion(BigDecimal quantityPerPromotion, UnitType measureUnitPerPromotion,
            BigDecimal promotionPrice) {
        this.quantityPerPromotion    = quantityPerPromotion;
        this.measureUnitPerPromotion = measureUnitPerPromotion;
        this.promotionPrice          = promotionPrice;
    }

    @Override
    public BigDecimal computePriceWithPromotion(BigDecimal itemQuantity, UnitType itemMeasureUnit, Price itemPrice) {
        itemQuantity         = ConversionUtils.conversion(itemQuantity, itemMeasureUnit, itemPrice.getPriceType());
        quantityPerPromotion = ConversionUtils.conversion(quantityPerPromotion, measureUnitPerPromotion,
                itemPrice.getPriceType());
        //
        BigDecimal numPromotions       = new BigDecimal(itemQuantity.divide(quantityPerPromotion).intValue());
        BigDecimal remainingItems      = itemQuantity.remainder(quantityPerPromotion);

        BigDecimal totalPromotionPrice = numPromotions.multiply(promotionPrice);
        BigDecimal remainingItemsPrice = remainingItems.multiply(itemPrice.getPriceValue());

        return totalPromotionPrice.add(remainingItemsPrice);
    }

}
