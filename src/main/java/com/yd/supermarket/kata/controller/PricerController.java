package com.yd.supermarket.kata.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yd.supermarket.kata.enumerations.UnitType;
import com.yd.supermarket.kata.models.Item;
import com.yd.supermarket.kata.models.Price;
import com.yd.supermarket.kata.services.BuyXForYPromotion;
import com.yd.supermarket.kata.services.BuyXGetYFreePromotion;
import com.yd.supermarket.kata.services.BuyXPoundForYPromotion;
import com.yd.supermarket.kata.services.PricerService;

/**
 * Rest controller of pricer.
 * 
 * @author Yassine
 *
 */
@RestController
public class PricerController {

    @Autowired
    PricerService pricerService;

    @Value("${promotion.buy.x.for.y.quantity.per.promotion}")
    int           quantityPerPromotion;
    @Value("${promotion.buy.x.for.y.measure.unit.per.promotion}")
    String        measureUnitPerPromotion;
    @Value("${promotion.buy.x.for.y.promotion.price}")
    int           promotionPrice;

    @Value("${promotion.buy.x.get.y.free.quantity.to.get.discount}")
    int           quantityToGetDiscount;
    @Value("${promotion.buy.x.get.y.free.measure.unit.to.get.discount}")
    String        measureUnitToGetDiscount;
    @Value("${promotion.buy.x.get.y.free.quantity.for.free}")
    int           quantityForFree;
    @Value("${promotion.buy.x.get.y.free.measure.unit.for.free}")
    String        measureUnitForFree;

    @Value("${promotion.buy.x.pound.for.y.weighted.quantity.per.promotion}")
    int           weightedQuantityPerPromotion;
    @Value("${promotion.buy.x.pound.for.y.weighted.measure.unit.per.promotion}")
    String        weightedMeasureUnitPerPromotion;
    @Value("${promotion.buy.x.pound.for.y.weighted.promotion.price}")
    int           weightedPromotionPrice;

    @PostMapping("/DefaultPricer")
    public ResponseEntity<BigDecimal> calculateWithDefaultPricer(@RequestBody List<Item> items) {
        List<Item> itemsWithoutPromotion = new ArrayList<>();

        for (Item itemRequest : items) {
            Item item = new Item(itemRequest.getItemName(),
                    new Price(itemRequest.getPrice().getPriceType(), itemRequest.getPrice().getPriceValue()),
                    itemRequest.getQuantity(), itemRequest.getMeasureUnit());

            itemsWithoutPromotion.add(item);
        }

        return ResponseEntity.status(HttpStatus.OK).body(pricerService.defaultPricer(itemsWithoutPromotion));
    }

    @PostMapping("/BuyXForY")
    public ResponseEntity<BigDecimal> calculateBuyXForY(@RequestBody List<Item> items) {
        List<Item> itemsWithPromotionBuyXForY = new ArrayList<>();

        for (Item itemRequest : items) {
            Item item = new Item(itemRequest.getItemName(),
                    new Price(itemRequest.getPrice().getPriceType(), itemRequest.getPrice().getPriceValue()),
                    itemRequest.getQuantity(), itemRequest.getMeasureUnit());

            itemsWithPromotionBuyXForY.add(item);
        }

        pricerService.setBuyXForYPromotion(new BuyXForYPromotion(new BigDecimal(quantityPerPromotion),
                UnitType.getUnitType(measureUnitPerPromotion), new BigDecimal(promotionPrice)));
        return ResponseEntity.status(HttpStatus.OK)
                .body(pricerService.buyXForYPromotionPricer(itemsWithPromotionBuyXForY));
    }

    @PostMapping("/BuyXGetYFree")
    public ResponseEntity<BigDecimal> calculateBuyXGetYFree(@RequestBody List<Item> items) {
        List<Item> itemsWithPromotionBuyXGetYFree = new ArrayList<>();

        for (Item itemRequest : items) {
            Item item = new Item(itemRequest.getItemName(),
                    new Price(itemRequest.getPrice().getPriceType(), itemRequest.getPrice().getPriceValue()),
                    itemRequest.getQuantity(), itemRequest.getMeasureUnit());

            itemsWithPromotionBuyXGetYFree.add(item);
        }

        pricerService.setBuyXGetYFreePromotion(new BuyXGetYFreePromotion(new BigDecimal(quantityToGetDiscount),
                UnitType.getUnitType(measureUnitToGetDiscount), new BigDecimal(quantityForFree),
                UnitType.getUnitType(measureUnitForFree)));
        return ResponseEntity.status(HttpStatus.OK)
                .body(pricerService.buyXGetYFreePromotionPricer(itemsWithPromotionBuyXGetYFree));
    }

    @PostMapping("/BuyXPoundForY")
    public ResponseEntity<BigDecimal> calculateBuyXPoundForY(@RequestBody List<Item> items) {
        List<Item> itemsWithPromotionBuyXPoundForY = new ArrayList<>();

        for (Item itemRequest : items) {
            Item item = new Item(itemRequest.getItemName(),
                    new Price(itemRequest.getPrice().getPriceType(), itemRequest.getPrice().getPriceValue()),
                    itemRequest.getQuantity(), itemRequest.getMeasureUnit());

            itemsWithPromotionBuyXPoundForY.add(item);
        }

        pricerService.setBuyXPoundForYPromotion(new BuyXPoundForYPromotion(new BigDecimal(weightedQuantityPerPromotion),
                UnitType.getUnitType(weightedMeasureUnitPerPromotion), new BigDecimal(weightedPromotionPrice)));
        return ResponseEntity.status(HttpStatus.OK)
                .body(pricerService.buyXPoundForYPromotionPricer(itemsWithPromotionBuyXPoundForY));
    }
}
