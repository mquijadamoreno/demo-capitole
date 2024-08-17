package com.demo;

import com.demo.domain.Price;
import com.demo.input.adapters.PriceUseCases;
import com.demo.input.dto.GetPriceRQ;
import com.demo.input.dto.PriceResponseDTO;
import com.demo.input.mappers.PriceFilterMapper;
import com.demo.input.mappers.PriceResponseMapper;
import com.demo.input.ports.PricesController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PricesControllerTest {

    @InjectMocks
    private PricesController pricesController;

    @Mock
    private PriceUseCases priceService;

    @Mock
    private PriceFilterMapper filterMapper;

    @Mock
    private PriceResponseMapper priceResponseMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPrices_Success() {
        String applicationDate = "2020-06-15T16:00:00.000Z";
        String productId = "35455";
        String brandId = "1";
        Price price = new Price();
        List<Price> prices = Collections.singletonList(price);

        when(filterMapper.toFilterMap(any(GetPriceRQ.class))).thenReturn(Collections.emptyMap());
        when(priceService.findPriceByFilters(any())).thenReturn(prices);
        when(priceResponseMapper.toPriceResponseDTO(any(Price.class))).thenReturn(new PriceResponseDTO());

        ResponseEntity<Object> responseEntity = pricesController.getPrices(applicationDate, productId, brandId);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isInstanceOf(List.class);
        List<PriceResponseDTO> responseList = (List<PriceResponseDTO>) responseEntity.getBody();
        assertThat(responseList).hasSize(1);
        verify(priceService, times(1)).findPriceByFilters(any());
    }

    @Test
    void testGetPrices_NoResults() {
        String applicationDate = "2020-06-15T16:00:00.000Z";
        String productId = "35455";
        String brandId = "1";

        when(filterMapper.toFilterMap(any(GetPriceRQ.class))).thenReturn(Collections.emptyMap());
        when(priceService.findPriceByFilters(any())).thenReturn(Collections.emptyList());

        ResponseEntity<Object> responseEntity = pricesController.getPrices(applicationDate, productId, brandId);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("No prices found");
        verify(priceService, times(1)).findPriceByFilters(any());
    }
}
