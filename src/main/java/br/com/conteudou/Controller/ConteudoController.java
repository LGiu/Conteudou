package br.com.conteudou.Controller;

import br.com.conteudou.Model.Conteudo;
import br.com.conteudou.Service.ConteudoService;
import br.com.conteudou.Service.LoginService;
import br.com.conteudou.Util.DadosPaginados;
import br.com.conteudou.Util.Patch;
import br.com.conteudou.Util.Retorno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConteudoController {

    private final Patch<Conteudo> patch;

    @Autowired
    public ConteudoController(ConteudoService conteudoService, LoginService loginService) {
        patch = new Patch<>(conteudoService, Conteudo.class, loginService);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/conteudo/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DadosPaginados<Conteudo>> buscaConteudo(@RequestParam(value = "ordem", required = false) String ordem,
                                                                  @RequestParam(value = "tamanho", required = false) Integer tamanho,
                                                                  @RequestParam(value = "paginaAtual", required = false) Integer paginaAtual,
                                                                  @PathVariable Long id) {
        return patch.consultar(id, ordem, tamanho, paginaAtual);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/conteudos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DadosPaginados<Conteudo>> buscaConteudos(@RequestParam(value = "ordem", required = false) String ordem,
                                                                   @RequestParam(value = "tamanho", required = false) Integer tamanho,
                                                                   @RequestParam(value = "paginaAtual", required = false) Integer paginaAtual,
                                                                   @RequestParam(value = "filtros", required = false) String filtros) {
        return patch.consultar(ordem, tamanho, paginaAtual, filtros);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/conteudo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> salvaConteudo(@RequestBody Conteudo conteudo) {
        return patch.salva(conteudo);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/conteudo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> alteraConteudo(@RequestBody Conteudo conteudo) {
        return patch.altera(conteudo);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/conteudo/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> excluiConteudo(@PathVariable Long id) {
        return patch.exclui(id);
    }
}