package com.app.f1x.dto.user;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserResponse {

    private Integer id;
    private LocalDateTime createdAt;
    private String userRole;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private Boolean locked;
    private Boolean enabled;

}
