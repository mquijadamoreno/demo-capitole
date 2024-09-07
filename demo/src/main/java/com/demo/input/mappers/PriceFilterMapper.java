package com.demo.input.mappers;

import com.demo.domain.Price;
import com.demo.input.dto.GetPriceRQ;
import org.mapstruct.Mapper;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface PriceFilterMapper {

    String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

    default Map<String, Object> toFilterMap(GetPriceRQ request) {

        Map<String, Object> filters = new HashMap<>();

        if (!ObjectUtils.isEmpty(request.getApplicationDate())) {
            filters.put(Price.getDateFilter(),
                    LocalDateTime
                            .parse(request.getApplicationDate(),DateTimeFormatter.ofPattern(DATE_PATTERN)));
        }

        if (!ObjectUtils.isEmpty(request.getProductId())) {
            filters.put(Price.getProductIdFilter(), Integer.valueOf(request.getProductId()));
        }

        if (!ObjectUtils.isEmpty(request.getBrandId())) {
            filters.put(Price.getBrandIdFilter(), Integer.valueOf(request.getBrandId()));
        }

        return filters;
    }
}
