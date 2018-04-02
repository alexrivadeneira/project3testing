package com.example.usersapi.controllers;

import com.example.usersapi.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsersController {

    @GetMapping("/users")
    public List<User> findAllUsers() {
        return new ArrayList<User>();
    }

}
