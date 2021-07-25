package br.com.talentstechbank.padaria.controllers;

import br.com.talentstechbank.padaria.models.Produto;
import br.com.talentstechbank.padaria.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;


    @GetMapping
    public List<Produto> listarTodosOsProdutos() {
        return produtoRepository.findAll();
    }


    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @PutMapping("/{id}")
    public Produto updateProduto(@RequestBody Produto produto, @PathVariable Long id) {
        Produto produtoJaExistente = produtoRepository.getById(id);

        produtoJaExistente.setDescricao(produto.getDescricao());

        return produtoRepository.save(produtoJaExistente);
    }

}
