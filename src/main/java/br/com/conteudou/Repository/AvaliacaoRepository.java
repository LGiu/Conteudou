package br.com.conteudou.Repository;

import br.com.conteudou.Model.Avaliacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoRepository  extends JpaRepository<Avaliacao, Long> {

    Page<Avaliacao> findById(Long id, Pageable pageable);
}

