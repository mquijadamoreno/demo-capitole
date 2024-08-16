package com.demo.output.adapters;

import com.demo.domain.Price;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public interface MongoPriceRepository {

    List<Price> getAll();
    Price getOne(String id);
    Price save(Price price);
    List<Price> findByFilters(Map<String,String> filters);

}
