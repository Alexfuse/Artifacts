package org.artifacts.services;

import java.util.ArrayList;

import org.artifacts.entity.DAOUser;
import org.artifacts.entity.UserDTO;
import org.artifacts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DAOUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    public DAOUser save(UserDTO userDTO) {
        DAOUser newUser = new DAOUser();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(bcryptEncoder.encode(userDTO.getPassword()));
        return userRepository.save(newUser);
    }
}