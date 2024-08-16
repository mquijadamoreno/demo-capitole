package com.demo.input.ports;

import com.demo.domain.Price;
import com.demo.input.adapters.PriceUseCases;
import com.demo.input.dto.GetPriceRQ;
import com.demo.input.mappers.PriceFilterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
public class PricesController {

    private final PriceUseCases priceService;
    private final PriceFilterMapper filterMapper;


    @GetMapping
    public ResponseEntity<List<Price>> getPrice(@RequestBody GetPriceRQ request){
        List<Price> response = priceService.findPriceByFilters(filterMapper.toFilterMap(request));
        return ResponseEntity.ok(response);
    }

}
