package br.com.talentstechbank.padaria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.talentstechbank.padaria.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	@Query(value = "SELECT * FROM tb_produto WHERE status = true", nativeQuery = true)
	List<Produto> listarProdutosAtivos();

	@Query(value = "SELECT * FROM tb_produto WHERE status = false", nativeQuery = true)
	List<Produto> listarProdutosInativos();
	
	@Query(value = "SELECT * FROM tb_produto", nativeQuery = true)
	List<Produto> listarTodosProdutos();

	@Query(value = "SELECT * from tb_produto WHERE (descricao ilike upper(concat('%', :descricaoOuCod, '%')) OR codigo_barras = :descricaoOuCod) AND status = true", nativeQuery = true)
	List<Produto> listarProdutosPorDescricaoOuCod(String descricaoOuCod);
}
