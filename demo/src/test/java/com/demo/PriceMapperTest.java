package com.demo;

import com.demo.domain.Price;
import com.demo.output.mappers.PriceEntityMapper;
import com.demo.output.pojo.PriceEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static com.demo.util.PriceDataFixture.generatePrice;
import static com.demo.util.PriceDataFixture.generatePriceDocument;
import static org.assertj.core.api.Assertions.assertThat;

public class PriceMapperTest {

    private PriceEntityMapper priceEntityMapper = Mappers.getMapper(PriceEntityMapper.class);

    private static Price priceDomain;
    private static PriceEntity priceDocument;

    @BeforeAll
    private static void setUp(){
        priceDomain = generatePrice();
        priceDocument = generatePriceDocument();
    }

    @Test
    public void testToDocument(){
        PriceEntity document = priceEntityMapper.toDocument(priceDomain);
        assertThat(document).usingRecursiveComparison().isEqualTo(priceDocument);
    }

    @Test
    public void testToDomain(){
        Price domain = priceEntityMapper.toDomain(priceDocument);
        assertThat(domain).usingRecursiveComparison().isEqualTo(priceDomain);
    }


}
