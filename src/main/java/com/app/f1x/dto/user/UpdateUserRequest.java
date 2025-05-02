package com.app.f1x.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class UpdateUserRequest {

    @Length(min = 3, max = 63)
    @NotNull
    private String username;

    @Length(min = 3, max = 63)
    @NotNull
    private String firstName;

    @Length(min = 3, max = 63)
    @NotNull
    private String lastName;

}
