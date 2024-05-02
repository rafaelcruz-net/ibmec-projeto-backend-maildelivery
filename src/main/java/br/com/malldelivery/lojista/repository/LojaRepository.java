package br.com.malldelivery.lojista.repository;


import br.com.malldelivery.lojista.model.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Integer> {
    Optional<Loja> findByCnpj(String cnpj);
}
