package com.demo.output.adapters;

import com.demo.output.pojo.PriceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoPriceRepository extends MongoRepository<PriceEntity, String> {

}
