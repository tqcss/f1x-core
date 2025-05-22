package com.app.f1x.payload.response;

import com.app.f1x.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LaundromatDetailsResponse {

    private boolean inLaundromat;
    private boolean isLaundromatCreator;
    private String laundromatName;
    private String laundromatInviteCode;
    private String laundromatCreatorEmail;
    private List<UserDetailsResponse> employeeDetails;

}
