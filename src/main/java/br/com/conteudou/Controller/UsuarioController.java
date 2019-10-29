package br.com.conteudou.Controller;

import br.com.conteudou.Model.Usuario;
import br.com.conteudou.Service.UsuarioService;
import br.com.conteudou.Util.DadosPaginados;
import br.com.conteudou.Util.Patch;
import br.com.conteudou.Util.Retorno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController {

    private final Patch<Usuario> patch;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        patch = new Patch<>(usuarioService, Usuario.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/usuario/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DadosPaginados<Usuario>> buscaUsuario(@RequestHeader(value = "ordem", required = false) String ordem,
                                                            @RequestHeader(value = "tamanho", required = false) Integer tamanho,
                                                            @RequestHeader(value = "paginaAtual", required = false) Integer paginaAtual,
                                                            @PathVariable Long id) {
        return patch.consultar(id, ordem, tamanho, paginaAtual);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DadosPaginados<Usuario>> buscaUsuarios(@RequestHeader(value = "ordem", required = false) String ordem,
                                                           @RequestHeader(value = "tamanho", required = false) Integer tamanho,
                                                           @RequestHeader(value = "paginaAtual", required = false) Integer paginaAtual) {
        return patch.consultar(ordem, tamanho, paginaAtual);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> salvaUsuario(@RequestBody Usuario usuario) {
        return patch.salva(usuario);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> alteraUsuario(@RequestBody Usuario usuario) {
        return patch.altera(usuario);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/usuario/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> excluiUsuario(@PathVariable Long id) {
        return patch.exclui(id);
    }
}