package br.com.conteudou.Service;


import br.com.conteudou.Model.Comentario;
import br.com.conteudou.Repository.ComentarioRepository;
import br.com.conteudou.Util.Retorno;
import br.com.conteudou.Util.ServiceGenerico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ComentarioService extends ServiceGenerico<Comentario> {

    @Autowired
    public ComentarioService(ComentarioRepository comentarioRepository) {
        super(comentarioRepository, Comentario.class);
    }

    @Override
    @Transactional
    public Retorno salva(Comentario comentario) {
        return super.salva(comentario);
    }

    @Override
    public Retorno validador(Comentario comentario) {
        return super.validador(comentario);
    }
}
