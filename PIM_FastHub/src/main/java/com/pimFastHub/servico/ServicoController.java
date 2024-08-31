package com.pimFastHub.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;


@Controller
public class ServicoController {

    @Autowired
    private ServicoDAO servicoDAO;
    @GetMapping("/administracao/servico/formulario")
    public String retornaInsercao(Model model){
        ServicoModel servico = new ServicoModel();

        model.addAttribute("servico", servico);
        return "administracao/servico/formulario";
    }

    @GetMapping("/administracao/servico/formulario/{id}")
    public String retornaEdicao(Model model, @PathVariable (value = "id") Long id){
        ServicoModel servico = servicoDAO.buscaItemPorId(id);
        model.addAttribute("servico", servico);
        return "administracao/servico/formulario";
    }

    @PostMapping("/administracao/servico/insere")
    public String insere(Model model,ServicoModel servico){
        ServicoModel servicoNovo = new ServicoModel();
        servicoNovo.setDescricao(servico.getDescricao());
        servicoNovo.setNome(servico.getNome());
        servicoNovo.setDtIncl(Calendar.getInstance());
        servicoDAO.insereItem(servicoNovo);
        return "redirect:/administracao/servico/lista";
    }

    @PostMapping("/administracao/servico/altera")
    public String altera(Model model, ServicoModel servico){
        ServicoModel servicoNovo = servicoDAO.buscaItemPorId(servico.getId());
        servicoNovo.setDtIncl(servico.getDtIncl());
        servicoNovo.setDescricao(servico.getDescricao());
        servicoNovo.setDtAlt(Calendar.getInstance());
        servicoNovo.setId(servico.getId());
        servicoDAO.alteraItem(servicoNovo);
        return "redirect:/administracao/servico/lista";
    }

    @GetMapping("/administracao/servico/lista")
    public String lista(Model model){
        List<ServicoModel> listaServico = servicoDAO.listaTodosItens();
        model.addAttribute("listaServico", listaServico);
        return "administracao/servico/lista";
    }

    @GetMapping("/administracao/servico/exclui/{id}")
    public String exclui(Model model,@PathVariable(value = "id") Long id ){
        ServicoModel servico = servicoDAO.buscaItemPorId(id);
        servicoDAO.excluirItem(servico);
        return "redirect:/administracao/servico/lista";
    }



    @GetMapping("/api/listaTodasCategorias")
    public ResponseEntity<List<ServicoModel>> listaServicosAPI(){
        List<ServicoModel> listagem = servicoDAO.listaTodosItens();
        return ResponseEntity.ok(listagem);
    }




}
