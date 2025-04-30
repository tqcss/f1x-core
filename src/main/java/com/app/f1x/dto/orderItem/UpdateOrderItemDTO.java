package com.app.f1x.dto.orderItem;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UpdateOrderItemDTO {

    @NotNull
    private List<Integer> optionalProductIds;

    @PositiveOrZero
    @NotNull
    private Integer quantity;

}
