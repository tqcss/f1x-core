package com.app.f1x.controller;

import com.app.f1x.dto.user.CreateUserRequest;
import com.app.f1x.dto.user.UpdateUserRequest;
import com.app.f1x.dto.user.UserResponse;
import com.app.f1x.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping("/{id}")
    public Optional<UserResponse> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public Optional<UserResponse> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        return userService.createUser(createUserRequest);
    }

    @PutMapping("/{id}")
    public Optional<UserResponse> updateUser(@PathVariable Integer id, @Valid @RequestBody UpdateUserRequest updateUserRequest) {
        return userService.updateUser(id, updateUserRequest);
    }

    @DeleteMapping("/{id}")
    public Optional<UserResponse> deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

}
