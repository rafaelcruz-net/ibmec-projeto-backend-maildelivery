package br.com.malldelivery.lojista.repository;


import br.com.malldelivery.lojista.model.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Integer> {
}
