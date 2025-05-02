package com.app.f1x.service;

import com.app.f1x.dto.user.CreateUserRequest;
import com.app.f1x.dto.user.UserResponse;

public interface UserService {

    UserResponse createUser(CreateUserRequest createUserRequest);

}
