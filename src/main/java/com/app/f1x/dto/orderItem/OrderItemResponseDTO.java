package com.app.f1x.dto.orderItem;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class OrderItemResponseDTO {

    private Long id;
    private String serviceName;
    private Float serviceCost;
    private Integer quantity;
    private Float subTotal;
    private List<Long> requiredProductIds;
    private List<Long> optionalProductIds;

}
