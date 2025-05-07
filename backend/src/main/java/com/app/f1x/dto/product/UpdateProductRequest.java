package com.app.f1x.dto.product;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class UpdateProductRequest {

    @PositiveOrZero
    private Integer laundromat_id;

    @Length(min = 3, max = 63)
    private String name;

    @PositiveOrZero
    private Float inventoryCost;

    @PositiveOrZero
    private Float usageCost;

}
