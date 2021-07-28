package br.com.talentstechbank.padaria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.talentstechbank.padaria.models.MovimentacaoDeProduto;

@Repository
public interface MovimentacaoDeProdutoRepository extends JpaRepository<MovimentacaoDeProduto, Long> {
}
