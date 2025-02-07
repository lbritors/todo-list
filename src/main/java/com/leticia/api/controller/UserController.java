package com.leticia.api.controller;

import com.leticia.api.domain.user.UserRequestDTO;
import com.leticia.api.domain.user.User;
import com.leticia.api.domain.user.UserResponseDTO;
import com.leticia.api.services.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private  UserService userService;



    @PostMapping
    public ResponseEntity<Object> createUser(@Valid  @RequestBody UserRequestDTO data) {

        try {
            User user = userService.createUser(data);
            return ResponseEntity.ok().body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
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

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") UUID id) {
        try{
            User user = userService.getUserById((id));
            return ResponseEntity.ok(user);
        }catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@Valid @RequestBody UserRequestDTO data, @PathVariable UUID id) {
        try {
            User user = userService.updateUser(data, id);
            return ResponseEntity.ok(user);
        }catch(Exception e) {
            throw new RuntimeException();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.accepted().build();

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }




}
