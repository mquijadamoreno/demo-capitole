package com.demo.util;

import com.demo.domain.Price;
import com.demo.output.pojo.PriceEntity;

import java.time.LocalDateTime;

public class PriceDataFixture {

    private static final Integer brandId = 1;
    private static final LocalDateTime startDate = LocalDateTime.of(2020, 6, 14, 0, 0);
    private static final LocalDateTime endDate = LocalDateTime.of(2020, 12, 31, 23, 59);
    private static final Integer priceList = 1;
    private static final Integer productId = 35455;
    private static final Integer priority = 0;
    private static final Double price = 35.50;
    private static final String currency = "EUR";

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

    public static PriceEntity generatePriceEntity() {
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
