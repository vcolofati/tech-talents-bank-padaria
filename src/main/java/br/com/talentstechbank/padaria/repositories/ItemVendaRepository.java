package br.com.talentstechbank.padaria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.talentstechbank.padaria.models.ItemVenda;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {
}
