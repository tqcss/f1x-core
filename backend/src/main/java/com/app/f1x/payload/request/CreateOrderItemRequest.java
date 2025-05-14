package com.app.f1x.payload.request;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class CreateOrderItemRequest {

    @Length(max = 64)
    private String serviceName;

    @PositiveOrZero
    private Integer quantity;

}
