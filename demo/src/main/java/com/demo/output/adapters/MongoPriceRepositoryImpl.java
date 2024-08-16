package com.demo.output.adapters;

import com.demo.domain.Price;
import com.demo.output.mappers.PriceEntityMapper;
import com.demo.output.pojo.PriceEntity;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class MongoPriceRepositoryImpl implements MongoPriceRepository {

    private final PriceEntityMapper priceEntityMapper;
    MongoTemplate mongoTemplate;

    public MongoPriceRepositoryImpl(PriceEntityMapper priceEntityMapper, MongoTemplate mongoTemplate) {
        this.priceEntityMapper = priceEntityMapper;
        this.mongoTemplate = mongoTemplate;
    }


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

    @Override
    public List<Price> findByFilters(Map<String, String> filters) {
        Query query = new Query();

        filters.forEach((key, value) -> {
            Criteria criteria = Criteria.where(key).is(value);
            query.addCriteria(criteria);
        });

        return mongoTemplate.find(query, PriceEntity.class).stream()
                .map(priceEntityMapper::toDomain).collect(Collectors.toList());
    }
}
