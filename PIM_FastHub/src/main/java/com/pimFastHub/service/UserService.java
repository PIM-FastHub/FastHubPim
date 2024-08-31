package com.pimFastHub.service;


import com.pimFastHub.dto.RegistrationDTO;
import com.pimFastHub.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDTO paramRegistrationDto);

    UserEntity findByEmail(String paramString);

    UserEntity findByUsername(String paramString);
}
