package com.demo.input.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetPriceRQ {
    private String applicationDate;
    private String productId;
    private String brandId;
}
