package com.app.f1x.dto.product;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductResponse {

    private Integer id;
    private String name;
    private Float inventoryCost;
    private Float usageCost;

}
