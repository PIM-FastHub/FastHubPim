package com.pimFastHub.dto;


import javax.validation.constraints.NotEmpty;

import com.pimFastHub.servico.ServicoModel;
import lombok.Data;

@Data
public class RegistrationDTO {
    private Long id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;

    private String role;

    private ServicoModel servicoId;



    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setServico(ServicoModel servico){
        this.servicoId = servico;
    }

    public ServicoModel getServico(){
        return servicoId;
    }


}
