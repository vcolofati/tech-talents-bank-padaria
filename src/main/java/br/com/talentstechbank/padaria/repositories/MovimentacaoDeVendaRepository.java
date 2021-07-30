package br.com.talentstechbank.padaria.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.talentstechbank.padaria.models.MovimentacaoDeProduto;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface MovimentacaoDeVendaRepository extends JpaRepository<MovimentacaoDeProduto, Long> {

    @Query(value = "SELECT SUM(quantidade) FROM tb_movimentacao_de_produto a JOIN tb_produto b ON a.id_produto = b.id WHERE ((b.descricao ilike %?1% OR b.codigo_barras = ?1) AND b.status = true) AND (a.especie_da_movimentacao = 'comprado' OR a.especie_da_movimentacao = 'fabricado')", nativeQuery = true)
         BigDecimal qtdeEntradaEmEstoque(String descOuCod);

    @Query(value = "SELECT SUM(quantidade) FROM tb_movimentacao_de_produto a JOIN tb_produto b ON a.id_produto = b.id WHERE ((b.descricao ilike %?1% OR b.codigo_barras = ?1) AND b.status = true) AND (a.especie_da_movimentacao = 'vendido' OR a.especie_da_movimentacao = 'consumido_materia_prima')", nativeQuery = true)
         BigDecimal qtdeSaidaDoEstoque(String descOuCod);

}
