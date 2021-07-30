package br.com.talentstechbank.padaria.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.talentstechbank.padaria.models.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {
	
	@Query(value = "SELECT * from vendas WHERE data_hora BETWEEN ?1 and ?2", nativeQuery = true)
	List<Venda> listarVendasPeriodo(LocalDate inicio, LocalDate fim);
}
