package br.com.conteudou.Service;


import br.com.conteudou.Model.Conteudo;
import br.com.conteudou.Repository.ConteudoRepository;
import br.com.conteudou.Util.Retorno;
import br.com.conteudou.Util.ServiceGenerico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ConteudoService extends ServiceGenerico<Conteudo> {

    private final LoginService loginService;

    @Autowired
    public ConteudoService(ConteudoRepository conteudoRepository, LoginService loginService) {
        super(conteudoRepository, Conteudo.class);
        this.loginService = loginService;
    }

    @Override
    @Transactional
    public Retorno salva(Conteudo conteudo) {
        if (conteudo.getId() == null) {
            conteudo.setUsuario(loginService.getUsuarioAtual());
        }
        return super.salva(conteudo);
    }

    @Override
    public Retorno validador(Conteudo conteudo) {
        return super.validador(conteudo);
    }
}
