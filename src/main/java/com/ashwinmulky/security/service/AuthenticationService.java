package com.ashwinmulky.security.service;

import com.ashwinmulky.security.controller.dtos.UserDTO;
import com.ashwinmulky.security.model.User;
import com.ashwinmulky.security.repository.UserRepository;
import com.ashwinmulky.security.security.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    public String authenticate(UserDTO userDto) {
        Optional<User> mayBeUser = userRepository.findOneByName(userDto.getName());
        User authUser = mayBeUser.map(user -> {
            if (passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
                return user;
            }
            return null;
        }).orElse(null);

        if(authUser == null) {
            return "User not found : Unauthorized !!";
        }

        return jwtUtils.generateToken(authUser);
    }
}
