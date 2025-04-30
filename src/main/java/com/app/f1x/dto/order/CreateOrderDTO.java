package com.app.f1x.dto.order;

import com.app.f1x.dto.orderItem.CreateOrderItemDTO;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateOrderDTO {

    @Future
    @NotNull
    private LocalDateTime completionTime;

    @Length(min = 3, max = 127)
    @NotNull
    private String customerName;

    @Length(min = 0, max = 63)
    @NotNull
    private String customerContact;

    @Length(min = 0, max = 255)
    @NotNull
    private String note;

    @NotNull
    List<CreateOrderItemDTO> orderItems;

}
