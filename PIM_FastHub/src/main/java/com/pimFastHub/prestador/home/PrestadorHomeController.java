package com.pimFastHub.prestador.home;

import com.pimFastHub.encryptionUtil.EncryptionUtil;
import com.pimFastHub.models.UserEntity;
import com.pimFastHub.repository.UserRepository;
import com.pimFastHub.servico.ServicoDAO;
import com.pimFastHub.servico.ServicoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class PrestadorHomeController {

    @Autowired
    private ServicoDAO servicoDAO;

    @Autowired
    private EncryptionUtil encryptionUtil;

    @Autowired
    UserRepository repository;

    @GetMapping("/prestador/home/{id}")
    public String getTelaSelecaoDeTipoDeServico(Model model, @PathVariable (value = "id") Long id){
        List<ServicoModel> listaServicos = servicoDAO.listaTodosItens();

       Optional<UserEntity> usuario =  repository.findById(id);

       System.out.println(usuario.get().getServico().getDescricao());

       try{
           model.addAttribute("servico", usuario.get().getServico().getDescricao());

       }catch (Exception e){
           System.out.println("Ocorreu um erro");
       }


        return "prestador/home";
    }


}
