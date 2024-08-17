package com.demo.input.ports;

import com.demo.domain.Price;
import com.demo.input.adapters.PriceUseCases;
import com.demo.input.dto.GetPriceRQ;
import com.demo.input.mappers.PriceFilterMapper;
import com.demo.input.mappers.PriceResponseMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
@Validated
public class PricesController {

    private final PriceUseCases priceService;
    private final PriceFilterMapper filterMapper;

    private final PriceResponseMapper priceResponseMapper;


    @GetMapping
    public ResponseEntity<Object> getPrices(
            @RequestParam
            @NotBlank(message = "Application date is required.")
            @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z$",
                    message = "Application date must be in the format YYYY-MM-DDTHH:MM[:SS].")
            String applicationDate,

            @RequestParam
            @NotBlank(message = "Product ID is required.")
            @Pattern(regexp = "^\\d+$", message = "Product ID must be a numeric value.")
            String productId,

            @RequestParam
            @NotBlank(message = "Brand ID is required.")
            @Pattern(regexp = "^\\d+$", message = "Brand ID must be a numeric value.")
            String brandId) {

        List<Price> prices = priceService.findPriceByFilters(filterMapper.toFilterMap(
                GetPriceRQ.builder().applicationDate(applicationDate).brandId(brandId).productId(productId).build()));

        return prices.isEmpty() ? ResponseEntity.ok("No prices found") :
                ResponseEntity.ok(
                        prices.stream().map(priceResponseMapper::toPriceResponseDTO).collect(Collectors.toList()));
    }

}
