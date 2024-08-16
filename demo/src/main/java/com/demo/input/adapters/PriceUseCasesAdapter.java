package com.demo.input.adapters;

import com.demo.domain.Price;
import com.demo.output.adapters.MongoPriceRepository;
import com.demo.output.ports.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PriceUseCasesAdapter implements PriceUseCases {
    private final MongoPriceRepository priceRepository;

    @Override
    public List<Price> findPriceByFilters(Map<String, String> filters) {
        return priceRepository.findByFilters(filters);
    }
}
