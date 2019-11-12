package br.com.conteudou.Enum;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Comparador {

    IGUAL("IGUAL", "="),
    DIFERENTE("DIFERENTE", "<>"),
    CONTEM("CONTEM", "CONTEM");

    private final String chave;

    private final String descricao;

    public String getChave() {
        return chave;
    }

    public String getDescricao() {
        return descricao;
    }

    Comparador(String chave, String descricao) {
        this.chave = chave;
        this.descricao = descricao;
    }

    @JsonValue
    public String getValor() {
        switch (this) {
            case IGUAL:
            case DIFERENTE:
            case CONTEM:
                return getChave();
        }
        return null;
    }


}