package com.pimFastHub.security;

import com.pimFastHub.encryptionUtil.EncryptionUtil;
import com.pimFastHub.models.UserEntity;
import com.pimFastHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class CustomAuthSuccesHandler implements AuthenticationSuccessHandler {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EncryptionUtil encryptionUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername(); // Obter o e-mail (ou nome de usuário)

        // Buscar o usuário usando o e-mail
        UserEntity userEntity = userRepository.findByEmail(email);
        Long id = userEntity.getId();

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("/administracao/home");
        } else if (roles.contains("ROLE_PRESTADOR")) {
            response.sendRedirect("/prestador/home/" + id); // Redireciona com o ID do usuário
        } else if (roles.contains("ROLE_CLIENTE")) {
            response.sendRedirect("/cliente/home/" + id); // Redireciona com o ID do usuário
        } else {
            response.sendRedirect("/default");
        }
    }
}
