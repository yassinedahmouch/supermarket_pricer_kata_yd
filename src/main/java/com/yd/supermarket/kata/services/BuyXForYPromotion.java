package com.yd.supermarket.kata.services;

import static com.yd.supermarket.kata.utils.CheckUtils.ensureNotEqualToZero;
import static com.yd.supermarket.kata.utils.CheckUtils.ensureNotNull;
import static com.yd.supermarket.kata.utils.CheckUtils.ensureType;
import static com.yd.supermarket.kata.utils.ConversionUtils.conversion;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

import com.yd.supermarket.kata.enumerations.UnitType;
import com.yd.supermarket.kata.models.Price;
import com.yd.supermarket.kata.models.Promotion;

/**
 * This class is the buy X for Y implementation of the interface {@link Promotion}.
 * 
 * @author Yassine
 *
 */
@Component
public class BuyXForYPromotion implements Promotion {

    // The minimum quantity needed to apply the promotion.
    private BigDecimal quantityPerPromotion;
    // The measure unit of the promotion.
    private UnitType   measureUnitPerPromotion;
    // The price applied for the promotion
    private BigDecimal promotionPrice;

    public BuyXForYPromotion() {}
    public BuyXForYPromotion(BigDecimal quantityPerPromotion, UnitType measureUnitPerPromotion,
            BigDecimal promotionPrice) {
        this.quantityPerPromotion    = quantityPerPromotion;
        this.measureUnitPerPromotion = measureUnitPerPromotion;
        this.promotionPrice          = promotionPrice;
    }

    /**
     * {@inheritDoc} </br>
     * This method calculate the price using the promotion that
     * specify that if you buy X of a goods, you will have to pay only Y instead of
     * the original price.
     */
    @Override
    public BigDecimal computePriceWithPromotion(BigDecimal itemQuantity, UnitType itemMeasureUnit, Price itemPrice) {
        itemQuantity = conversion(itemQuantity, itemMeasureUnit, itemPrice.getPriceType());
        
        // We do some checks before starting the process.
        ensureType(measureUnitPerPromotion, "Quantity per promotion type");
        ensureNotEqualToZero(quantityPerPromotion, "quantityPerPromotion");
        ensureNotNull(promotionPrice, "promotionPrice");

        BigDecimal itemPriceValue      = itemPrice.getPriceValue();
        
        // We retrieve the quantity on which will be applied the promotion and the remaining quantity.
        BigDecimal numPromotions       = itemQuantity.divide(quantityPerPromotion, 0, RoundingMode.DOWN);
        BigDecimal remainingItems      = itemQuantity.remainder(quantityPerPromotion);

        // We calculate the price of the quantity on which will be applied the promotion and the price of the remaining one.
        BigDecimal totalPromotionPrice = promotionPrice.multiply(numPromotions);
        BigDecimal remainingItemsPrice = itemPriceValue.multiply(remainingItems);

        return totalPromotionPrice.add(remainingItemsPrice);
    }
}
