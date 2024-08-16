package com.demo.input.adapters;

import com.demo.domain.Price;

import java.util.List;
import java.util.Map;

public interface PriceUseCases {

    List<Price> findPriceByFilters(Map<String, Object> filters);

}
