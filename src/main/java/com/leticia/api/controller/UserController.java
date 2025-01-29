package com.leticia.api.controller;

import com.leticia.api.domain.user.UserRequestDTO;
import com.leticia.api.domain.user.User;
import com.leticia.api.domain.user.UserResponseDTO;
import com.leticia.api.services.UserService;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid  @RequestBody UserRequestDTO data) {
        User user = userService.createUser(data);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping
    public ResponseEntity<List<User>>getAllUsers() {
        try {
            List <User> user = userService.getAllUsers();
            return ResponseEntity.ok(user);

        }catch(Exception e ) {
            throw new RuntimeException("Could not fetchd data");

        }
    }

    @GetMapping("/id")
    public ResponseEntity<User> getUserById(@PathVariable UUID userId ) {
        try{
            User user = userService.getUserById((userId));
            return ResponseEntity.ok(user);
        }catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @PutMapping("/id")
    public ResponseEntity<User> updateUser(@Valid @RequestBody UserRequestDTO data, @PathVariable UUID id) {
        try {
            User user = userService.updateUser(data, id);
            return ResponseEntity.ok(user);
        }catch(Exception e) {
            throw new RuntimeException();
        }
    }

    @DeleteMapping("/id")
    public void deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);

    }




}
