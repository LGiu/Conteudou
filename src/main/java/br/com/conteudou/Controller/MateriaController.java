package br.com.conteudou.Controller;

import br.com.conteudou.Model.Materia;
import br.com.conteudou.Service.MateriaService;
import br.com.conteudou.Util.DadosPaginados;
import br.com.conteudou.Util.Patch;
import br.com.conteudou.Util.Retorno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MateriaController {

    private final Patch<Materia> patch;

    @Autowired
    public MateriaController(MateriaService materiaService) {
        patch = new Patch<>(materiaService, Materia.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/materia/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DadosPaginados<Materia>> buscaMateria(@RequestHeader(value = "ordem", required = false) String ordem,
                                                            @RequestHeader(value = "tamanho", required = false) Integer tamanho,
                                                            @RequestHeader(value = "paginaAtual", required = false) Integer paginaAtual,
                                                            @PathVariable Long id) {
        return patch.consultar(id, ordem, tamanho, paginaAtual);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/materias", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DadosPaginados<Materia>> buscaMaterias(@RequestHeader(value = "ordem", required = false) String ordem,
                                                           @RequestHeader(value = "tamanho", required = false) Integer tamanho,
                                                           @RequestHeader(value = "paginaAtual", required = false) Integer paginaAtual) {
        return patch.consultar(ordem, tamanho, paginaAtual);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/materia", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> salvaMateria(@RequestBody Materia materia) {
        return patch.salva(materia);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/materia", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> alteraMateria(@RequestBody Materia materia) {
        return patch.altera(materia);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/materia/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> excluiMateria(@PathVariable Long id) {
        return patch.exclui(id);
    }
}