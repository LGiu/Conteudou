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

    private final LoginService loginService;

    @Autowired
    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository, LoginService loginService) {
        super(avaliacaoRepository, Avaliacao.class);
        this.loginService = loginService;
    }

    @Override
    @Transactional
    public Retorno salva(Avaliacao avaliacao) {
        if (avaliacao.getId() == null) {
            avaliacao.setUsuario(loginService.getUsuarioAtual());
        }
        return super.salva(avaliacao);
    }

    @Override
    public Retorno validador(Avaliacao avaliacao) {
        return super.validador(avaliacao);
    }
}
