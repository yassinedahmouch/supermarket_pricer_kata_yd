package com.yd.supermarket.kata.services;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.yd.supermarket.kata.enumerations.UnitType;
import com.yd.supermarket.kata.models.Item;
import com.yd.supermarket.kata.models.Price;

import junit.framework.TestCase;

/**
 * Test class of Pricer.
 * 
 * @author Yassine
 *
 */
public class PricerTest extends TestCase {

    List<Item> items;
    List<Item> itemsWithPromotionBuyXForY;
    List<Item> itemsWithPromotionBuyXGetYFree;
    List<Item> weightedItem;
    List<Item> itemsWithPromotionBuyXPoundForY;

    @Override
    protected void setUp() throws Exception {
        items = new ArrayList<>();

        Item waterBottleItem = new Item("water bottle", new Price(UnitType.NUMBER, new BigDecimal(1)),
                new BigDecimal(2), UnitType.NUMBER);
        Item pastaPackItem   = new Item("pasta pack", new Price(UnitType.NUMBER, new BigDecimal(2)), new BigDecimal(1),
                UnitType.NUMBER);

        items.add(waterBottleItem);
        items.add(pastaPackItem);

        itemsWithPromotionBuyXForY = new ArrayList<>();

        Item beanCanItem = new Item("bean can", new Price(UnitType.NUMBER, new BigDecimal(1)), new BigDecimal(4),
                UnitType.NUMBER, new BuyXForYPromotion(new BigDecimal(3), UnitType.NUMBER, new BigDecimal(1)));

        itemsWithPromotionBuyXForY.add(beanCanItem);

        itemsWithPromotionBuyXGetYFree = new ArrayList<>();

        Item milkBottleItem = new Item("milk bottle", new Price(UnitType.NUMBER, new BigDecimal(1)), new BigDecimal(3),
                UnitType.NUMBER,
                new BuyXGetYFreePromotion(new BigDecimal(2), UnitType.NUMBER, new BigDecimal(1), UnitType.NUMBER));

        itemsWithPromotionBuyXGetYFree.add(milkBottleItem);

        weightedItem = new ArrayList<>();

        Item orangeItem = new Item("orange", new Price(UnitType.POUND, new BigDecimal(1.99)), new BigDecimal(4),
                UnitType.OUNCE);

        weightedItem.add(orangeItem);

        itemsWithPromotionBuyXPoundForY = new ArrayList<>();

        Item lensItem = new Item("tomato", new Price(UnitType.OUNCE, new BigDecimal(0.1)), new BigDecimal(3),
                UnitType.POUND, new BuyXPoundForYPromotion(new BigDecimal(2), UnitType.POUND, new BigDecimal(1)));

        itemsWithPromotionBuyXPoundForY.add(lensItem);

    }

    @Test
    public void testPricer() {

        Pricer     pricer = new Pricer();
        BigDecimal total  = pricer.computeTotalPrice(items);

        assertEquals(new BigDecimal(4), total);
    }

    @Test
    public void testPricerWithPromotionBuyXForY() {

        Pricer     pricer = new Pricer();
        BigDecimal total  = pricer.computeTotalPrice(itemsWithPromotionBuyXForY);

        assertEquals(new BigDecimal(2), total);
    }

    @Test
    public void testPricerWithPromotionBuyXGetYFree() {

        Pricer     pricer = new Pricer();
        BigDecimal total  = pricer.computeTotalPrice(itemsWithPromotionBuyXGetYFree);

        assertEquals(new BigDecimal(2), total);
    }

    @Test
    public void testPricerWithWeightedItem() {

        Pricer     pricer = new Pricer();
        BigDecimal total  = pricer.computeTotalPrice(weightedItem);

        assertEquals(new BigDecimal(0.4975, new MathContext(4)), total);
    }

    @Test
    public void testPricerWithPromotionBuyXPoundForY() {

        Pricer     pricer = new Pricer();
        BigDecimal total  = pricer.computeTotalPrice(itemsWithPromotionBuyXPoundForY);

        assertEquals(new BigDecimal(2.60, new MathContext(4)), total);
    }
}
