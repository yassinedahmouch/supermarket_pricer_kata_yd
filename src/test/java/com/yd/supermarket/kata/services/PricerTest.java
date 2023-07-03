package com.yd.supermarket.kata.services;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

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
@RunWith(MockitoJUnitRunner.class)
public class PricerTest extends TestCase {

    @InjectMocks
    PricerService pricerService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPricer() {
        List<Item> items           = new ArrayList<>();

        Item       waterBottleItem = new Item("water bottle", new Price(UnitType.NUMBER, new BigDecimal(1)),
                new BigDecimal(2), UnitType.NUMBER);
        Item       pastaPackItem   = new Item("pasta pack", new Price(UnitType.NUMBER, new BigDecimal(2)),
                new BigDecimal(1), UnitType.NUMBER);

        items.add(waterBottleItem);
        items.add(pastaPackItem);

        BigDecimal total = pricerService.defaultPricer(items);

        assertEquals(new BigDecimal(4), total);
    }

    @Test
    public void testPricerWithPromotionBuyXForY() {
        List<Item> itemsWithPromotionBuyXForY = new ArrayList<>();

        Item       beanCanItem                = new Item("bean can", new Price(UnitType.NUMBER, new BigDecimal(1)),
                new BigDecimal(4), UnitType.NUMBER);

        itemsWithPromotionBuyXForY.add(beanCanItem);

        pricerService
                .setBuyXForYPromotion(new BuyXForYPromotion(new BigDecimal(3), UnitType.NUMBER, new BigDecimal(1)));

        BigDecimal total = pricerService.buyXForYPromotionPricer(itemsWithPromotionBuyXForY);

        assertEquals(new BigDecimal(2), total);
    }

    @Test
    public void testPricerWithPromotionBuyXGetYFree() {
        List<Item> itemsWithPromotionBuyXGetYFree = new ArrayList<>();

        Item       milkBottleItem                 = new Item("milk bottle",
                new Price(UnitType.NUMBER, new BigDecimal(1)), new BigDecimal(3), UnitType.NUMBER);

        itemsWithPromotionBuyXGetYFree.add(milkBottleItem);

        pricerService.setBuyXGetYFreePromotion(
                new BuyXGetYFreePromotion(new BigDecimal(2), UnitType.NUMBER, new BigDecimal(1), UnitType.NUMBER));

        BigDecimal total = pricerService.buyXGetYFreePromotionPricer(itemsWithPromotionBuyXGetYFree);

        assertEquals(new BigDecimal(2), total);
    }

    @Test
    public void testPricerWithWeightedItemPoundToOunce() {
        List<Item> weightedItem = new ArrayList<>();

        Item       orangeItem   = new Item("orange", new Price(UnitType.POUND, new BigDecimal(1.99)), new BigDecimal(4),
                UnitType.OUNCE);

        weightedItem.add(orangeItem);

        BigDecimal total = pricerService.defaultPricer(weightedItem);

        assertEquals(new BigDecimal(0.4975, new MathContext(4)), total);
    }

    @Test
    public void testPricerWithWeightedItemGramToOunce() {
        List<Item> weightedItem = new ArrayList<>();

        Item       orangeItem   = new Item("orange", new Price(UnitType.GRAM, new BigDecimal(1.99)), new BigDecimal(4),
                UnitType.OUNCE);

        weightedItem.add(orangeItem);

        BigDecimal total = pricerService.defaultPricer(weightedItem);

        assertEquals(new BigDecimal(225.7, new MathContext(4)), total);
    }

    @Test
    public void testPricerWithWeightedItemGramToPound() {
        List<Item> weightedItem = new ArrayList<>();

        Item       orangeItem   = new Item("orange", new Price(UnitType.GRAM, new BigDecimal(0.25)), new BigDecimal(2),
                UnitType.POUND);

        weightedItem.add(orangeItem);

        BigDecimal total = pricerService.defaultPricer(weightedItem);

        assertEquals(new BigDecimal(226.8, new MathContext(4)), total);
    }

    @Test
    public void testPricerNotEligibleForPromotionBuyXGetYFree() {
        List<Item> itemsWithPromotionBuyXGetYFree = new ArrayList<>();

        Item       milkBottleItem                 = new Item("milk bottle",
                new Price(UnitType.NUMBER, new BigDecimal(1)), new BigDecimal(3), UnitType.NUMBER);

        itemsWithPromotionBuyXGetYFree.add(milkBottleItem);

        pricerService.setBuyXGetYFreePromotion(
                new BuyXGetYFreePromotion(new BigDecimal(4), UnitType.NUMBER, new BigDecimal(1), UnitType.NUMBER));

        BigDecimal total = pricerService.buyXGetYFreePromotionPricer(itemsWithPromotionBuyXGetYFree);

        assertEquals(new BigDecimal(3), total);
    }

    @Test
    public void testPricerWithPromotionBuyXPoundForY() {
        List<Item> itemsWithPromotionBuyXPoundForY = new ArrayList<>();

        Item       lensItem                        = new Item("tomato", new Price(UnitType.OUNCE, new BigDecimal(0.1)),
                new BigDecimal(3), UnitType.POUND);

        itemsWithPromotionBuyXPoundForY.add(lensItem);

        pricerService.setBuyXPoundForYPromotion(
                new BuyXPoundForYPromotion(new BigDecimal(2), UnitType.POUND, new BigDecimal(1)));

        BigDecimal total = pricerService.buyXPoundForYPromotionPricer(itemsWithPromotionBuyXPoundForY);

        assertEquals(new BigDecimal(2.60, new MathContext(4)), total);
    }

    @Test
    public void testPricerEnsureNotEqualToZero() {
        List<Item> itemsWithPromotionBuyXForY = new ArrayList<>();
        Item       beanCanItem                = new Item("bean can", new Price(UnitType.NUMBER, new BigDecimal(1)),
                new BigDecimal(4), UnitType.NUMBER);

        itemsWithPromotionBuyXForY.add(beanCanItem);

        pricerService.setBuyXForYPromotion(new BuyXForYPromotion(BigDecimal.ZERO, UnitType.NUMBER, new BigDecimal(1)));

        try {
            pricerService.buyXForYPromotionPricer(itemsWithPromotionBuyXForY);
        } catch (IllegalArgumentException e) {
            // Assert the error message
            assertEquals("The parameter quantityPerPromotion can not be equal to 0.", e.getMessage());
        }
    }

    @Test
    public void testPricerEnsureNotNull() {
        List<Item> itemsWithPromotionBuyXForY = new ArrayList<>();
        Item       beanCanItem                = new Item("bean can", new Price(UnitType.NUMBER, new BigDecimal(1)),
                new BigDecimal(4), UnitType.NUMBER);

        itemsWithPromotionBuyXForY.add(beanCanItem);

        pricerService.setBuyXForYPromotion(new BuyXForYPromotion(new BigDecimal(3), UnitType.NUMBER, null));

        try {
            pricerService.buyXForYPromotionPricer(itemsWithPromotionBuyXForY);
        } catch (IllegalArgumentException e) {
            // Assert the error message
            assertEquals("The parameter promotionPrice can not be null.", e.getMessage());
        }
    }

    @Test
    public void testPricerEnsureType() {
        List<Item> itemsWithPromotionBuyXForY = new ArrayList<>();
        Item       beanCanItem                = new Item("bean can", new Price(UnitType.NUMBER, new BigDecimal(1)),
                new BigDecimal(4), UnitType.NUMBER);

        itemsWithPromotionBuyXForY.add(beanCanItem);

        pricerService.setBuyXForYPromotion(new BuyXForYPromotion(new BigDecimal(3), UnitType.GRAM, new BigDecimal(1)));

        try {
            pricerService.buyXForYPromotionPricer(itemsWithPromotionBuyXForY);
        } catch (IllegalArgumentException e) {
            // Assert the error message
            assertEquals("The parameter Quantity per promotion type : " + UnitType.GRAM + " should be countable.",
                    e.getMessage());
        }
    }

    @Test
    public void testPricerSumNotEqualToZero() {
        List<Item> itemsWithPromotionBuyXGetYFree = new ArrayList<>();

        Item       milkBottleItem                 = new Item("milk bottle",
                new Price(UnitType.NUMBER, new BigDecimal(1)), new BigDecimal(3), UnitType.NUMBER);

        itemsWithPromotionBuyXGetYFree.add(milkBottleItem);

        pricerService.setBuyXGetYFreePromotion(
                new BuyXGetYFreePromotion(new BigDecimal(-1), UnitType.NUMBER, new BigDecimal(1), UnitType.NUMBER));

        try {
            pricerService.buyXGetYFreePromotionPricer(itemsWithPromotionBuyXGetYFree);
        } catch (IllegalArgumentException e) {
            // Assert the error message
            assertEquals("The sum of parameters : quantityToGetDiscount and quantityForFree can not be 0.",
                    e.getMessage());
        }
    }

    @Test
    public void testPricerEnsureCompatibility() {
        List<Item> itemsWithPromotionBuyXGetYFree = new ArrayList<>();

        Item       milkBottleItem                 = new Item("milk bottle",
                new Price(UnitType.NUMBER, new BigDecimal(1)), new BigDecimal(3), UnitType.GRAM);

        itemsWithPromotionBuyXGetYFree.add(milkBottleItem);

        pricerService.setBuyXGetYFreePromotion(
                new BuyXGetYFreePromotion(BigDecimal.ZERO, UnitType.NUMBER, BigDecimal.ZERO, UnitType.NUMBER));

        try {
            pricerService.buyXGetYFreePromotionPricer(itemsWithPromotionBuyXGetYFree);
        } catch (IllegalArgumentException e) {
            // Assert the error message
            assertEquals("The Item price type : " + UnitType.NUMBER + " and the Item quantity type : " + UnitType.GRAM
                    + " are incompatible.", e.getMessage());
        }
    }

    @Test
    public void testPricerEnsureNotNegative() {
        List<Item> itemsWithPromotionBuyXGetYFree = new ArrayList<>();

        Item       milkBottleItem                 = new Item("milk bottle",
                new Price(UnitType.NUMBER, new BigDecimal(-1)), new BigDecimal(3), UnitType.NUMBER);

        itemsWithPromotionBuyXGetYFree.add(milkBottleItem);

        pricerService.setBuyXGetYFreePromotion(
                new BuyXGetYFreePromotion(BigDecimal.ZERO, UnitType.NUMBER, BigDecimal.ZERO, UnitType.NUMBER));

        try {
            pricerService.buyXGetYFreePromotionPricer(itemsWithPromotionBuyXGetYFree);
        } catch (IllegalArgumentException e) {
            // Assert the error message
            assertEquals("The parameter " + -1 + " : Item price should be countable.", e.getMessage());
        }
    }
}
