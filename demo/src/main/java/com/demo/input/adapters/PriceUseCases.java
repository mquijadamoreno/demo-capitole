package com.demo.input.adapters;

import com.demo.domain.Price;

import java.util.Map;

public interface PriceUseCases {

    Price findPriceByFilters(Map<String, Object> filters);

}
