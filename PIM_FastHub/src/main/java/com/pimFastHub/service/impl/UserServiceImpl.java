package com.pimFastHub.service.impl;



import com.pimFastHub.dto.RegistrationDTO;
import com.pimFastHub.models.Role;
import com.pimFastHub.models.UserEntity;
import com.pimFastHub.repository.UserRepository;
import com.pimFastHub.service.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(RegistrationDTO registrationDto) {
        UserEntity user = new UserEntity();
        user.setServico(registrationDto.getServico());
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(this.passwordEncoder.encode(registrationDto.getPassword()));
        Role role = new Role();
        role.setName(registrationDto.getRole());
        user.getRoles().add(role);
        this.userRepository.save(user);
    }

    public UserEntity findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public UserEntity findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }
}
