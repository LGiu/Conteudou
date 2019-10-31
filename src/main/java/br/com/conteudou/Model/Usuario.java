package br.com.conteudou.Model;


import br.com.conteudou.Interface.Model;
import br.com.conteudou.Util.Modelador;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuario", schema = "conteudou", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Usuario extends Modelador<Usuario> implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    @Email(message = "Email inválido")
    @NotNull(message = "O email deve ser informado!")
    @Size(max = 255, message = "O email deve possuir no máximo 255 caracteres!")
    private String email;

    @Column(name = "senha")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "A senha deve ser informada!")
    private String senha;

    @Column(name = "nome")
    @NotNull(message = "O nome deve ser informado!")
    @Size(max = 255, message = "O nome deve possuir no máximo 255 caracteres!")
    private String nome;

    @Column(name = "flag_administrador", columnDefinition = "boolean default false")
    private Boolean flagAdministrador;


    @Override
    public void preInitializy() {
        if (id == null && senha != null) {
            senha = new BCryptPasswordEncoder().encode(senha);
        }

        if (flagAdministrador == null) {
            flagAdministrador = false;
        }
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getFlagAdministrador() {
        return flagAdministrador;
    }

    public void setFlagAdministrador(Boolean flagAdministrador) {
        this.flagAdministrador = flagAdministrador;
    }
}
