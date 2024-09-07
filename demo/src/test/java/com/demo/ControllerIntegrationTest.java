package com.demo;

import com.demo.input.dto.PriceResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    void testFindPriceByFilters() {
        ResponseEntity<PriceResponseDTO> response =
                restTemplate.getForEntity(
                        "/prices?applicationDate=2020-06-14T10:00:00&productId=35455&brandId=1",
                        PriceResponseDTO.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getPrice()).isEqualTo(35.5);
        assertThat(response.getBody().getBrandId()).isEqualTo(1);
        assertThat(response.getBody().getTariff()).isEqualTo(1);
        assertThat(response.getBody().getCurrency()).isEqualTo("EUR");
    }

    @Test
    void testFindPriceByFiltersInvalidDateFormat() {
        ResponseEntity<String> response =
                restTemplate.getForEntity(
                        "/prices?applicationDate=invalid-date&productId=35455&brandId=1",
                        String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).contains("The date must be in the format yyyy-MM-dd'T'HH:mm:ss");
    }

    @Test
    void testFindPriceByFiltersMissingProductId() {
        ResponseEntity<String> response =
                restTemplate.getForEntity(
                        "/prices?applicationDate=2020-06-14T10:00:00&brandId=1",
                        String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).contains("Required parameter 'productId' is not present.");
    }

    @Test
    void testFindPriceByFiltersMissingBrandId() {
        ResponseEntity<String> response =
                restTemplate.getForEntity(
                        "/prices?applicationDate=2020-06-14T10:00:00&productId=35455",
                        String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).contains("Required parameter 'brandId' is not present.");
    }


}
