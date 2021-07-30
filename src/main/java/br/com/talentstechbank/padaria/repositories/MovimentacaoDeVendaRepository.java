package br.com.talentstechbank.padaria.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.talentstechbank.padaria.models.MovimentacaoDeProduto;

public interface MovimentacaoDeVendaRepository extends JpaRepository<MovimentacaoDeProduto, Long> {
}
