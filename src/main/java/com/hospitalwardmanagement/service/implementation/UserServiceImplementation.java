package com.hospitalwardmanagement.service.implementation;

import com.hospitalwardmanagement.exceptions.ResourceNotFoundException;
import com.hospitalwardmanagement.exceptions.UserAlreadyRegistered;
import com.hospitalwardmanagement.model.user.User;
import com.hospitalwardmanagement.model.user.UserRole;
import com.hospitalwardmanagement.payload.UserDTO;
import com.hospitalwardmanagement.repository.RoleRepository;
import com.hospitalwardmanagement.repository.UserRepository;
import com.hospitalwardmanagement.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new UserAlreadyRegistered(userDTO.getEmail());
        }

        User user = mapToEntity(userDTO);
        user.setPassword(bcryptEncoder.encode(userDTO.getPassword()));

        UserRole roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));

        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserDTO userDTO) {
        User existingUser = getUserByEmail(userDTO.getEmail());

        existingUser.setFirstName(userDTO.getFirstName());
        existingUser.setLastName(userDTO.getLastName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPassword(bcryptEncoder.encode(userDTO.getPassword()));

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(String email) {
        User userToDelete = getUserByEmail(email);
        userRepository.delete(userToDelete);
    }
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                ()-> new ResourceNotFoundException("User", "email", email)
        );
    }

    @Override
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return getUserByEmail(email);
    }

    private User mapToEntity (UserDTO userDTO) {
        User user = mapper.map(userDTO, User.class);
        return user;
    }



}
