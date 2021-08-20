package br.com.talentstechbank.padaria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.talentstechbank.padaria.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	List<Produto> findProdutosByAtivoIsTrue();

	List<Produto> findProdutosByAtivoIsFalse();

	@Query(value = "SELECT * from tb_produto WHERE descricao ilike upper(concat('%', :descricaoOuCod, '%')) AND ativo = true", nativeQuery = true)
	List<Produto> findProdutosByDescricaoAndAtivoIsTrue(String descricao);

	Produto findProdutoByIdAndAtivoIsTrue(Long id);

	Produto findProdutoByCodigoDeBarrasAndAtivoIsTrue(String codigo);
}
