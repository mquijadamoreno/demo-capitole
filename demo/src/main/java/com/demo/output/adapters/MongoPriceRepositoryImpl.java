package com.demo.output.adapters;

import com.demo.domain.Price;
import com.demo.output.mappers.PriceEntityMapper;
import com.demo.output.pojo.PriceEntity;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MongoPriceRepositoryImpl implements MongoPriceRepository {

    private final PriceEntityMapper priceEntityMapper;
    @Resource
    MongoTemplate mongoTemplate;


    @Override
    public List<Price> getAll() {
        return mongoTemplate.findAll(PriceEntity.class).stream()
                .map(priceEntityMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Price getOne(String id) {
      // return priceEntityMapper.toDomain(mongoPriceRepository.findById(id).orElse(null));

        return null;
    }

    @Override
    public Price save(Price price) {
        return null;
    }
}
