package com.demo.input.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetPriceRQ {

    @JsonProperty("applicationDate")
    private String applicationDate;

    @JsonProperty("productId")
    private String productId;

    @JsonProperty("brandId")
    private String brandId;

}
