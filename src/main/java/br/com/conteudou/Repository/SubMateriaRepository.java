package br.com.conteudou.Repository;

import br.com.conteudou.Model.SubMateria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubMateriaRepository extends JpaRepository<SubMateria, Long> {

}

