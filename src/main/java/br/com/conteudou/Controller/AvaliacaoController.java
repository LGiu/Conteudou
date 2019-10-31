package br.com.conteudou.Controller;

import br.com.conteudou.Model.Avaliacao;
import br.com.conteudou.Service.AvaliacaoService;
import br.com.conteudou.Util.DadosPaginados;
import br.com.conteudou.Util.Patch;
import br.com.conteudou.Util.Retorno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AvaliacaoController {

    private final Patch<Avaliacao> patch;

    @Autowired
    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        patch = new Patch<>(avaliacaoService, Avaliacao.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/avaliacao/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DadosPaginados<Avaliacao>> buscaAvaliacao(@RequestHeader(value = "ordem", required = false) String ordem,
                                                                    @RequestHeader(value = "tamanho", required = false) Integer tamanho,
                                                                    @RequestHeader(value = "paginaAtual", required = false) Integer paginaAtual,
                                                                    @PathVariable Long id) {
        return patch.consultar(id, ordem, tamanho, paginaAtual);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/avaliacaos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DadosPaginados<Avaliacao>> buscaAvaliacaos(@RequestHeader(value = "ordem", required = false) String ordem,
                                                                     @RequestHeader(value = "tamanho", required = false) Integer tamanho,
                                                                     @RequestHeader(value = "paginaAtual", required = false) Integer paginaAtual,
                                                                     @RequestHeader(value = "filtros", required = false) String filtros) {
        return patch.consultar(ordem, tamanho, paginaAtual, filtros);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/avaliacao", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> salvaAvaliacao(@RequestBody Avaliacao avaliacao) {
        return patch.salva(avaliacao);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/avaliacao", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> alteraAvaliacao(@RequestBody Avaliacao avaliacao) {
        return patch.altera(avaliacao);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/avaliacao/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> excluiAvaliacao(@PathVariable Long id) {
        return patch.exclui(id);
    }
}