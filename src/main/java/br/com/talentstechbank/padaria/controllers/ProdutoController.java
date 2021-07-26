package br.com.talentstechbank.padaria.controllers;

import br.com.talentstechbank.padaria.controllers.util.URL;
import br.com.talentstechbank.padaria.models.Produto;
import br.com.talentstechbank.padaria.services.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping("/{id}")
    public ResponseEntity<Produto> listarProdutoPorId(@PathVariable Long id) {
        Produto obj = service.listarProdutoPorId(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarTodosOsProdutos() {
        List<Produto> list = service.listarProdutos();
        return ResponseEntity.ok().body(list);
    }
    
    @GetMapping("/buscaDescricao")
    public ResponseEntity<List<Produto>> listarPorDescricao(@RequestParam(value = "descricao", 
    	defaultValue = "") String descricao) {
    	URL.decodeParam(descricao);
		List<Produto> list = service.listarProdutosPorDescricao(descricao);
		return ResponseEntity.ok().body(list);
    }
    
    @GetMapping("/buscaCodigo")
    public ResponseEntity<Produto> listarPorCodBarra(@RequestParam(value = "cod", 
    	defaultValue = "") String cod) {
    	URL.decodeParam(cod);
		Produto obj = service.listarProdutoPorCodBarra(cod);
		return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public Produto cadastrarProduto(@RequestBody Produto produto) {
        return service.cadastrarProduto(produto);
    }

    @PutMapping("/{id}")
    public Produto alterarProduto(@RequestBody Produto produto, @PathVariable Long id) {
        return service.alterarProduto(produto, id);
    }
    
    @PutMapping("/{id}/inativarProduto")
    public void inativarProduto(@PathVariable Long id) {
    	service.inativarProduto(id);
    }
    
    @PutMapping("{id}/ativaProduto")
    public void ativarProduto(@PathVariable Long id) {
    	service.ativarProduto(id);
    }

}
