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

    Pricer pricer;

    /**
     * This method is used for initialization and it is called before a test is executed
     */
    @Override
    protected void setUp() throws Exception {
        pricer = new Pricer();
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

        BigDecimal total = pricer.computeTotalPrice(items);

        assertEquals(new BigDecimal(4), total);
    }

    @Test
    public void testPricerWithPromotionBuyXForY() {
        List<Item> itemsWithPromotionBuyXForY = new ArrayList<>();

        Item       beanCanItem                = new Item("bean can", new Price(UnitType.NUMBER, new BigDecimal(1)),
                new BigDecimal(4), UnitType.NUMBER,
                new BuyXForYPromotion(new BigDecimal(3), UnitType.NUMBER, new BigDecimal(1)));

        itemsWithPromotionBuyXForY.add(beanCanItem);

        BigDecimal total = pricer.computeTotalPrice(itemsWithPromotionBuyXForY);

        assertEquals(new BigDecimal(2), total);
    }

    @Test
    public void testPricerWithPromotionBuyXGetYFree() {
        List<Item> itemsWithPromotionBuyXGetYFree = new ArrayList<>();

        Item       milkBottleItem                 = new Item("milk bottle",
                new Price(UnitType.NUMBER, new BigDecimal(1)), new BigDecimal(3), UnitType.NUMBER,
                new BuyXGetYFreePromotion(new BigDecimal(2), UnitType.NUMBER, new BigDecimal(1), UnitType.NUMBER));

        itemsWithPromotionBuyXGetYFree.add(milkBottleItem);

        BigDecimal total = pricer.computeTotalPrice(itemsWithPromotionBuyXGetYFree);

        assertEquals(new BigDecimal(2), total);
    }

    @Test
    public void testPricerWithWeightedItemPoundToOunce() {
        List<Item> weightedItem = new ArrayList<>();

        Item       orangeItem   = new Item("orange", new Price(UnitType.POUND, new BigDecimal(1.99)), new BigDecimal(4),
                UnitType.OUNCE);

        weightedItem.add(orangeItem);

        BigDecimal total = pricer.computeTotalPrice(weightedItem);

        assertEquals(new BigDecimal(0.4975, new MathContext(4)), total);
    }

    @Test
    public void testPricerWithWeightedItemGramToOunce() {
        List<Item> weightedItem = new ArrayList<>();

        Item       orangeItem   = new Item("orange", new Price(UnitType.GRAM, new BigDecimal(1.99)), new BigDecimal(4),
                UnitType.OUNCE);

        weightedItem.add(orangeItem);

        BigDecimal total = pricer.computeTotalPrice(weightedItem);

        assertEquals(new BigDecimal(225.7, new MathContext(4)), total);
    }

    @Test
    public void testPricerWithWeightedItemGramToPound() {
        List<Item> weightedItem = new ArrayList<>();

        Item       orangeItem   = new Item("orange", new Price(UnitType.GRAM, new BigDecimal(0.25)), new BigDecimal(2),
                UnitType.POUND);

        weightedItem.add(orangeItem);

        BigDecimal total = pricer.computeTotalPrice(weightedItem);

        assertEquals(new BigDecimal(226.8, new MathContext(4)), total);
    }

    @Test
    public void testPricerNotEligibleForPromotionBuyXGetYFree() {
        List<Item> itemsWithPromotionBuyXGetYFree = new ArrayList<>();

        Item       milkBottleItem                 = new Item("milk bottle",
                new Price(UnitType.NUMBER, new BigDecimal(1)), new BigDecimal(3), UnitType.NUMBER,
                new BuyXGetYFreePromotion(new BigDecimal(4), UnitType.NUMBER, new BigDecimal(1), UnitType.NUMBER));

        itemsWithPromotionBuyXGetYFree.add(milkBottleItem);

        BigDecimal total = pricer.computeTotalPrice(itemsWithPromotionBuyXGetYFree);

        assertEquals(new BigDecimal(3), total);
    }

    @Test
    public void testPricerWithPromotionBuyXPoundForY() {
        List<Item> itemsWithPromotionBuyXPoundForY = new ArrayList<>();

        Item       lensItem                        = new Item("tomato", new Price(UnitType.OUNCE, new BigDecimal(0.1)),
                new BigDecimal(3), UnitType.POUND,
                new BuyXPoundForYPromotion(new BigDecimal(2), UnitType.POUND, new BigDecimal(1)));

        itemsWithPromotionBuyXPoundForY.add(lensItem);

        BigDecimal total = pricer.computeTotalPrice(itemsWithPromotionBuyXPoundForY);

        assertEquals(new BigDecimal(2.60, new MathContext(4)), total);
    }

    @Test
    public void testPricerEnsureNotEqualToZero() {
        List<Item> itemsWithPromotionBuyXForY = new ArrayList<>();
        Item       beanCanItem                = new Item("bean can", new Price(UnitType.NUMBER, new BigDecimal(1)),
                new BigDecimal(4), UnitType.NUMBER,
                new BuyXForYPromotion(BigDecimal.ZERO, UnitType.NUMBER, new BigDecimal(1)));

        itemsWithPromotionBuyXForY.add(beanCanItem);

        try {
            pricer.computeTotalPrice(itemsWithPromotionBuyXForY);
        } catch (IllegalArgumentException e) {
            // Assert the error message
            assertEquals("The parameter quantityPerPromotion can not be equal to 0.", e.getMessage());
        }
    }

    @Test
    public void testPricerEnsureNotNull() {
        List<Item> itemsWithPromotionBuyXForY = new ArrayList<>();
        Item       beanCanItem                = new Item("bean can", new Price(UnitType.NUMBER, new BigDecimal(1)),
                new BigDecimal(4), UnitType.NUMBER, new BuyXForYPromotion(new BigDecimal(3), UnitType.NUMBER, null));

        itemsWithPromotionBuyXForY.add(beanCanItem);

        try {
            pricer.computeTotalPrice(itemsWithPromotionBuyXForY);
        } catch (IllegalArgumentException e) {
            // Assert the error message
            assertEquals("The parameter promotionPrice can not be null.", e.getMessage());
        }
    }

    @Test
    public void testPricerEnsureType() {
        List<Item> itemsWithPromotionBuyXForY = new ArrayList<>();
        Item       beanCanItem                = new Item("bean can", new Price(UnitType.NUMBER, new BigDecimal(1)),
                new BigDecimal(4), UnitType.NUMBER,
                new BuyXForYPromotion(new BigDecimal(3), UnitType.GRAM, new BigDecimal(1)));

        itemsWithPromotionBuyXForY.add(beanCanItem);

        try {
            pricer.computeTotalPrice(itemsWithPromotionBuyXForY);
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
                new Price(UnitType.NUMBER, new BigDecimal(1)), new BigDecimal(3), UnitType.NUMBER,
                new BuyXGetYFreePromotion(new BigDecimal(-1), UnitType.NUMBER, new BigDecimal(1), UnitType.NUMBER));

        itemsWithPromotionBuyXGetYFree.add(milkBottleItem);

        try {
            pricer.computeTotalPrice(itemsWithPromotionBuyXGetYFree);
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
                new Price(UnitType.NUMBER, new BigDecimal(1)), new BigDecimal(3), UnitType.GRAM,
                new BuyXGetYFreePromotion(BigDecimal.ZERO, UnitType.NUMBER, BigDecimal.ZERO, UnitType.NUMBER));

        itemsWithPromotionBuyXGetYFree.add(milkBottleItem);

        try {
            pricer.computeTotalPrice(itemsWithPromotionBuyXGetYFree);
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
                new Price(UnitType.NUMBER, new BigDecimal(-1)), new BigDecimal(3), UnitType.NUMBER,
                new BuyXGetYFreePromotion(BigDecimal.ZERO, UnitType.NUMBER, BigDecimal.ZERO, UnitType.NUMBER));

        itemsWithPromotionBuyXGetYFree.add(milkBottleItem);

        try {
            pricer.computeTotalPrice(itemsWithPromotionBuyXGetYFree);
        } catch (IllegalArgumentException e) {
            // Assert the error message
            assertEquals("The parameter " + -1 + " : Item price should be countable.", e.getMessage());
        }
    }
}
