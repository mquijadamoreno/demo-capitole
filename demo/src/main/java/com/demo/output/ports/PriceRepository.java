package com.demo.output.ports;

import com.demo.domain.Price;

import java.util.List;

public interface PriceRepository {
    public List<Price> getAll();
    public Price getOne(String id);
    public Price save(Price price);
}
