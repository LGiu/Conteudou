package br.com.conteudou.Controller;

import br.com.conteudou.Model.Link;
import br.com.conteudou.Service.LinkService;
import br.com.conteudou.Service.LoginService;
import br.com.conteudou.Util.DadosPaginados;
import br.com.conteudou.Util.Patch;
import br.com.conteudou.Util.Retorno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LinkController {

    private final Patch<Link> patch;

    @Autowired
    public LinkController(LinkService linkService, LoginService loginService) {
        patch = new Patch<>(linkService, Link.class, loginService);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/link/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DadosPaginados<Link>> buscaLink(@RequestParam(value = "ordem", required = false) String ordem,
                                                          @RequestParam(value = "tamanho", required = false) Integer tamanho,
                                                          @RequestParam(value = "paginaAtual", required = false) Integer paginaAtual,
                                                          @PathVariable Long id) {
        return patch.consultar(id, ordem, tamanho, paginaAtual);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/links", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DadosPaginados<Link>> buscaLinks(@RequestParam(value = "ordem", required = false) String ordem,
                                                           @RequestParam(value = "tamanho", required = false) Integer tamanho,
                                                           @RequestParam(value = "paginaAtual", required = false) Integer paginaAtual,
                                                           @RequestParam(value = "filtros", required = false) String filtros) {
        return patch.consultar(ordem, tamanho, paginaAtual, filtros);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/link", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> salvaLink(@RequestBody Link link) {
        return patch.salva(link);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/link", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> alteraLink(@RequestBody Link link) {
        return patch.altera(link);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/link/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> excluiLink(@PathVariable Long id) {
        return patch.exclui(id);
    }
}