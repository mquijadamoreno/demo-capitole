package com.demo.input.adapters;

import com.demo.domain.Price;
import com.demo.output.ports.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PriceUseCasesAdapter implements PriceUseCases {

    private final PriceRepository priceRepository;

    @Override
    public Price findPriceByFilters(Map<String, Object> filters) {
        return priceRepository.findByFilters(filters);
    }
}
