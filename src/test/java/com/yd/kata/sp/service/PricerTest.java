package com.yd.kata.sp.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.yd.kata.sp.model.BuyXForYPromotion;
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
    List<Item> itemsWithPromotion;

    @Override
    protected void setUp() throws Exception {
        items = new ArrayList<>();

        Item waterBottleItem = new Item("water bottle", new BigDecimal(1), 2);
        Item pastaPackItem   = new Item("pasta pack", new BigDecimal(2), 1);

        items.add(waterBottleItem);
        items.add(pastaPackItem);
        
        itemsWithPromotion = new ArrayList<>();

        Item beanCanItem = new Item("bean can", new BigDecimal(1), 4, new BuyXForYPromotion(3, new BigDecimal(1)));

        itemsWithPromotion.add(beanCanItem);
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
        BigDecimal total  = pricer.computeTotalPrice(itemsWithPromotion);

        assertEquals(new BigDecimal(2), total);
    }
}
