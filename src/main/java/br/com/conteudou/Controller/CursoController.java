package br.com.conteudou.Controller;

import br.com.conteudou.Model.Curso;
import br.com.conteudou.Service.CursoService;
import br.com.conteudou.Service.LoginService;
import br.com.conteudou.Util.DadosPaginados;
import br.com.conteudou.Util.Patch;
import br.com.conteudou.Util.Retorno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CursoController {

    private final Patch<Curso> patch;

    @Autowired
    public CursoController(CursoService cursoService, LoginService loginService) {
        patch = new Patch<>(cursoService, Curso.class, loginService);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/curso/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DadosPaginados<Curso>> buscaCurso(@RequestHeader(value = "ordem", required = false) String ordem,
                                                            @RequestHeader(value = "tamanho", required = false) Integer tamanho,
                                                            @RequestHeader(value = "paginaAtual", required = false) Integer paginaAtual,
                                                            @PathVariable Long id) {
        return patch.consultar(id, ordem, tamanho, paginaAtual, true);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cursos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DadosPaginados<Curso>> buscaCursos(@RequestHeader(value = "ordem", required = false) String ordem,
                                                             @RequestHeader(value = "tamanho", required = false) Integer tamanho,
                                                             @RequestHeader(value = "paginaAtual", required = false) Integer paginaAtual,
                                                             @RequestHeader(value = "filtros", required = false) String filtros) {
        return patch.consultar(ordem, tamanho, paginaAtual, filtros, true);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/curso", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> salvaCurso(@RequestBody Curso curso) {
        return patch.salva(curso, true);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/curso", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> alteraCurso(@RequestBody Curso curso) {
        return patch.altera(curso, true);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/curso/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> excluiCurso(@PathVariable Long id) {
        return patch.exclui(id, true);
    }
}