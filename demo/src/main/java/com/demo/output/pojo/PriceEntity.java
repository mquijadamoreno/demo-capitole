package com.demo.output.pojo;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document("prices")
@Builder
@Data
public class PriceEntity {
    @Id
    private String id;

    @Field("brand_id")
    private Integer brandId;

    @Field("start_date")
    private LocalDateTime startDate;

    @Field("end_date")
    private LocalDateTime endDate;

    @Field("price_list")
    private Integer priceList;

    @Field("product_id")
    private Integer productId;

    @Field("priority")
    private Integer priority;

    @Field("price")
    private Double price;

    @Field("curr")
    private String currency;
}
