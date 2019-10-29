package br.com.conteudou.Service;


import br.com.conteudou.Model.Materia;
import br.com.conteudou.Repository.MateriaRepository;
import br.com.conteudou.Util.Retorno;
import br.com.conteudou.Util.ServiceGenerico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MateriaService extends ServiceGenerico<Materia> {

    @Autowired
    public MateriaService(MateriaRepository materiaRepository) {
        super(materiaRepository, Materia.class);
    }

    @Override
    @Transactional
    public Retorno salva(Materia materia) {
        return super.salva(materia);
    }

    @Override
    public Retorno validador(Materia materia) {
        return super.validador(materia);
    }
}
