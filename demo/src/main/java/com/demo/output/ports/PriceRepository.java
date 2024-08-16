package com.demo.output.ports;

import com.demo.output.adapters.MongoPriceRepository;
import com.demo.output.pojo.PriceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PriceRepository extends MongoRepository<PriceEntity, String>, MongoPriceRepository {

}
