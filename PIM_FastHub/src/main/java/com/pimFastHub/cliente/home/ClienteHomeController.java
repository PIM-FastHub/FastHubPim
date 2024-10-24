package com.pimFastHub.cliente.home;

import com.pimFastHub.cliente.home.service.ClienteServiceHome;
import com.pimFastHub.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ClienteHomeController {

    @Autowired private ClienteServiceHome service;
    @GetMapping("/cliente/home/{id}")
    public String home(@PathVariable (value="id") Long id, Model model){
        UserEntity usuario= service.buscaPorId(id);
        String nomeUsuario = usuario.getUsername();
        model.addAttribute("nomeUsuario",nomeUsuario);
        model.addAttribute("usuarioid", usuario.getId());
        return "cliente/home";
    }
}
