package com.yd.supermarket.kata.services;

import static com.yd.supermarket.kata.utils.CheckUtils.ensureCompatibility;
import static com.yd.supermarket.kata.utils.CheckUtils.ensureNotNegative;
import static com.yd.supermarket.kata.utils.CheckUtils.ensureNotNull;
import static com.yd.supermarket.kata.utils.ConversionUtils.conversion;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yd.supermarket.kata.enumerations.UnitType;
import com.yd.supermarket.kata.models.Item;
import com.yd.supermarket.kata.models.Price;
import com.yd.supermarket.kata.models.Promotion;

/**
 * This class define the different way to calculate items price.
 * 
 * @author Yassine
 *
 */
@Service
public class PricerService {

    @Qualifier("buyXForYPromotion")
    Promotion buyXForYPromotion;

    @Qualifier("buyXGetYFreePromotion")
    Promotion buyXGetYFreePromotion;

    @Autowired
    @Qualifier("buyXPoundForYPromotion")
    Promotion buyXPoundForYPromotion;

    @Autowired
    public void setBuyXForYPromotion(Promotion buyXForYPromotion) {
        this.buyXForYPromotion = buyXForYPromotion;
    }

    @Autowired
    public void setBuyXGetYFreePromotion(Promotion buyXGetYFreePromotion) {
        this.buyXGetYFreePromotion = buyXGetYFreePromotion;
    }

    @Autowired
    public void setBuyXPoundForYPromotion(Promotion buyXPoundForYPromotion) {
        this.buyXPoundForYPromotion = buyXPoundForYPromotion;
    }

    /**
     * This method calculate the default price without applying any promotion.
     * 
     * @param items
     * @return {@link BigDecimal}
     */
    public BigDecimal defaultPricer(List<Item> items) {

        BigDecimal totalPrice = ZERO;

        if (null == items) {
            return totalPrice;
        }

        for (Item item : items) {
            BigDecimal itemTotalPrice;
            //
            Price      itemPrice       = item.getPrice();
            BigDecimal itemPriceValue  = itemPrice.getPriceValue();
            UnitType   itemPriceType   = itemPrice.getPriceType();
            //
            BigDecimal itemQuantity    = item.getQuantity();
            UnitType   itemMeasureUnit = item.getMeasureUnit();
            //
            checks(itemPriceValue, itemPriceType, itemQuantity, itemMeasureUnit);

            itemQuantity   = conversion(itemQuantity, itemMeasureUnit, itemPriceType);
            itemTotalPrice = itemPriceValue.multiply(itemQuantity);

            totalPrice     = totalPrice.add(itemTotalPrice, new MathContext(4));
        }

        return totalPrice;
    }

    /**
     * This method calculate the price of items with applying the buy X for Y promotion.
     * 
     * @param items
     * @return {@link BigDecimal}
     */
    public BigDecimal buyXForYPromotionPricer(List<Item> items) {

        BigDecimal totalPrice = ZERO;

        if (null == items) {
            return totalPrice;
        }

        for (Item item : items) {
            BigDecimal itemTotalPrice;
            //
            Price      itemPrice       = item.getPrice();
            BigDecimal itemPriceValue  = itemPrice.getPriceValue();
            UnitType   itemPriceType   = itemPrice.getPriceType();
            //
            BigDecimal itemQuantity    = item.getQuantity();
            UnitType   itemMeasureUnit = item.getMeasureUnit();
            checks(itemPriceValue, itemPriceType, itemQuantity, itemMeasureUnit);

            itemTotalPrice = buyXForYPromotion.computePriceWithPromotion(itemQuantity, itemMeasureUnit, itemPrice);

            totalPrice     = totalPrice.add(itemTotalPrice, new MathContext(4));
        }

        return totalPrice;
    }

    /**
     * This method calculate the price of items with applying the buy X get Y free promotion.
     * 
     * @param items
     * @return {@link BigDecimal}
     */
    public BigDecimal buyXGetYFreePromotionPricer(List<Item> items) {

        BigDecimal totalPrice = ZERO;

        if (null == items) {
            return totalPrice;
        }

        for (Item item : items) {
            BigDecimal itemTotalPrice;
            //
            Price      itemPrice       = item.getPrice();
            BigDecimal itemPriceValue  = itemPrice.getPriceValue();
            UnitType   itemPriceType   = itemPrice.getPriceType();
            //
            BigDecimal itemQuantity    = item.getQuantity();
            UnitType   itemMeasureUnit = item.getMeasureUnit();
            checks(itemPriceValue, itemPriceType, itemQuantity, itemMeasureUnit);

            itemTotalPrice = buyXGetYFreePromotion.computePriceWithPromotion(itemQuantity, itemMeasureUnit, itemPrice);

            totalPrice     = totalPrice.add(itemTotalPrice, new MathContext(4));
        }

        return totalPrice;
    }

    /**
     * This method calculate the price of items with applying the buy X pound for Y promotion.
     * 
     * @param items
     * @return {@link BigDecimal}
     */
    public BigDecimal buyXPoundForYPromotionPricer(List<Item> items) {

        BigDecimal totalPrice = ZERO;

        if (null == items) {
            return totalPrice;
        }

        for (Item item : items) {
            BigDecimal itemTotalPrice;
            //
            Price      itemPrice       = item.getPrice();
            BigDecimal itemPriceValue  = itemPrice.getPriceValue();
            UnitType   itemPriceType   = itemPrice.getPriceType();
            //
            BigDecimal itemQuantity    = item.getQuantity();
            UnitType   itemMeasureUnit = item.getMeasureUnit();
            checks(itemPriceValue, itemPriceType, itemQuantity, itemMeasureUnit);

            itemTotalPrice = buyXPoundForYPromotion.computePriceWithPromotion(itemQuantity, itemMeasureUnit, itemPrice);

            totalPrice     = totalPrice.add(itemTotalPrice, new MathContext(4));
        }

        return totalPrice;
    }

    private void checks(BigDecimal itemPriceValue, UnitType itemPriceType, BigDecimal itemQuantity,
            UnitType itemMeasureUnit) {
        ensureCompatibility(itemPriceType, itemMeasureUnit, "Item price type", "Item quantity type");
        ensureNotNull(itemPriceValue, "itemPrice");
        ensureNotNegative(itemPriceValue, "Item price");
        ensureNotNegative(itemQuantity, "Item quantity");
    }
}
