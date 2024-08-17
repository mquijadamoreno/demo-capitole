package com.demo.output.adapters;

import com.demo.domain.Price;
import com.demo.output.mappers.PriceEntityMapper;
import com.demo.output.pojo.PriceEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
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
    public List<Price> findByFilters(Map<String, Object> filters) {
        Query query = new Query();

        //Filtro de fecha
        if (filters.containsKey("date")) {
            Instant date = (Instant) filters.get("date");
            query.addCriteria(Criteria.where("start_date").lte(date)
                    .and("end_date").gte(date));
        }

        //Resto de filtros
        filters.forEach((key, value) -> {
            if (!"date".equals(key)) {
                query.addCriteria(Criteria.where(key).is(value));
            }
        });

        //Ordenamos por prioridad
        query.with(Sort.by(Sort.Order.desc("priority")));

        //Nos quedamos con el más prioritario
        query.limit(1);

        return mongoTemplate.find(query, PriceEntity.class).stream()
                .map(priceEntityMapper::toDomain).collect(Collectors.toList());
    }
}
