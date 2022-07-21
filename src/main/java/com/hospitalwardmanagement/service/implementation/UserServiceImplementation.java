package com.hospitalwardmanagement.service.implementation;

import com.hospitalwardmanagement.exceptions.ResourceNotFoundException;
import com.hospitalwardmanagement.exceptions.UserAlreadyRegistered;
import com.hospitalwardmanagement.model.user.User;
import com.hospitalwardmanagement.payload.UserDTO;
import com.hospitalwardmanagement.repository.UserRepository;
import com.hospitalwardmanagement.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public User createUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new UserAlreadyRegistered(userDTO.getEmail());
        }

        User user = mapToEntity(userDTO);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserDTO userDTO) {
        User existingUser = findUserByEmail(userDTO.getEmail());

        existingUser.setFirstName(userDTO.getFirstName());
        existingUser.setLastName(userDTO.getLastName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPassword(userDTO.getPassword());

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(String email) {
        User userToDelete = findUserByEmail(email);
        userRepository.delete(userToDelete);
    }


    private User mapToEntity (UserDTO userDTO) {
        User user = mapper.map(userDTO, User.class);
        return user;
    }

    private User findUserByEmail (String email) {
        return userRepository.findByEmail(email).orElseThrow(
                ()-> new ResourceNotFoundException("User", "email", email)
        );
    }


}
