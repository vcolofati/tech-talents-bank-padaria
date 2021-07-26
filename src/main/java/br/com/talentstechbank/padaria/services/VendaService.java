package br.com.talentstechbank.padaria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.talentstechbank.padaria.models.Venda;
import br.com.talentstechbank.padaria.repositories.VendaRepository;

@Service
public class VendaService {
	
	@Autowired
	VendaRepository repository;
	
	public Venda inicializarVenda() {
		return repository.save(new Venda());
	}

	public List<Venda> listarVendas() {
		return repository.findAll();
		
	}
}
