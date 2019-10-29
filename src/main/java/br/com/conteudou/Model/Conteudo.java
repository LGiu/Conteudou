package br.com.conteudou.Model;


import br.com.conteudou.Interface.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "conteudo", schema = "conteudou", uniqueConstraints = {@UniqueConstraint(columnNames = {"i_sub_materia", "nome"})})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Conteudo implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "i_usuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "i_sub_materia")
    private SubMateria subMateria;

    @Column(name = "nome")
    @Size(max = 255, message = "O nome deve ser de 255 caracteres!")
    @NotNull(message = "O nome deve ser informado!")
    private String nome;

    @Column(name = "descricao", length = 2500)
    @Size(max = 2500, message = "A descrição deve ser de 2500 caracteres!")
    private String descricao;

    @Column(name = "cor")
    @NotNull(message = "A cor deve ser informada!")
    private String cor;

    @OneToMany(mappedBy = "conteudo", cascade = CascadeType.REMOVE)
    private List<Link> links;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonSerialize()
    @Column(name = "data_criacao")
    private Date dataCriacao;

    @Override
    public void preInitializy() {
        if(dataCriacao == null){
            dataCriacao = new Date();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public SubMateria getSubMateria() {
        return subMateria;
    }

    public void setSubMateria(SubMateria subMateria) {
        this.subMateria = subMateria;
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

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
