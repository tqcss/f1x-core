package com.app.f1x.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class RegisterRequest {

    @Length(min = 3, max = 63)
    private String email;

    @Length(min = 3, max = 63)
    private String username;

    @Length(min = 3, max = 63)
    private String firstName;

    @Length(min = 3, max = 63)
    private String lastName;

    @Length(min = 3, max = 63)
    private String password;

}
