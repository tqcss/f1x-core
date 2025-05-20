package com.app.f1x.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsResponse {

    private String fullName;
    private Boolean inLaundromat;
    private String laundromatName;

}
