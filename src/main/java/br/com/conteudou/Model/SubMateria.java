package br.com.conteudou.Model;


import br.com.conteudou.Interface.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sub_materia", schema = "conteudou", uniqueConstraints = {@UniqueConstraint(columnNames = {"i_materia", "nome"})})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SubMateria implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "i_materia")
    private Materia materia;

    @Column(name = "nome")
    @Size(max = 255, message = "O nome deve ser de 255 caracteres!")
    @NotNull(message = "O nome deve ser informado!")
    private String nome;

    @Column(name = "descricao")
    @Size(max = 255, message = "A descrição deve ser de 255 caracteres!")
    private String descricao;

    @Column(name = "cor")
    @NotNull(message = "A cor deve ser informada!")
    private String cor;

    @Column(name = "data_criacao")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dataCriacao;

    @OneToMany(mappedBy = "subMateria", cascade = CascadeType.REMOVE)
    private List<Conteudo> conteudos;

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

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
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

    public List<Conteudo> getConteudos() {
        return conteudos;
    }

    public void setConteudos(List<Conteudo> conteudos) {
        this.conteudos = conteudos;
    }
}
