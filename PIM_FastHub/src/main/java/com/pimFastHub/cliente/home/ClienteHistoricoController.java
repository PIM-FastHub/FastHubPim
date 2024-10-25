package com.pimFastHub.cliente.home;

import com.pimFastHub.cliente.home.service.ClienteServiceHome;
import com.pimFastHub.models.UserEntity;
import com.pimFastHub.solicitacaoServico.SolicitacaoServicoDAO;
import com.pimFastHub.solicitacaoServico.SolicitacaoServicoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ClienteHistoricoController {
    @Autowired
    private ClienteServiceHome service;
    @Autowired private SolicitacaoServicoDAO solicitacaoDAO;

    @GetMapping("/cliente/historico/{idUsuario}")
    public String visualizaHistoricoAtendimentos(Model model, @PathVariable(value = "idUsuario") Long idUsuario){
        UserEntity usuario = service.buscaPorId(idUsuario);
        List<SolicitacaoServicoModel> listaServicos = solicitacaoDAO.listaSolicitacoesByCliente(usuario);

        model.addAttribute("usuarioid", usuario.getId());
        model.addAttribute("listaServicos", listaServicos);
        return "cliente/historico/exibe";

    }
}
