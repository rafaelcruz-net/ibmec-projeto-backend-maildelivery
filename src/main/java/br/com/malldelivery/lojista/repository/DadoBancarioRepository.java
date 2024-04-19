package br.com.malldelivery.lojista.repository;


import br.com.malldelivery.lojista.model.DadoBancario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadoBancarioRepository extends JpaRepository<DadoBancario, Integer> {
}
