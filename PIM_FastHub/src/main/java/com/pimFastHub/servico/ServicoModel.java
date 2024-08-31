package com.pimFastHub.servico;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Entity
@Table(name="SERVICO")
public class ServicoModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nome;
    private String descricao;
    private Calendar dtIncl;
    private Calendar dtAlt;

    public ServicoModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Calendar getDtIncl() {
        return dtIncl;
    }

    public void setDtIncl(Calendar dtIncl) {
        this.dtIncl = dtIncl;
    }

    public Calendar getDtAlt() {
        return dtAlt;
    }

    public void setDtAlt(Calendar dtAlt) {
        this.dtAlt = dtAlt;
    }
}

