package com.ashwinmulky.security.controller;

import com.ashwinmulky.security.controller.dtos.UserDTO;
import com.ashwinmulky.security.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody UserDTO userDto) {
       if(userDto != null) {
           return authenticationService.authenticate(userDto);
       }
       return "Insufficient input";
    }

    @GetMapping("/")
    public ResponseEntity<?> testHome() {
        return new ResponseEntity<>("Welcome to Home", HttpStatus.OK);
    }

}
