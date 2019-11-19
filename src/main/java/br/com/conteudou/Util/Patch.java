package br.com.conteudou.Util;

import br.com.conteudou.Enum.Comparador;
import br.com.conteudou.Interface.Model;
import br.com.conteudou.Service.LoginService;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Patch<U extends Model> {

    private final ServiceGenerico<U> serviceGenerico;
    private final Class aClass;
    private final LoginService loginService;

    @Autowired
    public Patch(ServiceGenerico<U> serviceGenerico, Class aClass, LoginService loginService) {
        this.serviceGenerico = serviceGenerico;
        this.aClass = aClass;
        this.loginService = loginService;
    }

    public ResponseEntity<DadosPaginados<U>> consultar(String ordem, Integer tamanho, Integer paginaAtual, String filtro) {
        return consultar(ordem, tamanho, paginaAtual, filtro, false);
    }

    public ResponseEntity<DadosPaginados<U>> consultar(String ordem, Integer tamanho, Integer paginaAtual, String filtro, boolean validaPermissao) {
        validaPermissao(validaPermissao);
        List<Filtro> filtros = new ArrayList<>();
        if (filtro != null && !filtro.equals("")) {
            filtros = getFiltros(filtro);
            if (filtros == null) {
                throw new ApiError("Erro de filtro!");
            }
        }
        return new ResponseEntity<>(converteDadosPaginados(serviceGenerico.buscaLista(ordem, tamanho, paginaAtual, filtros), tamanho, paginaAtual), HttpStatus.OK);
    }

    public ResponseEntity<DadosPaginados<U>> consultar(Long id, String ordem, Integer tamanho, Integer paginaAtual) {
        return consultar(id, ordem, tamanho, paginaAtual, false);
    }

    public ResponseEntity<DadosPaginados<U>> consultar(Long id, String ordem, Integer tamanho, Integer paginaAtual, boolean validaPermissao) {
        validaPermissao(validaPermissao);
        return new ResponseEntity<>(converteDadosPaginados(serviceGenerico.buscaPorId(id, ordem, tamanho, paginaAtual), tamanho, paginaAtual), HttpStatus.OK);
    }

    public ResponseEntity<Retorno> salva(U u) {
        return salva(u, false);
    }

    public ResponseEntity<Retorno> salva(U u, boolean validaPermissao) {
        validaPermissao(validaPermissao);
        u = preInitializy(u);
        return new ResponseEntity<>(serviceGenerico.salva(u), HttpStatus.OK);
    }

    public ResponseEntity<Retorno> altera(U u) {
        return altera(u, false);
    }

    public ResponseEntity<Retorno> altera(U u, boolean validaPermissao) {
        validaPermissao(validaPermissao);
        u = aplicaAlteracoes(u, serviceGenerico.buscaPorId(u.getId()));
        if (u == null) {
            return new ResponseEntity<>(new Retorno("Id não foi informado ou não existe"), HttpStatus.OK);
        }
        u = preInitializy(u);
        return new ResponseEntity<>(serviceGenerico.salva(u), HttpStatus.OK);
    }

    public ResponseEntity<Retorno> exclui(Long id) {
        return exclui(id, false);
    }

    public ResponseEntity<Retorno> exclui(Long id, boolean validaPermissao) {
        validaPermissao(validaPermissao);
        return new ResponseEntity<>(serviceGenerico.exclui(id), HttpStatus.OK);
    }

    private U aplicaAlteracoes(U u, U uAnt) {
        if (u != null && uAnt != null) {
            BeanWrapper beanWrapperAnt = new BeanWrapperImpl(uAnt);
            BeanWrapper beanWrapper = new BeanWrapperImpl(u);
            for (PropertyDescriptor propertyDescriptor : beanWrapper.getPropertyDescriptors()) {
                try {
                    if (!propertyDescriptor.getName().equals("id")) {
                        beanWrapperAnt.setPropertyValue(propertyDescriptor.getName(), beanWrapper.getPropertyValue(propertyDescriptor.getName()));
                    }
                } catch (Exception ignored) {
                }
            }
            return (U) beanWrapperAnt.getWrappedInstance();
        }
        return null;
    }

    public DadosPaginados<U> converteDadosPaginados(U u, Integer tamanho, Integer paginaAtual) {
        return converteDadosPaginados((u == null ? new ArrayList<>() : Collections.singletonList(u)), tamanho, paginaAtual);
    }

    public DadosPaginados<U> converteDadosPaginados(List<U> list, Integer tamanho, Integer paginaAtual) {
        DadosPaginados<U> dadosPaginados = new DadosPaginados();
        dadosPaginados.setNumeroPagina((paginaAtual == null ? 0 : paginaAtual));
        dadosPaginados.setQuantidadeRegistros(list.size());
        dadosPaginados.setQuantidadePaginas(list.size() / (tamanho == null ? 10 : tamanho));
        dadosPaginados.setTamanhoPagina((tamanho == null ? 10 : tamanho));
        dadosPaginados.setConteudo(list);
        return dadosPaginados;
    }

    public U preInitializy(U u) {
        if (u.getId() == null) {
            u.setDataCriacao(new Date());
        }
        u.setDataAlteracao(new Date());
        u.preInitializy();
        return u;
    }

    private List<Filtro> getFiltros(String filtro) {
        List<Filtro> filtroList = new ArrayList<>();
        try {
            String[] filtros = filtro.split("&");
            for (String f : filtros) {
                Comparador comparador = null;
                if (f.contains(Comparador.IGUAL.getDescricao())) {
                    comparador = Comparador.IGUAL;
                } else if (f.contains(Comparador.DIFERENTE.getDescricao())) {
                    comparador = Comparador.DIFERENTE;
                } else if (f.toUpperCase().contains(Comparador.CONTEM.getDescricao())) {
                    comparador = Comparador.CONTEM;
                }
                if (f.substring(0, f.toUpperCase().indexOf(comparador.getDescricao())).contains(".")) {
                    filtroList.add(new Filtro(f.substring(f.indexOf(".") + (comparador.getDescricao().length()), f.toUpperCase().indexOf(comparador.getDescricao())).trim(), comparador, f.substring(f.toUpperCase().lastIndexOf(comparador.getDescricao()) + 1).trim().trim(), f.substring(0, f.indexOf("."))));
                } else {
                    filtroList.add(new Filtro(f.substring(0, f.toUpperCase().indexOf(comparador.getDescricao())).trim(), comparador, f.substring(f.toUpperCase().lastIndexOf(comparador.getDescricao()) + (comparador.getDescricao().length())).trim()));
                }
            }
        } catch (Exception e) {
            return null;
        }
        return filtroList;
    }

    private void validaPermissao(boolean valida) {
        if (valida && !loginService.getUsuarioAtual().getFlagAdministrador()) {
            throw new ApiError("Você não possuí permissão para a ação!");
        }
    }

}
