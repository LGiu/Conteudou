package br.com.conteudou.Controller;

import br.com.conteudou.Model.SubMateria;
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
    public SubMateriaController(SubMateriaService subMateriaService) {
        patch = new Patch<>(subMateriaService, SubMateria.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sub-materia/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DadosPaginados<SubMateria>> buscaSubMateria(@RequestHeader(value = "ordem", required = false) String ordem,
                                                                      @RequestHeader(value = "tamanho", required = false) Integer tamanho,
                                                                      @RequestHeader(value = "paginaAtual", required = false) Integer paginaAtual,
                                                                      @PathVariable Long id) {
        return patch.consultar(id, ordem, tamanho, paginaAtual);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sub-materias", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DadosPaginados<SubMateria>> buscaSubMaterias(@RequestHeader(value = "ordem", required = false) String ordem,
                                                                       @RequestHeader(value = "tamanho", required = false) Integer tamanho,
                                                                       @RequestHeader(value = "paginaAtual", required = false) Integer paginaAtual,
                                                                       @RequestHeader(value = "filtros", required = false) String filtros) {
        return patch.consultar(ordem, tamanho, paginaAtual, filtros);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/sub-materia", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> salvaSubMateria(@RequestBody SubMateria subMateria) {
        return patch.salva(subMateria);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/sub-materia", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> alteraSubMateria(@RequestBody SubMateria subMateria) {
        return patch.altera(subMateria);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/sub-materia/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> excluiSubMateria(@PathVariable Long id) {
        return patch.exclui(id);
    }
}