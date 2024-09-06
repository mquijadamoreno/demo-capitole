package com.demo.output.ports;


import com.demo.domain.Price;

import java.util.Map;

public interface PriceRepository {
    Price findByFilters(Map<String, Object> filters);
}
