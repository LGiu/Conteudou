package br.com.conteudou.Controller;

import br.com.conteudou.Model.Comentario;
import br.com.conteudou.Service.ComentarioService;
import br.com.conteudou.Service.LoginService;
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
    public ComentarioController(ComentarioService comentarioService, LoginService loginService) {
        patch = new Patch<>(comentarioService, Comentario.class, loginService);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/comentario/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DadosPaginados<Comentario>> buscaComentario(@RequestParam(value = "ordem", required = false) String ordem,
                                                                      @RequestParam(value = "tamanho", required = false) Integer tamanho,
                                                                      @RequestParam(value = "paginaAtual", required = false) Integer paginaAtual,
                                                                      @PathVariable Long id) {
        return patch.consultar(id, ordem, tamanho, paginaAtual);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/comentarios", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DadosPaginados<Comentario>> buscaComentarios(@RequestParam(value = "ordem", required = false) String ordem,
                                                                       @RequestParam(value = "tamanho", required = false) Integer tamanho,
                                                                       @RequestParam(value = "paginaAtual", required = false) Integer paginaAtual,
                                                                       @RequestParam(value = "filtros", required = false) String filtros) {
        return patch.consultar(ordem, tamanho, paginaAtual, filtros);
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