package com.pimFastHub.controller;


import com.pimFastHub.cliente.home.service.ClienteServiceHome;
import com.pimFastHub.models.UserEntity;
import com.pimFastHub.solicitacaoServico.SolicitacaoServicoDAO;
import com.pimFastHub.solicitacaoServico.SolicitacaoServicoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MeuPerfil {

    @Autowired private ClienteServiceHome service;
    @Autowired private SolicitacaoServicoDAO dao;

    @GetMapping("/prestador/meuperfil/{id}")
    public String meuPerfilPRestador(Model model, @PathVariable(value = "id") Long id){
        UserEntity usuario = service.buscaPorId(id);
        List<SolicitacaoServicoModel> listaSolicitacoes = dao.listaSolicitacoesByPrestador(usuario);
        List<SolicitacaoServicoModel> listaUltimas_3_atendidas = dao.listaSolicitacoesByPrestador_Ultimas(usuario);
        int qtdAtendidas = listaSolicitacoes.size();


        model.addAttribute("listaUltimas_3_atendidas", listaUltimas_3_atendidas);
        model.addAttribute("qtdAtendidas", qtdAtendidas);
        model.addAttribute("usuario", usuario);
        return "/prestador/meuperfil/exibir";
    }

}
