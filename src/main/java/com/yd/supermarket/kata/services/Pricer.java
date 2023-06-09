package com.yd.supermarket.kata.services;

import static com.yd.supermarket.kata.utils.CheckUtils.ensureCompatibility;
import static com.yd.supermarket.kata.utils.CheckUtils.ensureNotNull;
import static com.yd.supermarket.kata.utils.ConversionUtils.conversion;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import com.yd.supermarket.kata.enumerations.UnitType;
import com.yd.supermarket.kata.models.Item;
import com.yd.supermarket.kata.models.Price;
import com.yd.supermarket.kata.models.Promotion;

/**
 * This class is used for pricing a basket of goods.
 * 
 * @author Yassine
 *
 */
public class Pricer {

    public BigDecimal computeTotalPrice(List<Item> items) {

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
            Promotion  itemPromotion   = item.getPromotion();

            ensureCompatibility(itemPriceType, itemMeasureUnit, "Item price type", "Item quantity type");
            ensureNotNull(itemPriceValue, "itemPrice");

            if (null != itemPromotion) {
                itemTotalPrice = itemPromotion.computePriceWithPromotion(itemQuantity, itemMeasureUnit, itemPrice);
            } else {
                itemQuantity   = conversion(itemQuantity, itemMeasureUnit, itemPriceType);
                itemTotalPrice = itemPriceValue.multiply(itemQuantity);
            }

            totalPrice = totalPrice.add(itemTotalPrice, new MathContext(4));
        }

        return totalPrice;
    }
}
