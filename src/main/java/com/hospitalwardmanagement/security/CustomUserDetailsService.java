package com.hospitalwardmanagement.security;

import com.hospitalwardmanagement.model.user.User;
import com.hospitalwardmanagement.model.user.UserRole;
import com.hospitalwardmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User existingUser = userRepository.findByEmail(email).orElseThrow(
                ()-> new UsernameNotFoundException("User not found for the email: " + email)
        );

        return new org.springframework.security.core.userdetails.User(existingUser.getEmail(),
                                                            existingUser.getPassword(),
                                                            mapRolesToAuthorities(existingUser.getRoles()));
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<UserRole> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
