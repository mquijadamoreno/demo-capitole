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
            @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$",
                    message = "The date must be in the format yyyy-MM-dd'T'HH:mm:ss, e.g., 2021-01-15T16:00:00")
            String applicationDate,

            @RequestParam
            @NotBlank(message = "Product ID is required.")
            @Pattern(regexp = "^\\d+$", message = "Product ID must be a numeric value.")
            String productId,

            @RequestParam
            @NotBlank(message = "Brand ID is required.")
            @Pattern(regexp = "^\\d+$", message = "Brand ID must be a numeric value.")
            String brandId) {

        Price price = priceService.findPriceByFilters(filterMapper.toFilterMap(
                GetPriceRQ.builder().applicationDate(applicationDate).brandId(brandId).productId(productId).build()));

        return price == null ? ResponseEntity.ok("No prices found") :
                ResponseEntity.ok(priceResponseMapper.toPriceResponseDTO(price));
    }

}
