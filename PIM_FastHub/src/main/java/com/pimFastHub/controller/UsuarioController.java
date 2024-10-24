package com.pimFastHub.controller;


import com.pimFastHub.models.UserEntity;
import com.pimFastHub.models.UserEntityDAO;
import com.pimFastHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UsuarioController {

    @Autowired private UserRepository repository;
    @Autowired private UserEntityDAO userDAO;
//                            //controle ==> ou prestador/cliente
//    @GetMapping("/administracao/usuario/{controle}")
//    public String listagemUsuario(@PathVariable (value = "controle") String tipoUsuario, Model model){
//
//
//        List<UserEntity> listaUsuario = userDAO.listaUsuarioByRole("role");
//        model.addAttribute("listaUsuario",listaUsuario );
//        return "";
//    }

    @GetMapping("/administracao/usuario/lista")
    public String listaTodos(Model model){
        List<UserEntity> listaTodos = repository.findAll();
        model.addAttribute("listaTodos", listaTodos);
        return "adm/usuario/lista";
    }

    public String retornaValorRole(){
        return "";
    }
}
