package com.pimFastHub.controller;


import com.pimFastHub.dto.RegistrationDTO;
import com.pimFastHub.models.Role;
import com.pimFastHub.models.UserEntity;
import com.pimFastHub.repository.RoleRepository;
import com.pimFastHub.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.pimFastHub.service.UserService;
import com.pimFastHub.servico.ServicoDAO;
import com.pimFastHub.servico.ServicoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private ServicoDAO servicoDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            UserDetails userDetails = (UserDetails)auth.getPrincipal();
            if (userDetails.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN")))
                return "redirect:/home";
            return "redirect:/outraPagina";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("RoleView", "ROLE_ADMIN");
        return "login";
    }

    @GetMapping("/login/cliente")
    public String loginPageCliente(Model model){
        model.addAttribute("RoleView","ROLE_CLIENTE");
        return "login";
    }

    @GetMapping("/login/prestador")
    public String loginPagePrestador(Model model){
        model.addAttribute("listaServico", servicoDAO.listaTodosItens());
        model.addAttribute("RoleView","ROLE_PRESTADOR");
        return "login";
    }

    @GetMapping("register/prestador")
    public String getRegisterFormPrestador(Model model){
        RegistrationDTO user = new RegistrationDTO();
        model.addAttribute("user", user);
        List<Role> roleList = this.roleRepository.findAll();


        model.addAttribute("RoleView","ROLE_PRESTADOR");
        model.addAttribute("listaServico", servicoDAO.listaTodosItens());

        return "register";
    }

    @GetMapping("register/cliente")
    public String getRegisterFormCliente(Model model){
        RegistrationDTO user = new RegistrationDTO();
        model.addAttribute("user", user);
        List<Role> roleList = this.roleRepository.findAll();
        List<String> roleNameList = (List<String>)roleList.stream().map(Role::getName).collect(Collectors.toList());
        model.addAttribute("roleList", roleNameList);
        model.addAttribute("RoleView","ROLE_CLIENTE");
        return "register";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        RegistrationDTO user = new RegistrationDTO();
        model.addAttribute("user", user);
        List<Role> roleList = this.roleRepository.findAll();
        List<String> roleNameList = (List<String>)roleList.stream().map(Role::getName).collect(Collectors.toList());
        model.addAttribute("roleList", roleNameList);
        model.addAttribute("RoleView","ROLE_ADMIN");
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDTO user, BindingResult result, Model model, Long idServico) {UserEntity existingUserEmail = this.userService.findByEmail(user.getEmail());
        System.out.println(idServico);

        if(user.getRole().contains("PRESTADOR")) {
            ServicoModel servico = servicoDAO.buscaItemPorId(idServico);
            user.setServico(servico);
        }
        if (existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty())
            return "redirect:/register?fail";
        UserEntity existingUserUsername = this.userService.findByUsername(user.getUsername());
        if (existingUserUsername != null && existingUserUsername.getUsername() != null && !existingUserUsername.getUsername().isEmpty())
            return "redirect:/register?fail";
//        if (result.hasErrors()) {
//            model.addAttribute("user", user);
//            return "register";
//        }
        this.userService.saveUser(user);
        return "redirect:/login";
    }
}
