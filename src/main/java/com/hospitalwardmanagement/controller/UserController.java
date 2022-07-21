package com.hospitalwardmanagement.controller;

import com.hospitalwardmanagement.model.user.User;
import com.hospitalwardmanagement.payload.UserDTO;
import com.hospitalwardmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> addUser (@Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<User>(userService.createUser(userDTO), HttpStatus.CREATED);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUser (@Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<User>(userService.updateUser(userDTO), HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<User> deleteUserByEmail (@RequestParam String email) {
        userService.deleteUser(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
