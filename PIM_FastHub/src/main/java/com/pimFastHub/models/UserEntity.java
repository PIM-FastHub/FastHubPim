package com.pimFastHub.models;


import com.pimFastHub.servico.ServicoModel;

import javax.persistence.*;



import java.util.ArrayList;
import java.util.List;


@Entity(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;
    @ManyToOne
    private ServicoModel servicoObj;

    private Boolean temFastCash;
    public UserEntity(Long id, String username, String email, String password, List<Role> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "users_roles", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles = new ArrayList<>();

    public List<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

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

    public void setServico(ServicoModel s){
        this.servicoObj = s;
    }

    public ServicoModel getServico(){
        return servicoObj;
    }

    public Boolean getTemFastCash() {
        return temFastCash;
    }

    public void setTemFastCash(Boolean temFastCash) {
        this.temFastCash = temFastCash;
    }

    public UserEntity() {}
}

