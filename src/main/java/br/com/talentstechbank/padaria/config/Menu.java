package br.com.talentstechbank.padaria.config;

import java.math.BigDecimal;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.talentstechbank.padaria.models.Produto;
import br.com.talentstechbank.padaria.services.ProdutoService;

@Configuration
public class Menu implements CommandLineRunner {
	
	@Autowired
	ProdutoService produtoService;

	@Override
	public void run(String... args) throws Exception {
			
		// Como fazer o menu no spring
		System.out.println();
		System.out.println("----- Padaria ------");
		Scanner in = new Scanner(System.in);
		System.out.print("Digite a descricao do produto: ");
		String descricao = in.next();
		Produto produto = new Produto(descricao, BigDecimal.valueOf(2.00), BigDecimal.valueOf(0.5), "gr.", "2366565558", BigDecimal.valueOf(3.00));
		produtoService.cadastrarProduto(produto);
		System.out.println(produtoService.listarProdutos());
		in.close();
	}
	
}
