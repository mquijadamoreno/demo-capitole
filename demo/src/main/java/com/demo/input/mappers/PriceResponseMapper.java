package com.demo.input.mappers;

import com.demo.domain.Price;
import com.demo.input.dto.PriceResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceResponseMapper {

    @Mapping(target = "tariff", source = "priceList")
    PriceResponseDTO toPriceResponseDTO(Price price);
}
