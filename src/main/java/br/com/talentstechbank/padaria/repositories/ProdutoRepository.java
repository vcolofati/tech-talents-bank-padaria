package br.com.talentstechbank.padaria.repositories;

import br.com.talentstechbank.padaria.models.Produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	@Query(value = "SELECT * FROM tb_produto WHERE status = true", nativeQuery = true)
	List<Produto> listarProdutos();
	
	@Query(value = "SELECT * FROM tb_produto WHERE status = false", nativeQuery = true)
	List<Produto> listarProdutosInativos();
	
	@Query(value = "SELECT * FROM tb_produto WHERE id = ?1 AND status = true", nativeQuery = true)
	Produto listarProduto(Long id);
	
	@Query(value = "SELECT * from tb_produto WHERE descricao ilike %?1% AND status = true", nativeQuery = true)
	List<Produto> listarProdutosPorDescricao(String descricao);
	
	@Query(value = "SELECT * FROM tb_produto WHERE codigo_barras = ?1 AND status = true", nativeQuery = true)
	Produto listarProdutoPorCodBarra(String codigo);
}
