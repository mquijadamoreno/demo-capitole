package com.demo;

import com.demo.domain.Price;
import com.demo.output.mappers.PriceEntityMapper;
import com.demo.output.pojo.PriceEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static com.demo.util.PriceDataFixture.generatePrice;
import static com.demo.util.PriceDataFixture.generatePriceEntity;
import static org.assertj.core.api.Assertions.assertThat;

public class PriceMapperTest {

    private PriceEntityMapper priceEntityMapper = Mappers.getMapper(PriceEntityMapper.class);

    private static Price priceDomain;
    private static PriceEntity priceEntity;

    @BeforeAll
    public static void setUp(){
        priceDomain = generatePrice();
        priceEntity = generatePriceEntity();
    }

    @Test
    public void testToDocument(){
        PriceEntity document = priceEntityMapper.toEntity(priceDomain);
        assertThat(document).usingRecursiveComparison().isEqualTo(priceEntity);
    }

    @Test
    public void testToDomain(){
        Price domain = priceEntityMapper.toDomain(priceEntity);
        assertThat(domain).usingRecursiveComparison().isEqualTo(priceDomain);
    }


}
