package com.app.f1x.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateOrderRequest {

    @Length(max = 64)
    private String customerName;

    @Length(max = 64)
    private String customerContact;

    @NotNull
    private List<CreateOrderItemRequest> orderItems;

}
