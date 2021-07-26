package br.com.talentstechbank.padaria.repositories;

import br.com.talentstechbank.padaria.models.Venda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
}
