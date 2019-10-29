package br.com.conteudou.Service;


import br.com.conteudou.Model.Avaliacao;
import br.com.conteudou.Repository.AvaliacaoRepository;
import br.com.conteudou.Util.Retorno;
import br.com.conteudou.Util.ServiceGenerico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AvaliacaoService extends ServiceGenerico<Avaliacao> {

    @Autowired
    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository) {
        super(avaliacaoRepository, Avaliacao.class);
    }

    @Override
    @Transactional
    public Retorno salva(Avaliacao avaliacao) {
        return super.salva(avaliacao);
    }

    @Override
    public Retorno validador(Avaliacao avaliacao) {
        return super.validador(avaliacao);
    }
}
