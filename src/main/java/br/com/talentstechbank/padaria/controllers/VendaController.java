package br.com.talentstechbank.padaria.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.talentstechbank.padaria.models.Venda;
import br.com.talentstechbank.padaria.services.VendaService;

@RestController
@RequestMapping("/iniciarVenda")
public class VendaController {
	
	@Autowired
	VendaService service;
	
	@PostMapping 
	public Venda inicializarVenda() { 
		return service.inicializarVenda();
	}
	
	@GetMapping
	public ResponseEntity<List<Venda>> listarVendas() {
		List<Venda> list = service.listarVendas();
		return ResponseEntity.ok().body(list);
	}
}
