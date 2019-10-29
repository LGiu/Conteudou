package br.com.conteudou.Util;

import br.com.conteudou.Interface.Model;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Patch<U extends Model> {

    private final ServiceGenerico<U> serviceGenerico;
    private final Class aClass;

    public Patch(ServiceGenerico<U> serviceGenerico, Class aClass) {
        this.serviceGenerico = serviceGenerico;
        this.aClass = aClass;
    }

    public ResponseEntity<DadosPaginados<U>> consultar(String ordem, Integer tamanho, Integer paginaAtual) {
        return new ResponseEntity<>(converteDadosPaginados(serviceGenerico.buscaLista(ordem, tamanho, paginaAtual), tamanho, paginaAtual), HttpStatus.OK);
    }

    public ResponseEntity<DadosPaginados<U>> consultar(Long id, String ordem, Integer tamanho, Integer paginaAtual) {
        return new ResponseEntity<>(converteDadosPaginados(serviceGenerico.buscaPorId(id, ordem, tamanho, paginaAtual), tamanho, paginaAtual), HttpStatus.OK);
    }

    public ResponseEntity<Retorno> salva(U u) {
        return new ResponseEntity<>(serviceGenerico.salva(u), HttpStatus.OK);
    }

    public ResponseEntity<Retorno> altera(U u) {
        u = aplicaAlteracoes(u, serviceGenerico.buscaPorId(u.getId()));
        return new ResponseEntity<>(serviceGenerico.salva(u), HttpStatus.OK);
    }

    public ResponseEntity<Retorno> exclui(Long id) {
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

    private DadosPaginados<U> converteDadosPaginados(U u, Integer tamanho, Integer paginaAtual) {
        return converteDadosPaginados((u == null ? new ArrayList<>() : Collections.singletonList(u)), tamanho, paginaAtual);
    }

    private DadosPaginados<U> converteDadosPaginados(List<U> list, Integer tamanho, Integer paginaAtual) {
        DadosPaginados<U> dadosPaginados = new DadosPaginados();
        dadosPaginados.setNumeroPagina((paginaAtual == null ? 0 : paginaAtual));
        dadosPaginados.setQuantidadeRegistros(list.size());
        dadosPaginados.setQuantidadePaginas(list.size() / (tamanho == null ? 10 : tamanho));
        dadosPaginados.setTamanhoPagina((tamanho == null ? 10 : tamanho));
        dadosPaginados.setConteudo(list);
        return dadosPaginados;
    }

}
