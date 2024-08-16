package com.demo.output.adapters;

import com.demo.domain.Price;
import com.demo.output.mappers.PriceEntityMapper;
import com.demo.output.ports.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MongoPriceRepositoryImpl implements PriceRepository {
    private final MongoPriceRepository mongoPriceRepository;
    private final PriceEntityMapper priceEntityMapper;


    @Override
    public List<Price> getAll() {
        return mongoPriceRepository.findAll().stream()
                .map(m -> priceEntityMapper.toDomain(m)).collect(Collectors.toList());
    }

    @Override
    public Price getOne(String id) {
       return priceEntityMapper.toDomain(mongoPriceRepository.findById(id).orElse(null));
    }

    @Override
    public Price save(Price price) {
        return null;
    }
}
