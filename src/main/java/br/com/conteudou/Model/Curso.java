package br.com.conteudou.Model;


import br.com.conteudou.Interface.Model;
import br.com.conteudou.Util.Modelador;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "curso", schema = "conteudou", uniqueConstraints = {@UniqueConstraint(columnNames = {"nome"})})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Curso extends Modelador<Curso> implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    @Size(max = 255, message = "O nome deve ser de 255 caracteres!")
    @NotNull(message = "O nome deve ser informado!")
    private String nome;

    @Column(name = "descricao")
    @Size(max = 255, message = "A descrição deve ser de 255 caracteres!")
    private String descricao;

    @Column(name = "posicao")
    @Range(min = 1, message = "A posição deve ser de no mínimo 1!")
    private Integer posicao;

    @Column(name = "cor")
    @NotNull(message = "A cor deve ser informada!")
    private String cor;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.REMOVE)
    private List<Materia> materias;

    @Override
    public void preInitializy() {
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

    public Integer getPosicao() {
        return posicao;
    }

    public void setPosicao(Integer posicao) {
        this.posicao = posicao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }
}
