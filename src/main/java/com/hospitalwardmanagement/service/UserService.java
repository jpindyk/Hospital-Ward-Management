package com.hospitalwardmanagement.service;

import com.hospitalwardmanagement.model.user.User;
import com.hospitalwardmanagement.payload.UserDTO;

public interface UserService {
    User createUser (UserDTO userDTO);
    User updateUser(UserDTO userDTO);
    void deleteUser(String email);
    User getUserByEmail(String email);

    User getLoggedInUser();

}
