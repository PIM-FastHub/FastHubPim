package com.pimFastHub.cliente.home.service;

import com.pimFastHub.models.UserEntity;
import com.pimFastHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceHome {
    @Autowired private UserRepository repository;

    public UserEntity buscaPorId(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    }
}

