package com.app.f1x.dto.product;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ProductResponse {

    private Integer id;
    private LocalDateTime createdAt;
    private String name;
    private Float inventoryCost;
    private Float usageCost;

}
