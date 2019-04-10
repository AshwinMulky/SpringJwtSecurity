package com.ashwinmulky.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserResource {


    @GetMapping("/user/{name}")
    public ResponseEntity<?> getUserResource(@PathVariable String name) {
        return new ResponseEntity<>("Hello User " + name, HttpStatus.OK);
    }

    @GetMapping("/admin/{name}")
    public ResponseEntity<?> getAdminResource(@PathVariable String name) {
        return new ResponseEntity<>("Hello Admin " + name, HttpStatus.OK);
    }
}
