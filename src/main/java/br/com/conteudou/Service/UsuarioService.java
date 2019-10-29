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

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        super(usuarioRepository, Usuario.class);
    }

    @Override
    @Transactional
    public Retorno salva(Usuario usuario) {
        return super.salva(usuario);
    }

    @Override
    public Retorno validador(Usuario usuario) {
        return super.validador(usuario);
    }
}
