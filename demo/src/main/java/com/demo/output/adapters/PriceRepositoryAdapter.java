package com.demo.output.adapters;

import com.demo.domain.Price;
import com.demo.output.mappers.PriceEntityMapper;
import com.demo.output.ports.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepository {

    private PriceJpaRepository priceJpaRepository;
    private PriceEntityMapper priceEntityMapper;

    @Override
    public Price findByFilters(Map<String, Object> filters) {

        Integer productId = (Integer) filters.get(Price.getProductIdFilter());
        Integer brandId = (Integer) filters.get(Price.getBrandIdFilter());
        LocalDateTime date = (LocalDateTime) filters.get(Price.getDateFilter());

        return priceEntityMapper.toDomain(priceJpaRepository.findTopPrice(productId, brandId, date).orElse(null));

    }

}
