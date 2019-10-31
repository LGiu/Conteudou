package br.com.conteudou.Interface;

import java.util.Date;

public interface Model {

    Long getId();

    void preInitializy();

    void setDataCriacao(Date dataCriacao);

    void setDataAlteracao(Date dataAlteracao);

}
