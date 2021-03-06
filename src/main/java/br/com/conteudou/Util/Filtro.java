package br.com.conteudou.Util;

import br.com.conteudou.Enum.Comparador;

public class Filtro {

    private String atributo;

    private String join;

    private Comparador comparador;

    private String valor;

    public Filtro(String atributo, Comparador comparador, String valor) {
        this.atributo = atributo;
        this.comparador = comparador;
        this.valor = valor;
        this.join = null;
    }

    public Filtro(String atributo, Comparador comparador, String valor, String join) {
        this.atributo = atributo;
        this.comparador = comparador;
        this.valor = valor;
        this.join = join;
    }


    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    public Comparador getComparador() {
        return comparador;
    }

    public void setComparador(Comparador comparador) {
        this.comparador = comparador;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getJoin() {
        return join;
    }

    public void setJoin(String join) {
        this.join = join;
    }
}
