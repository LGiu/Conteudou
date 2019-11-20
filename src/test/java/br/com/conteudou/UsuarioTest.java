package br.com.conteudou;

import br.com.conteudou.Enum.Comparador;
import br.com.conteudou.Model.Usuario;
import br.com.conteudou.Service.UsuarioService;
import br.com.conteudou.Util.Filtro;
import br.com.conteudou.Util.Retorno;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioTest {

    @Autowired
    private UsuarioService usuarioService;

    @Test
    public void salva() {
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@teste.com");
        usuario.setSenha("teste");
        usuario.setNome("Teste");
        Retorno retorno = usuarioService.salva(usuario, false);
        assertNotNull(retorno.getIdGravado());
    }

    @Test
    public void busca() {
        List<Usuario> usuarioList = usuarioService.buscaLista();
        assertThat(usuarioList.size()).isGreaterThan(1);
    }

    @Test
    public void excluir() {
        Filtro filtro = new Filtro("email", Comparador.IGUAL, "teste@teste.com");
        Retorno retorno = usuarioService.exclui(usuarioService.selecao(Collections.singletonList(filtro)).get(0));
        assertNotNull(retorno.getIdExcluido());
    }

    @Test
    public void altera() {
        Filtro filtro = new Filtro("email", Comparador.IGUAL, "teste@teste.com");
        Usuario usuario = usuarioService.selecao(Collections.singletonList(filtro)).get(0);
        usuario.setNome("Teste Alterado");
        Retorno retorno = usuarioService.salva(usuario, false);
        assertNotNull(retorno.getIdGravado());
    }
}