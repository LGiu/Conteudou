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
@Table(name = "materia", schema = "conteudou", uniqueConstraints = {@UniqueConstraint(columnNames = {"i_curso", "nome"})})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Materia implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "i_curso")
    private Curso curso;

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

    @OneToMany(mappedBy = "materia", cascade = CascadeType.REMOVE)
    private List<SubMateria> subMaterias;

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

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
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

    public List<SubMateria> getSubMaterias() {
        return subMaterias;
    }

    public void setSubMaterias(List<SubMateria> subMaterias) {
        this.subMaterias = subMaterias;
    }
}
