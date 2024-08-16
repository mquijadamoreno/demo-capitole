package com.demo.input.mappers;

import com.demo.input.dto.GetPriceRQ;
import org.mapstruct.Mapper;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper(componentModel = "spring")
public interface PriceFilterMapper {

    default Map<String, String> toFilterMap(GetPriceRQ request) {
        return Stream.of(
                        new AbstractMap.SimpleEntry<>("applicationDate", request.getApplicationDate()),
                        new AbstractMap.SimpleEntry<>("productId", request.getProductId()),
                        new AbstractMap.SimpleEntry<>("brandId", request.getBrandId()))
                .filter(entry -> entry.getValue() != null)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
