package com.app.f1x.service.impl;

import com.app.f1x.dto.user.CreateUserRequest;
import com.app.f1x.dto.user.UpdateUserRequest;
import com.app.f1x.dto.user.UserResponse;
import com.app.f1x.model.User;
import com.app.f1x.repository.UserRepository;
import com.app.f1x.service.UserService;
import com.app.f1x.util.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
//public class UserServiceImpl implements UserService, UserDetailsService {
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toResponse).toList();
    }

    @Override
    public Optional<UserResponse> getUserById(Integer id) {
        return userRepository.findById(id).map(userMapper::toResponse);
    }

    @Override
    public Optional<UserResponse> createUser(CreateUserRequest createUserRequest) {
        User user = userMapper.toUser(createUserRequest);
        user.setCreatedAt(LocalDateTime.now());
        user.setUserRole(Collections.emptySet());
        user.setLocked(false);
        user.setEnabled(true);

        userRepository.save(user);
        return Optional.ofNullable(userMapper.toResponse(user));
    }

    @Override
    public Optional<UserResponse> updateUser(Integer id, UpdateUserRequest updateUserRequest) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) { return Optional.empty(); }

        User user = userOptional.get();
        Optional<String> username = Optional.ofNullable(updateUserRequest.getUsername());
        Optional<String> firstName = Optional.ofNullable(updateUserRequest.getFirstName());
        Optional<String> lastName = Optional.ofNullable(updateUserRequest.getLastName());
        Optional<String> password = Optional.ofNullable(updateUserRequest.getPassword());

        username.ifPresent(user::setUsername);
        firstName.ifPresent(user::setFirstName);
        lastName.ifPresent(user::setLastName);
        password.ifPresent(user::setPassword);

        userRepository.save(user);
        return userRepository.findById(id).map(userMapper::toResponse);
    }

    @Override
    public Optional<UserResponse> deleteUser(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            userRepository.delete(existingUser);
            return Optional.ofNullable(userMapper.toResponse(existingUser));
        }
        return Optional.empty();
    }

    // security methods
    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public Boolean existsByUsername(String username) {
        return null;
    }

    @Override
    public Boolean existsByEmail(String email) {
        return null;
    }

}
