package com.pimFastHub.solicitacaoServico;

import com.pimFastHub.cliente.home.service.ClienteServiceHome;
import com.pimFastHub.models.UserEntity;
import com.pimFastHub.prestadorUtil.PrestadorService;
import com.pimFastHub.service.UserService;
import com.pimFastHub.servico.ServicoDAO;
import com.pimFastHub.servico.ServicoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Calendar;
import java.util.List;

@Controller
public class SolicitacaoServicoController {
    @Autowired private ClienteServiceHome service;
    @Autowired private ServicoDAO servicoDAO;
    @Autowired private SolicitacaoServicoDAO solicitacaoServicoDAO;

    @Autowired private PrestadorService prestadorService;

    @GetMapping("/cliente/solicitacao/{id}")
    public String solicitacaoServico(Model model, @PathVariable(value = "id") Long id) {
        UserEntity usuario = service.buscaPorId(id);
        List<ServicoModel> servicos = servicoDAO.listaTodosItens();

        // Criar um novo objeto para o formulário
        SolicitacaoServicoModel solicitacaoServico = new SolicitacaoServicoModel();
        solicitacaoServico.setClienteId(usuario); // Definir o cliente

        // Adicionar o objeto de formulário ao model
        model.addAttribute("solicitacaoServico", solicitacaoServico);
        model.addAttribute("usuarioid", usuario.getId());
        model.addAttribute("servicos", servicos);

        return "cliente/solicitacao/formulario";
    }

    @PostMapping("/solicitacao/salvar")
    public String salvarSolicitacao(@ModelAttribute SolicitacaoServicoModel solicitacaoServico) {
        solicitacaoServico.setDataSolicitacao(Calendar.getInstance());
        solicitacaoServicoDAO.insereItem(solicitacaoServico);
        return "redirect:/cliente/solicitacao/"+solicitacaoServico.getClienteId().getId();
    }

    @GetMapping("/prestador/solicitacoes/{id}")
    public String feedSolicitacoes(@PathVariable (value = "id") Long id, Model model){
        UserEntity usuario = service.buscaPorId(id);
        ServicoModel servico = servicoDAO.buscaItemPorId(usuario.getServico().getId());

        List<SolicitacaoServicoModel> listaDeSolicitacoes = solicitacaoServicoDAO.listaSolicitacaoByServico(servico);

        model.addAttribute("listaDeSolicitacoes",listaDeSolicitacoes);
        model.addAttribute("usuarioId", usuario.getId());

        return "prestador/solicitacao/feed";

    }

    @GetMapping("/prestador/solicitacao/detalhes/{idSolicitacao}/{idUsuario}")
    public String detalhesSolicitacao(@PathVariable (value = "idSolicitacao") Long idSolicitacao, @PathVariable(value = "idUsuario") Long idUsuario, Model model){
        SolicitacaoServicoModel solicitacao = solicitacaoServicoDAO.buscaItemPorId(idSolicitacao);
        UserEntity usuario = service.buscaPorId(idUsuario);
        Boolean temCash = prestadorService.validaPrestadorFastCash(usuario);

        prestadorService.insereEmailSeUsuarioTiverFastCash(temCash,model,usuario);
        model.addAttribute("temCash", temCash);
        model.addAttribute("solicitacao", solicitacao);
        return "prestador/solicitacao/detalhe";
    }


}
