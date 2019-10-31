package br.com.conteudou.Util;

import br.com.conteudou.Enum.Comparador;
import br.com.conteudou.Interface.Model;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
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

    public Patch(ServiceGenerico<U> serviceGenerico, Class aClass) {
        this.serviceGenerico = serviceGenerico;
        this.aClass = aClass;
    }

    public ResponseEntity<DadosPaginados<U>> consultar(String ordem, Integer tamanho, Integer paginaAtual, String filtro) {
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
        return new ResponseEntity<>(converteDadosPaginados(serviceGenerico.buscaPorId(id, ordem, tamanho, paginaAtual), tamanho, paginaAtual), HttpStatus.OK);
    }

    public ResponseEntity<Retorno> salva(U u) {
        u = preInitializy(u);
        return new ResponseEntity<>(serviceGenerico.salva(u), HttpStatus.OK);
    }

    public ResponseEntity<Retorno> altera(U u) {
        u = aplicaAlteracoes(u, serviceGenerico.buscaPorId(u.getId()));
        u = preInitializy(u);
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
                } else if (f.contains(Comparador.CONTEM.getDescricao())) {
                    comparador = Comparador.IGUAL;
                }
                filtroList.add(new Filtro(f.substring(0, f.indexOf(comparador.getDescricao())), comparador, f.substring(f.lastIndexOf(comparador.getDescricao()) + 1)));
            }
        } catch (Exception e) {
            return null;
        }
        return filtroList;
    }

    /*private List<U> trataDados(List<U> list) {
        if (list != null) {
            for (int x = 0; x < list.size(); x++) {
                BeanWrapper b = new BeanWrapperImpl(list.get(x));
                b.setAutoGrowNestedPaths(true);
                for (Field field : aClass.getDeclaredFields()) {
                    if (field.getType() == Date.class && b.getPropertyValue(field.getName()) != null) {
                        b.setPropertyValue(field.getName(), Datas.dataSerializada((Date) b.getPropertyValue(field.getName())));
                    }
                }
                list.set(x, ((U) b));
            }
        }
        return list;
    }*/

}
