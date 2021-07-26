package br.com.talentstechbank.padaria.repositories;

import br.com.talentstechbank.padaria.models.Produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
