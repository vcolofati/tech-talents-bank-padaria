package br.com.talentstechbank.padaria.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.talentstechbank.padaria.models.ItemVenda;
import br.com.talentstechbank.padaria.repositories.ItemVendaRepository;

@Service
public class ItemVendaService {
	
	ItemVendaRepository itemVendaRepository;
	
	public List<ItemVenda> listarItemVenda() {
		return itemVendaRepository.findAll();
	}
	
	public ItemVenda adicionarItemCarrinho(ItemVenda itemVenda) {
		return itemVendaRepository.save(itemVenda);
	}
}
