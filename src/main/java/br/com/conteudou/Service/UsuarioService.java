package br.com.conteudou.Service;


import br.com.conteudou.Model.Usuario;
import br.com.conteudou.Repository.UsuarioRepository;
import br.com.conteudou.Util.Retorno;
import br.com.conteudou.Util.ServiceGenerico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UsuarioService extends ServiceGenerico<Usuario> {

    private final LoginService loginService;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, LoginService loginService) {
        super(usuarioRepository, Usuario.class);
        this.loginService = loginService;
    }

    @Override
    @Transactional
    public Retorno salva(Usuario usuario) {
        return super.salva(usuario);
    }

    @Override
    public Retorno validador(Usuario usuario) {
        Retorno retorno = super.validador(usuario);
        if (retorno.isErro()) {
            return retorno;
        }

        if (usuario.getId() != null && !loginService.getUsuarioAtual().getFlagAdministrador() && !loginService.getUsuarioAtual().getId().equals(usuario.getId())) {
            return new Retorno("Sem permissão para alterar este usuário");
        }

        return retorno;
    }

    @Override
    public Usuario buscaPorId(Long id, String ordem, Integer tamanho, Integer paginaAtual) {
        try {
            Usuario usuario;
            if (id == null) {
                usuario = loginService.getUsuarioAtual();
            } else {
                usuario = super.buscaPorId(id);

                if (usuario.getId() != null && !loginService.getUsuarioAtual().getFlagAdministrador()) {
                    return null;
                }
            }

            return usuario;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional
    public Retorno exclui(Long id) {
        try {
            Usuario usuario = buscaPorId(id);
            if (usuario.getId() != null && !loginService.getUsuarioAtual().getFlagAdministrador()) {
                return new Retorno("Sem permissão para excluir este usuário");
            }
            return super.exclui(usuario);
        } catch (Exception e) {
            return new Retorno<>(e.getMessage());
        }
    }

}
