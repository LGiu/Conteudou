package br.com.conteudou.Controller;

import br.com.conteudou.Model.SubMateria;
import br.com.conteudou.Service.LoginService;
import br.com.conteudou.Service.SubMateriaService;
import br.com.conteudou.Util.DadosPaginados;
import br.com.conteudou.Util.Patch;
import br.com.conteudou.Util.Retorno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubMateriaController {

    private final Patch<SubMateria> patch;

    @Autowired
    public SubMateriaController(SubMateriaService subMateriaService, LoginService loginService) {
        patch = new Patch<>(subMateriaService, SubMateria.class, loginService);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sub-materia/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DadosPaginados<SubMateria>> buscaSubMateria(@RequestParam(value = "ordem", required = false) String ordem,
                                                                      @RequestParam(value = "tamanho", required = false) Integer tamanho,
                                                                      @RequestParam(value = "paginaAtual", required = false) Integer paginaAtual,
                                                                      @PathVariable Long id) {
        return patch.consultar(id, ordem, tamanho, paginaAtual, true);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sub-materias", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DadosPaginados<SubMateria>> buscaSubMaterias(@RequestParam(value = "ordem", required = false) String ordem,
                                                                       @RequestParam(value = "tamanho", required = false) Integer tamanho,
                                                                       @RequestParam(value = "paginaAtual", required = false) Integer paginaAtual,
                                                                       @RequestParam(value = "filtros", required = false) String filtros) {
        return patch.consultar(ordem, tamanho, paginaAtual, filtros, true);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/sub-materia", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> salvaSubMateria(@RequestBody SubMateria subMateria) {
        return patch.salva(subMateria, true);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/sub-materia", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> alteraSubMateria(@RequestBody SubMateria subMateria) {
        return patch.altera(subMateria, true);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/sub-materia/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> excluiSubMateria(@PathVariable Long id) {
        return patch.exclui(id, true);
    }
}