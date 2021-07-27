package br.com.talentstechbank.padaria.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.talentstechbank.padaria.models.ItemVenda;
import br.com.talentstechbank.padaria.services.ItemVendaService;

@RestController
@RequestMapping("/addItemVenda")
public class ItemVendaController {
	
	@Autowired
	ItemVendaService service;
	
	@GetMapping
	public ResponseEntity<List<ItemVenda>> listarTodosOsProdutos() {
        List<ItemVenda> list = service.listarItemVenda();
        return ResponseEntity.ok().body(list);
    }
	
	@PostMapping
    public ItemVenda addItemCarrinho(@RequestBody ItemVenda itemvenda) {
        return service.adicionarItemCarrinho(itemvenda);
    }
}	