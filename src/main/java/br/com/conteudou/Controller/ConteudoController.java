package br.com.conteudou.Controller;

import br.com.conteudou.Model.Conteudo;
import br.com.conteudou.Service.ConteudoService;
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
    public ConteudoController(ConteudoService conteudoService) {
        patch = new Patch<>(conteudoService, Conteudo.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/conteudo/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DadosPaginados<Conteudo>> buscaConteudo(@RequestHeader(value = "ordem", required = false) String ordem,
                                                                    @RequestHeader(value = "tamanho", required = false) Integer tamanho,
                                                                    @RequestHeader(value = "paginaAtual", required = false) Integer paginaAtual,
                                                                    @PathVariable Long id) {
        return patch.consultar(id, ordem, tamanho, paginaAtual);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/conteudos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DadosPaginados<Conteudo>> buscaConteudos(@RequestHeader(value = "ordem", required = false) String ordem,
                                                           @RequestHeader(value = "tamanho", required = false) Integer tamanho,
                                                           @RequestHeader(value = "paginaAtual", required = false) Integer paginaAtual) {
        return patch.consultar(ordem, tamanho, paginaAtual);
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