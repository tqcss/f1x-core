package com.app.f1x.service.impl;

import com.app.f1x.dto.user.CreateUserRequest;
import com.app.f1x.dto.user.UserResponse;
import com.app.f1x.model.User;
import com.app.f1x.repository.UserRepository;
import com.app.f1x.service.UserService;
import com.app.f1x.util.enums.UserRole;
import com.app.f1x.util.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    @Override
    public UserResponse createUser(CreateUserRequest createUserRequest) {
        User user = userMapper.toUser(createUserRequest);
        user.setUserRole(UserRole.USER);
        user.setLocked(false);
        user.setEnabled(true);

        userRepository.save(user);
        return userMapper.toResponse(user);
    }
}
