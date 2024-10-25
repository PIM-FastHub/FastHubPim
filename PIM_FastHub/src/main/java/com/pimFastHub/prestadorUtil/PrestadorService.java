package com.pimFastHub.prestadorUtil;

import com.pimFastHub.cliente.home.service.ClienteServiceHome;
import com.pimFastHub.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class PrestadorService {
    @Autowired private ClienteServiceHome service;

    public UserEntity buscaPrestadorPorId(Long id){
        UserEntity usuario = service.buscaPorId(id);
        return usuario;
    }



    public void insereEmailSeUsuarioTiverFastCash(Boolean temCash, Model model, UserEntity usuario){
        if(temCash){
            model.addAttribute("usuarioEmail", usuario.getEmail());
        }
    }

    public Boolean validaPrestadorFastCash(UserEntity usuario){

        try{
            if(usuario.getTemFastCash()){
                return true;
            }else{
                return false;
            }

        }catch(Exception e){
            return false;
        }


    }

}
