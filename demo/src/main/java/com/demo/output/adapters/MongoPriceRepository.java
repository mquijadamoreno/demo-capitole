package com.demo.output.adapters;

import com.demo.domain.Price;

import java.util.List;

public interface MongoPriceRepository {

    List<Price> getAll();
    Price getOne(String id);
    Price save(Price price);

}
