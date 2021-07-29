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

    // Métodos de registro de entrada de produto em estoque

    public MovimentacaoDeProduto compra(
            Produto id_produto,
            BigDecimal quantidade,
            BigDecimal valor_custo,
            LocalDateTime validade,
            LocalDateTime fabricacao,
            String fornecedor,
            String lote) {
        //todo método que receba código ou descrição de um produto  e retorne o id de tb_produtos
        MovimentacaoDeProduto movimentacaoDeProduto = new MovimentacaoDeProduto(
                id_produto,// from tb_produtos
                quantidade,
                LocalDateTime.now(),
                valor_custo,
                id_produto.getValorVenda(),// from tb_produtos
                "comprado",
                validade,
                fabricacao,
                fornecedor,
                lote

        );

        return movimentacaoDeProdutoRepository.save(movimentacaoDeProduto);
    }


    public MovimentacaoDeProduto fabricar(Produto id_produto,
                                          BigDecimal quantidade,
                                          BigDecimal valorCusto,
                                          LocalDateTime validade,
                                          LocalDateTime vencimento,
                                          String lote) {
        //todo método que receba código ou descrição de um produto  e retorne o id de tb_produtos

        MovimentacaoDeProduto movimentacaoDeProduto = new MovimentacaoDeProduto(
                id_produto, //from tb_produtos
                quantidade,
                LocalDateTime.now(),
                valorCusto, //valor de venda ou de custo total dos produtos usados na fabricação??? //todo decidir**********
                id_produto.getValorVenda(), // from tb_produto
                "fabricado",
                validade,
                vencimento,
                "Padaria Pão & Cia",
                lote);
        return movimentacaoDeProdutoRepository.save(movimentacaoDeProduto);
    }


    // Métodos de registro de saída de produto em estoque

    public MovimentacaoDeProduto venda(Produto id_produto, BigDecimal quantidade) {


        //todo método que receba código ou descrição de um produto  e retorne o id de tb_produtos
        MovimentacaoDeProduto movimentacaoDeProduto = new MovimentacaoDeProduto(
                id_produto,// =tb_produtos
                quantidade,
                LocalDateTime.now(),
                id_produto.getValorDeCusto(), // =primeira entrada desse produto que não tenha saído ainda
                id_produto.getValorVenda(),
                "vendido",
                "-",
                "-",
                "-",
                "-"
        );
        return movimentacaoDeProdutoRepository.save(movimentacaoDeProduto);
    }


    public MovimentacaoDeProduto consumido_materia_prima(Produto id_produto, BigDecimal quantidade) {
        //método que receba código ou descrição de um produto  e retorne o id de tb_produtos
        MovimentacaoDeProduto movimentacaoDeProduto = new MovimentacaoDeProduto(
                id_produto,
                quantidade,
                LocalDateTime.now(),
                id_produto.getValorDeCusto(),
                0,
                "consumido_materia_prima",
                "-",
                "-",
                "-",
                "-"
        );
        return movimentacaoDeProdutoRepository.save(movimentacaoDeProduto);
    }



    public List<MovimentacaoDeProduto> listarCompras() {
        return movimentacaoDeProdutoRepository.findAll();
    }

    public MovimentacaoDeProduto listarCompra(Long id) {
        return movimentacaoDeProdutoRepository.findById(id).get();
    }
}
