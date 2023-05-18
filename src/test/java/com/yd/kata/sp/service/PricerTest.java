package com.yd.kata.sp.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.yd.kata.sp.model.Price;
import com.yd.kata.sp.model.Quantity;
import com.yd.kata.sp.model.enumeration.UnitType;
import com.yd.kata.sp.model.BuyXPoundForYPromotion;
import com.yd.kata.sp.model.BuyXForYPromotion;
import com.yd.kata.sp.model.BuyXGetYFreePromotion;
import com.yd.kata.sp.model.Item;

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
                new Quantity(UnitType.NUMBER, new BigDecimal(2)));
        Item pastaPackItem   = new Item("pasta pack", new Price(UnitType.NUMBER, new BigDecimal(2)),
                new Quantity(UnitType.NUMBER, new BigDecimal(1)));

        items.add(waterBottleItem);
        items.add(pastaPackItem);

        itemsWithPromotionBuyXForY = new ArrayList<>();

        Item beanCanItem = new Item("bean can", new Price(UnitType.NUMBER, new BigDecimal(1)),
                new Quantity(UnitType.NUMBER, new BigDecimal(4)),
                new BuyXForYPromotion(new Quantity(UnitType.NUMBER, new BigDecimal(3)), new BigDecimal(1)));

        itemsWithPromotionBuyXForY.add(beanCanItem);

        itemsWithPromotionBuyXGetYFree = new ArrayList<>();

        Item milkBottleItem = new Item("milk bottle", new Price(UnitType.NUMBER, new BigDecimal(1)),
                new Quantity(UnitType.NUMBER, new BigDecimal(3)),
                new BuyXGetYFreePromotion(new Quantity(UnitType.NUMBER, new BigDecimal(2)),
                        new Quantity(UnitType.NUMBER, new BigDecimal(1))));

        itemsWithPromotionBuyXGetYFree.add(milkBottleItem);

        weightedItem = new ArrayList<>();

        Item orangeItem = new Item("orange", new Price(UnitType.POUND, new BigDecimal(1.99)),
                new Quantity(UnitType.OUNCE, new BigDecimal(4)));

        weightedItem.add(orangeItem);
        
        itemsWithPromotionBuyXPoundForY = new ArrayList<>();
        
        Item lensItem = new Item("tomato", new Price(UnitType.OUNCE, new BigDecimal(0.1)),
                new Quantity(UnitType.POUND, new BigDecimal(3)),
                new BuyXPoundForYPromotion(new Quantity(UnitType.POUND, new BigDecimal(2)), new BigDecimal(1)));
        
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
