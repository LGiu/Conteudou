package br.com.conteudou.Model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class Login implements UserDetails {

    private String email;

    private String senha;

    private String permissao;

    public Login(Usuario usuario) {
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        if (usuario.getFlagAdministrador() != null && usuario.getFlagAdministrador()) {
            this.permissao = "ADMIN";

        } else {
            this.permissao = "USER";
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
