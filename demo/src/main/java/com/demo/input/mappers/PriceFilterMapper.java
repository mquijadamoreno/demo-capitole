package com.demo.input.mappers;

import com.demo.input.dto.GetPriceRQ;
import org.mapstruct.Mapper;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface PriceFilterMapper {

    String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSX";

    default Map<String, Object> toFilterMap(GetPriceRQ request) {

        Map<String, Object> filters = new HashMap<>();

        if (!ObjectUtils.isEmpty(request.getApplicationDate())) {
            filters.put("date",
                    LocalDateTime
                            .parse(request.getApplicationDate(),DateTimeFormatter.ofPattern(DATE_PATTERN))
                            .toInstant(ZoneOffset.UTC));
        }

        if (!ObjectUtils.isEmpty(request.getProductId())) {
            filters.put("product_id", Integer.valueOf(request.getProductId()));
        }

        if (!ObjectUtils.isEmpty(request.getBrandId())) {
            filters.put("brand_id", Integer.valueOf(request.getBrandId()));
        }

        return filters;
    }
}
