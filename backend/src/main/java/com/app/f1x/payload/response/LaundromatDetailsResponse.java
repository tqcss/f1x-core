package com.app.f1x.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LaundromatDetailsResponse {

    private boolean inLaundromat;
    private boolean isLaundromatCreator;
    private String laundromatName;
    private String laundromatInviteCode;

}
