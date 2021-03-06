package br.com.conteudou.Model;


import br.com.conteudou.Interface.Model;
import br.com.conteudou.Util.Modelador;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "link", schema = "conteudou", uniqueConstraints = {@UniqueConstraint(columnNames = {"i_conteudo", "url"})})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Link extends Modelador<Link> implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "i_conteudo")
    @NotNull(message = "O coonteúdo deve ser informado!")
    private Conteudo conteudo;

    @Column(name = "icone")
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String icone;

    @Column(name = "url", length = 1000)
    @Size(max = 1000, message = "A link deve ser de 1000 caracteres!")
    private String url;

    @Column(name = "posicao")
    @Range(min = 1, message = "A posição deve ser de no mínimo 1!")
    private Integer posicao;

    @Override
    public void preInitializy() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Conteudo getConteudo() {
        return conteudo;
    }

    public void setConteudo(Conteudo conteudo) {
        this.conteudo = conteudo;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPosicao() {
        return posicao;
    }

    public void setPosicao(Integer posicao) {
        this.posicao = posicao;
    }
}
