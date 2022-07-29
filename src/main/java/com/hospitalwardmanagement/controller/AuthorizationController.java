package com.hospitalwardmanagement.controller;

import com.hospitalwardmanagement.model.user.User;
import com.hospitalwardmanagement.payload.UserDTO;
import com.hospitalwardmanagement.payload.UserLoginDTO;
import com.hospitalwardmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class AuthorizationController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login(@RequestBody UserLoginDTO user) {
        Authentication authentication = authenticationManager.authenticate(
                                            new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);

    }

    @PostMapping("/register")
    public ResponseEntity<User> addUser (@Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<User>(userService.createUser(userDTO), HttpStatus.CREATED);
    }

    @PostMapping("/logout")
    public ResponseEntity<HttpStatus> logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
}
