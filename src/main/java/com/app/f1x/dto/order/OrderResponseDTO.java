package com.app.f1x.dto.order;

import com.app.f1x.dto.orderItem.OrderItemResponseDTO;
import com.app.f1x.util.enums.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class OrderResponseDTO {

    private Long id;
    private LocalDateTime creationTime;
    private LocalDateTime completionTime;
    private String customerName;
    private String customerContact;
    private OrderStatus orderStatus;
    private String note;
    private Float grandTotal;
    private List<OrderItemResponseDTO> orderItems;

}
