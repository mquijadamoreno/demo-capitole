package com.demo.input.dto;

import lombok.Data;

@Data
public class PriceResponseDTO {

    private Integer productId;
    private Integer brandId;
    private Integer tariff;
    private Double price;
    private String currency;

}
