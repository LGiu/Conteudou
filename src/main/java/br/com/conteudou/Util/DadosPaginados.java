package br.com.conteudou.Util;

import br.com.conteudou.Interface.Model;

import java.util.List;

public class DadosPaginados<U extends Model> {

    private Integer numeroPagina;
    private Integer quantidadeRegistros;
    private Integer quantidadePaginas;
    private Integer tamanhoPagina;
    private List<U> Conteudo;

    public Integer getNumeroPagina() {
        return numeroPagina;
    }

    public void setNumeroPagina(Integer numeroPagina) {
        this.numeroPagina = numeroPagina;
    }

    public Integer getQuantidadeRegistros() {
        return quantidadeRegistros;
    }

    public void setQuantidadeRegistros(Integer quantidadeRegistros) {
        this.quantidadeRegistros = quantidadeRegistros;
    }

    public Integer getQuantidadePaginas() {
        return quantidadePaginas;
    }

    public void setQuantidadePaginas(Integer quantidadePaginas) {
        this.quantidadePaginas = quantidadePaginas;
    }

    public Integer getTamanhoPagina() {
        return tamanhoPagina;
    }

    public void setTamanhoPagina(Integer tamanhoPagina) {
        this.tamanhoPagina = tamanhoPagina;
    }

    public List<U> getConteudo() {
        return Conteudo;
    }

    public void setConteudo(List<U> conteudo) {
        Conteudo = conteudo;
    }
}
