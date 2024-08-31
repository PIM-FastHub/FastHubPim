package com.pimFastHub.servico;

import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ServicoDAO {
    private static final String TABELA = ServicoModel.class.getSimpleName();

    @PersistenceContext
    private EntityManager manager;

    public ServicoDAO(EntityManager manager) {
        this.manager = manager;
    }



    @Transactional
    public void insereItem(ServicoModel item) {
        this.manager.persist(item);
        this.manager.flush();
        this.manager.detach(item);
    }

    @Transactional
    public void alteraItem(ServicoModel item) {
        this.manager.merge(item);
        this.manager.flush();
        this.manager.detach(item);
    }

    @Transactional
    public void excluirItem(ServicoModel item) {
        this.manager.remove(this.manager.find(ServicoModel.class, item.getId()));
    }

    public List<ServicoModel> listaTodosItens() {
        String jpql = "SELECT i FROM " + TABELA + " i ORDER BY i.descricao ASC";
        TypedQuery<ServicoModel> query = this.manager.createQuery(jpql, ServicoModel.class);
        return query.getResultList();
    }

    public ServicoModel buscaPorID(Long id) {
        String jpql = "SELECT c FROM " + TABELA + " c WHERE c.id =:id ";
        TypedQuery<ServicoModel> query = this.manager.createQuery(jpql, ServicoModel.class);
        query.setParameter("id", id);
        ServicoModel resultado = (ServicoModel)query.getSingleResult();
        if (resultado != null) {
            return resultado;
        }
        return null;
    }

    public ServicoModel buscaItemPorId(Long id) {
        return (ServicoModel)this.manager.find(ServicoModel.class, id);
    }

    public List<ServicoModel> buscaPorIdCategoria(ServicoModel categoria) {
        String jpql = "SELECT i FROM " + TABELA + " i WHERE i.categoria =: categoria";
        TypedQuery<ServicoModel> query = this.manager.createQuery(jpql, ServicoModel.class);
        query.setParameter("categoria", categoria);
        return query.getResultList();
    }
}
