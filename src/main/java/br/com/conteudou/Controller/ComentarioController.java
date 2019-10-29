package br.com.conteudou.Controller;

import br.com.conteudou.Model.Comentario;
import br.com.conteudou.Service.ComentarioService;
import br.com.conteudou.Util.DadosPaginados;
import br.com.conteudou.Util.Patch;
import br.com.conteudou.Util.Retorno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ComentarioController {

    private final Patch<Comentario> patch;

    @Autowired
    public ComentarioController(ComentarioService comentarioService) {
        patch = new Patch<>(comentarioService, Comentario.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/comentario/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DadosPaginados<Comentario>> buscaComentario(@RequestHeader(value = "ordem", required = false) String ordem,
                                                                    @RequestHeader(value = "tamanho", required = false) Integer tamanho,
                                                                    @RequestHeader(value = "paginaAtual", required = false) Integer paginaAtual,
                                                                    @PathVariable Long id) {
        return patch.consultar(id, ordem, tamanho, paginaAtual);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/comentarios", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DadosPaginados<Comentario>> buscaComentarios(@RequestHeader(value = "ordem", required = false) String ordem,
                                                           @RequestHeader(value = "tamanho", required = false) Integer tamanho,
                                                           @RequestHeader(value = "paginaAtual", required = false) Integer paginaAtual) {
        return patch.consultar(ordem, tamanho, paginaAtual);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/comentario", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> salvaComentario(@RequestBody Comentario comentario) {
        return patch.salva(comentario);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/comentario", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> alteraComentario(@RequestBody Comentario comentario) {
        return patch.altera(comentario);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/comentario/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> excluiComentario(@PathVariable Long id) {
        return patch.exclui(id);
    }
}