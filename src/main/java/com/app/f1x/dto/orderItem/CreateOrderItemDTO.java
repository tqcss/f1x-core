package com.app.f1x.dto.orderItem;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class CreateOrderItemDTO extends UpdateOrderItemDTO {

    @Getter
    @Setter
    @NotNull
    private Long serviceTypeId;

}
