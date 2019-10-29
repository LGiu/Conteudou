package br.com.conteudou.Service;


import br.com.conteudou.Model.Curso;
import br.com.conteudou.Repository.CursoRepository;
import br.com.conteudou.Util.Retorno;
import br.com.conteudou.Util.ServiceGenerico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CursoService extends ServiceGenerico<Curso> {

    @Autowired
    public CursoService(CursoRepository cursoRepository) {
        super(cursoRepository, Curso.class);
    }

    @Override
    @Transactional
    public Retorno salva(Curso curso) {
        return super.salva(curso);
    }

    @Override
    public Retorno validador(Curso curso) {
        return super.validador(curso);
    }
}
