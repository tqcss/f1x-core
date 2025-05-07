package com.app.f1x.dto.laundromat;

import com.app.f1x.dto.laundromatRole.LaundromatRoleResponse;
import com.app.f1x.dto.serviceType.ServiceTypeResponse;
import com.app.f1x.dto.user.UserResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class LaundromatResponse {

    private Integer id;
    private LocalDateTime createdAt;
    private Integer creator;
    private List<UserResponse> users;
    private List<LaundromatRoleResponse> roles;
    private List<ServiceTypeResponse> services;


}
