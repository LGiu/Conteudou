package br.com.conteudou.Service;


import br.com.conteudou.Model.Link;
import br.com.conteudou.Repository.LinkRepository;
import br.com.conteudou.Util.Retorno;
import br.com.conteudou.Util.ServiceGenerico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class LinkService extends ServiceGenerico<Link> {

    @Autowired
    public LinkService(LinkRepository linkRepository) {
        super(linkRepository, Link.class);
    }

    @Override
    @Transactional
    public Retorno salva(Link link) {
        return super.salva(link);
    }

    @Override
    public Retorno validador(Link link) {
        return super.validador(link);
    }
}
