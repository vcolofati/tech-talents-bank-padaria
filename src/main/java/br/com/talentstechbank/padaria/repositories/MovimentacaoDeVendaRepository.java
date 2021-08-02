package br.com.talentstechbank.padaria.repositories;
import br.com.talentstechbank.padaria.models.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.talentstechbank.padaria.models.MovimentacaoDeProduto;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface MovimentacaoDeVendaRepository extends JpaRepository<MovimentacaoDeProduto, Long> {

    @Query(value = "SELECT SUM(quantidade) FROM tb_movimentacao_de_produto a WHERE a.id_produto = :id AND (a.especie_da_movimentacao = 'comprado' OR a.especie_da_movimentacao = 'fabricado');", nativeQuery = true)
         BigDecimal qtdeEntradaEmEstoque(Long id);

    @Query(value = "SELECT SUM(quantidade) FROM tb_movimentacao_de_produto a where a.id_produto = :id AND (a.especie_da_movimentacao = 'vendido' OR a.especie_da_movimentacao = 'consumido_materia_prima');", nativeQuery = true)
         BigDecimal qtdeSaidaDoEstoque(Long id);
}
