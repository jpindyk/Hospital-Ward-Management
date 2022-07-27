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
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PutMapping("/update")
    public ResponseEntity<User> updateUser (@RequestParam String email, @Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<User>(userService.updateUser(email, userDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> deleteUserByEmail (@RequestParam String email) {
        userService.deleteUser(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getUserByEmail  (@RequestParam String email) {
        return new ResponseEntity<User>(userService.getUserByEmail(email), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getUserByID  (@PathVariable Long id) {
        return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> setRole (@RequestParam String email, @RequestParam int roleId) {
        return new ResponseEntity<User>(userService.setRole(email, roleId), HttpStatus.OK);
    }


}
