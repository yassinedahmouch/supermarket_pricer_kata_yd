package com.yd.kata.sp.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

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

    @Override
    protected void setUp() throws Exception {
        items = new ArrayList<>();

        Item waterBottleItem = new Item("water Bottle", new BigDecimal(1), 2);
        Item pastaPackItem   = new Item("pasta Pack", new BigDecimal(2), 1);

        items.add(waterBottleItem);
        items.add(pastaPackItem);
    }

    @Test
    public void testPricer() {

        Pricer     pricer = new Pricer();
        BigDecimal total  = pricer.computeTotalPrice(items);

        assertEquals(new BigDecimal(4), total);
    }
}
