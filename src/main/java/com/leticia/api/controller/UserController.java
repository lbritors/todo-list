package com.leticia.api.controller;

import com.leticia.api.domain.user.User;
import com.leticia.api.domain.user.UserRequestDTO;
import com.leticia.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private  UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        User user = userService.createUser(userRequestDTO);
        return ResponseEntity.ok().body(user);
    }


}
