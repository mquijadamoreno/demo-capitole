package com.demo.util;

import com.demo.domain.Price;
import com.demo.output.pojo.PriceEntity;

import java.time.LocalDateTime;

public class PriceDataFixture {

    private static Integer brandId = 1;
    private static LocalDateTime startDate = LocalDateTime.of(2020, 6, 14, 0, 0);
    private static LocalDateTime endDate = LocalDateTime.of(2020, 12, 31, 23, 59);
    private static Integer priceList = 1;
    private static Integer productId = 35455;
    private static Integer priority = 0;
    private static Double price = 35.50;
    private static String currency = "EUR";

    public static Price generatePrice() {
        return Price.builder()
                .brandId(brandId)
                .startDate(startDate)
                .endDate(endDate)
                .priceList(priceList)
                .productId(productId)
                .priority(priority)
                .price(price)
                .currency(currency)
                .build();
    }

    public static PriceEntity generatePriceDocument() {
        return PriceEntity.builder()
                .brandId(brandId)
                .startDate(startDate)
                .endDate(endDate)
                .priceList(priceList)
                .productId(productId)
                .priority(priority)
                .price(price)
                .currency(currency)
                .build();
    }
}
