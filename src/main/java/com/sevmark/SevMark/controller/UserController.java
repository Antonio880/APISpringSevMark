package com.sevmark.SevMark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sevmark.SevMark.DTO.UserDTO;
import com.sevmark.SevMark.model.User;
import com.sevmark.SevMark.repository.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<UserDTO> getUsers() {
       return service.getAllUsers();
    }

    @PostMapping
    public UserDTO postUser(@RequestBody User entity) {
        return service.postUser(entity);
    }
    
}
