package com.hospitalwardmanagement.service.implementation;

import com.hospitalwardmanagement.exceptions.ResourceNotFoundException;
import com.hospitalwardmanagement.exceptions.UserAlreadyRegistered;
import com.hospitalwardmanagement.model.user.User;
import com.hospitalwardmanagement.model.user.UserRole;
import com.hospitalwardmanagement.payload.UserDTO;
import com.hospitalwardmanagement.repository.UserRepository;
import com.hospitalwardmanagement.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PasswordEncoder bcryptEncoder;


    @Override
    public User createUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new UserAlreadyRegistered(userDTO.getEmail());
        }

        User user = mapToEntity(userDTO);
        user.setPassword(bcryptEncoder.encode(userDTO.getPassword()));
        user.setRole(UserRole.ROLE_DEFAULT_USER);

        return userRepository.save(user);
    }

    @Override
    public User updateUser(String email, UserDTO userDTO) {
        User existingUser = getUserByEmail(email);

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
                () -> new ResourceNotFoundException("User", "email", email)
        );
    }

    @Override
    public User getLoggedInUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByEmail(email);
    }

    @Override
    public User setRole(String email, int roleId) {
        User user = getUserByEmail(email);
        user.setRole(UserRole.ofNr(roleId));
        return userRepository.save(user);

    }

    private User mapToEntity(UserDTO userDTO) {
        User user = mapper.map(userDTO, User.class);
        return user;

    }


}
