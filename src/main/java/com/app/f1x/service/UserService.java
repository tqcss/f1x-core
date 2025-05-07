package com.app.f1x.service;

import com.app.f1x.dto.user.CreateUserRequest;
import com.app.f1x.dto.user.UpdateUserRequest;
import com.app.f1x.dto.user.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserResponse> getAllUsers();
    Optional<UserResponse> getUserById(Integer id);
    Optional<UserResponse> createUser(CreateUserRequest createUserRequest);
    Optional<UserResponse> updateUser(Integer id, UpdateUserRequest updateUserRequest);
    Optional<UserResponse> deleteUser(Integer id);

}
