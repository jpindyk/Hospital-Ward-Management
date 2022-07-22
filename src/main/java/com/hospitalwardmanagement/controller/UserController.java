package com.hospitalwardmanagement.controller;

import com.hospitalwardmanagement.model.user.User;
import com.hospitalwardmanagement.payload.UserDTO;
import com.hospitalwardmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping
public class UserController {
    @Autowired
    UserService userService;

    @PutMapping("/updateUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> updateUser (@Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<User>(userService.updateUser(userDTO), HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> deleteUserByEmail (@RequestParam String email) {
        userService.deleteUser(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getUserByEmail  (@RequestParam String email) {
        return new ResponseEntity<User>(userService.getUserByEmail(email), HttpStatus.OK);
    }


}
