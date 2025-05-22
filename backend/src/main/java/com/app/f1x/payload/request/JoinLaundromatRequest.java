package com.app.f1x.payload.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JoinLaundromatRequest {

    @NotEmpty
    private String inviteCode;

}
