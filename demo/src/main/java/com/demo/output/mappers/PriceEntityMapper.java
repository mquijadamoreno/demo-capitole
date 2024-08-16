package com.demo.output.mappers;

import com.demo.domain.Price;
import com.demo.output.pojo.PriceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

    PriceEntity toDocument(Price domain);

    Price toDomain(PriceEntity document);
}
