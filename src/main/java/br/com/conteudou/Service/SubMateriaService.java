package br.com.conteudou.Service;


import br.com.conteudou.Model.SubMateria;
import br.com.conteudou.Repository.SubMateriaRepository;
import br.com.conteudou.Util.Retorno;
import br.com.conteudou.Util.ServiceGenerico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SubMateriaService extends ServiceGenerico<SubMateria> {

    @Autowired
    public SubMateriaService(SubMateriaRepository subMateriaRepository) {
        super(subMateriaRepository, SubMateria.class);
    }

    @Override
    @Transactional
    public Retorno salva(SubMateria subMateria) {
        return super.salva(subMateria);
    }

    @Override
    public Retorno validador(SubMateria subMateria) {
        return super.validador(subMateria);
    }
}
