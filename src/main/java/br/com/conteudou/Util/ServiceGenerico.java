package br.com.conteudou.Util;


import br.com.conteudou.Interface.Model;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.*;

public class ServiceGenerico<U extends Model> {

    private final JpaRepository<U, Long> jpaRepository;
    private final Class aClass;

    @PersistenceContext
    private EntityManager entityManager;

    public ServiceGenerico(JpaRepository<U, Long> jpaRepository, Class aClass) {
        this.jpaRepository = jpaRepository;
        this.aClass = aClass;
    }

    public Retorno validador(U u) {
        if (u == null) {
            return new Retorno("Objeto nulo!");
        }

        if (u.getId() != null && !existe(u)) {
            return new Retorno("O registro não existe!");
        }

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<U>> violations = validator.validate(u);
        List<String> erros = new ArrayList<>();
        if (!violations.isEmpty()) {
            for (ConstraintViolation<U> erro : violations) {
                erros.add(u.getClass().getSimpleName() + "." + erro.getPropertyPath() + ":" + erro.getMessage());
            }
            return new Retorno(erros);
        }

        return new Retorno();
    }

    @Transactional
    public Retorno exclui(Long id) {
        try {
            U u = buscaPorId(id);
            if (u == null) {
                return new Retorno("Id informado não existe!");
            }
            entityManager.remove(u);
            return new Retorno(id);
        } catch (Exception e) {
            return new Retorno<>(e.getMessage());
        }
    }

    public Retorno salva(U u) {
        return salva(u, true);
    }

    public Retorno salva(U u, boolean valida) {
        try {
            if (valida) {
                Retorno retorno = validador(u);
                if (retorno.isErro()) {
                    return retorno;
                }
            }
            if (u.getId() == null) {
                entityManager.persist(u);
            } else {
                entityManager.merge(u);
            }
            return new Retorno(u);
        } catch (Exception e) {
            if (e.getCause().getClass() == org.hibernate.exception.ConstraintViolationException.class) {
                UniqueConstraint[] un = u.getClass().getAnnotation(Table.class).uniqueConstraints();
                if (un.length > 0) {
                    return new Retorno<>("Os atributos " + Arrays.toString(un[0].columnNames()) + " devem ser únicos!");
                } else {
                    return new Retorno<>("Exitem atributos que devem ser únicos!");
                }
            } else {
                return new Retorno<>(e.getMessage());
            }
        }
    }

    public List<U> buscaLista() {
        try {
            return jpaRepository.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    public List<U> buscaLista(String ordem, Integer tamanho, Integer paginaAtual) {
        try {
            return selecao(ordem, tamanho, paginaAtual, null, null);
        } catch (Exception e) {
            return null;
        }
    }

    public U buscaPorId(Long id) {
        try {
            if (id == null) {
                return null;
            }
            return (U) entityManager.find(aClass, id);
        } catch (Exception inored) {
            return null;
        }
    }

    public U buscaPorId(Long id, String ordem, Integer tamanho, Integer paginaAtual) {
        try {
            if (id == null) {
                return null;
            }
            return selecao(ordem, tamanho, paginaAtual, "id", id).get(0);
        } catch (Exception e) {
            return null;
        }
    }


    public boolean existe(U u) {
        try {
            if (u == null || u.getId() == null) {
                return false;
            }

            return jpaRepository.existsById(u.getId());
        } catch (Exception e) {
            return false;
        }
    }

    private List<U> selecao(String ordem, Integer tamanho, Integer paginaAtual) {
        return selecao(ordem, tamanho, paginaAtual, null, null);
    }

    private List<U> selecao(String ordem, Integer tamanho, Integer paginaAtual, String nome, Object valor) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<U> criteriaQuery = criteriaBuilder.createQuery(aClass);
        Root<U> root = criteriaQuery.from(aClass);
        if (ordem == null || ordem.toUpperCase().equals("ASC")) {
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
        }

        tamanho = (tamanho == null ? 10 : tamanho);
        paginaAtual = (paginaAtual == null ? 0 : paginaAtual);
        if (nome == null || valor == null) {
            return entityManager.createQuery(criteriaQuery)
                    .setFirstResult(paginaAtual * tamanho)
                    .setMaxResults(tamanho)
                    .getResultList();
        } else {
            criteriaQuery.where(criteriaBuilder.equal(root.get(nome), valor));
            return entityManager.createQuery(criteriaQuery)
                    .setFirstResult(paginaAtual * tamanho)
                    .setMaxResults(tamanho)
                    .getResultList();
        }
    }


}
