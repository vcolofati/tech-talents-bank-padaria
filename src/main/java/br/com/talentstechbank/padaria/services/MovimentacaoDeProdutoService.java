package br.com.talentstechbank.padaria.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.talentstechbank.padaria.models.MovimentacaoDeProduto;
import br.com.talentstechbank.padaria.models.Produto;
import br.com.talentstechbank.padaria.repositories.MovimentacaoDeProdutoRepository;

@Service
public class MovimentacaoDeProdutoService {
	
	@Autowired
	MovimentacaoDeProdutoRepository movimentacaoDeProdutoRepository;
	
	public MovimentacaoDeProduto compra(MovimentacaoDeProduto obj) {
		return movimentacaoDeProdutoRepository.save(obj);
	}
	
	public MovimentacaoDeProduto fabricar(Produto produto, BigDecimal quantidade, BigDecimal valorCusto, BigDecimal valorVenda,  LocalDateTime dataMovimentacao, LocalDateTime vencimento, String lote) {
		MovimentacaoDeProduto movimentacaoDeProduto = new MovimentacaoDeProduto(produto, quantidade, LocalDateTime.now(), valorCusto,valorVenda, "fabricado", LocalDateTime.now(), vencimento, "Padaria Pão & Cia", lote);
		return movimentacaoDeProdutoRepository.save(movimentacaoDeProduto);
	}
	
	public List<MovimentacaoDeProduto> listarCompras() {
		return movimentacaoDeProdutoRepository.findAll();
	}
	
	public MovimentacaoDeProduto listarCompra(Long id) {
		return movimentacaoDeProdutoRepository.findById(id).get();
	}
}
