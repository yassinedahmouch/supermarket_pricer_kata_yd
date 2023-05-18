package com.yd.kata.sp.service;

import static com.yd.kata.sp.util.CheckUtils.ensureCompatibility;
import static com.yd.kata.sp.util.CheckUtils.ensureNotNull;
import static com.yd.kata.sp.util.ConversionUtils.conversion;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import com.yd.kata.sp.model.Item;
import com.yd.kata.sp.model.Price;
import com.yd.kata.sp.model.Promotion;
import com.yd.kata.sp.model.Quantity;
import com.yd.kata.sp.model.enumeration.UnitType;

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
            Price      itemPrice         = item.getPrice();
            BigDecimal itemPriceValue    = itemPrice.getPriceValue();
            UnitType   itemPriceType     = itemPrice.getPriceType();
            //
            Quantity   itemQuantity      = item.getQuantity();
            UnitType   itemQuantityType     = itemQuantity.getQuantityType();
            //
            Promotion  itemPromotion     = item.getPromotion();

            ensureCompatibility(itemPriceType, itemQuantityType, "Item price type", "Item quantity type");
            ensureNotNull(itemPriceValue, "itemPrice");

            if (null != itemPromotion) {
                itemTotalPrice = itemPromotion.computePriceWithPromotion(itemQuantity, itemPrice);
            } else {
                itemQuantity.setQuantityValue(conversion(itemQuantity, itemPriceType));
                itemTotalPrice = itemPriceValue.multiply(itemQuantity.getQuantityValue());
            }

            totalPrice = totalPrice.add(itemTotalPrice, new MathContext(4));
        }

        return totalPrice;
    }
}
