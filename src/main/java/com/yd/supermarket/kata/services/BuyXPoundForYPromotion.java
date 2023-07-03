package com.yd.supermarket.kata.services;

import static com.yd.supermarket.kata.utils.ConversionUtils.conversion;

import java.math.BigDecimal;

import com.yd.supermarket.kata.enumerations.UnitType;
import com.yd.supermarket.kata.models.Price;
import com.yd.supermarket.kata.models.Promotion;

/**
 * This class is an implementation of the interface {@link Promotion}.</br>
 * 
 * @author Yassine
 *
 */
public class BuyXPoundForYPromotion implements Promotion {

    // The minimum quantity needed to apply the promotion.
    private BigDecimal quantityPerPromotion;
    // The measure unit of the promotion.
    private UnitType   measureUnitPerPromotion;
    // The price applied for the promotion
    private BigDecimal promotionPrice;

    public BuyXPoundForYPromotion(BigDecimal quantityPerPromotion, UnitType measureUnitPerPromotion,
            BigDecimal promotionPrice) {
        this.quantityPerPromotion    = quantityPerPromotion;
        this.measureUnitPerPromotion = measureUnitPerPromotion;
        this.promotionPrice          = promotionPrice;
    }

    /**
     * {@inheritDoc} </br>
     * This method calculate the price using the promotion that specify that if you
     * buy X pound of a goods, you will have to pay only Y instead of the original
     * price.
     */
    @Override
    public BigDecimal computePriceWithPromotion(BigDecimal itemQuantity, UnitType itemMeasureUnit, Price itemPrice) {
        // Apply unit conversion to the quantity of items taken by the client.
        itemQuantity         = conversion(itemQuantity, itemMeasureUnit, itemPrice.getPriceType());
        // Apply unit conversion to the minimum quantity needed to apply the promotion.
        quantityPerPromotion = conversion(quantityPerPromotion, measureUnitPerPromotion, itemPrice.getPriceType());
        
        // We retrieve the quantity on which will be applied the promotion and the remaining quantity.
        BigDecimal numPromotions       = new BigDecimal(itemQuantity.divide(quantityPerPromotion).intValue());
        BigDecimal remainingItems      = itemQuantity.remainder(quantityPerPromotion);

        // We calculate the price of the quantity on which will be applied the promotion and the price of the remaining one.
        BigDecimal totalPromotionPrice = numPromotions.multiply(promotionPrice);
        BigDecimal remainingItemsPrice = remainingItems.multiply(itemPrice.getPriceValue());

        return totalPromotionPrice.add(remainingItemsPrice);
    }

}
