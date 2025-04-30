package com.app.f1x.dto.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class CreateProductRequest {

    @Length(min = 3, max = 63)
    @NotNull
    private String name;

    @PositiveOrZero
    @NotNull
    private Float inventoryCost;

    @PositiveOrZero
    @NotNull
    private Float usageCost;

}
