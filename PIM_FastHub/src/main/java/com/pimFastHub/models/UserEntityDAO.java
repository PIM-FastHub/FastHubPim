package com.pimFastHub.models;


import com.pimFastHub.servico.ServicoModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserEntityDAO {
    private static final String TABELA = UserEntity.class.getSimpleName();

    @PersistenceContext
    private EntityManager manager;

    public UserEntityDAO(EntityManager manager) {
        this.manager = manager;
    }



    @Transactional
    public void insereItem(UserEntity item) {
        this.manager.persist(item);
        this.manager.flush();
        this.manager.detach(item);
    }

    @Transactional
    public void alteraItem(UserEntity item) {
        this.manager.merge(item);
        this.manager.flush();
        this.manager.detach(item);
    }

    @Transactional
    public void excluirItem(UserEntity item) {
        this.manager.remove(this.manager.find(UserEntity.class, item.getId()));
    }

    public List<UserEntity> listaTodosItens() {
        String jpql = "SELECT i FROM " + TABELA + " i ORDER BY i.id ASC";
        TypedQuery<UserEntity> query = this.manager.createQuery(jpql, UserEntity.class);
        return query.getResultList();
    }

    public UserEntity buscaPorID(Long id) {
        String jpql = "SELECT c FROM " + TABELA + " c WHERE c.id =:id ";
        TypedQuery<UserEntity> query = this.manager.createQuery(jpql, UserEntity.class);
        query.setParameter("id", id);
        UserEntity resultado = (UserEntity)query.getSingleResult();
        if (resultado != null) {
            return resultado;
        }
        return null;
    }

    public UserEntity buscaItemPorId(Long id) {
        return (UserEntity)this.manager.find(UserEntity.class, id);
    }

    public List<UserEntity> listaUsuarioByRole(String role){
        String jpql = "SELECT u FROM "+TABELA + " u WHERE U.role = :role";
        TypedQuery<UserEntity> query = this.manager.createQuery(jpql, UserEntity.class);
        query.setParameter("role", role);
        List<UserEntity> resultado = query.getResultList();
        return resultado;
    }

}
