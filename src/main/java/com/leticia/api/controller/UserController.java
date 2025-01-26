package com.leticia.api.controller;

import com.leticia.api.domain.user.CreateUserDTO;
import com.leticia.api.domain.user.User;
import com.leticia.api.domain.user.UserRequestDTO;
import com.leticia.api.domain.user.UserResponseDTO;
import com.leticia.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private  UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDTO data) {
        User user = userService.createUser(data);
        return ResponseEntity.ok().body(user);
    }


}
