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
            localDateTime fabricacao,
            String fornecedor,
            String lote) {
        //todo método que receba código ou descrição de um produto  e retorne o id de tb_produtos
        MovimentacaoDeProduto movimentacaoDeProduto = new MovimentacaoDeProduto(
                id_produto,// from tb_produtos
                quantidade,
                LocalDateTime.now(),
                valor_custo,
                valor_venda,// freom tb_produtos
                "comprado",
                validade,
                fabricacao,
                fornecedor,
                lote

        );

        return movimentacaoDeProdutoRepository.save(obj);
    }


    public MovimentacaoDeProduto fabricar(Produto id_produto,
                                          BigDecimal quantidade,
                                          BigDecimal valorCusto,
                                          BigDecimal valorVenda,
                                          LocalDateTime validade,
                                          LocalDateTime vencimento,
                                          String lote) {
        //todo método que receba código ou descrição de um produto  e retorne o id de tb_produtos

        MovimentacaoDeProduto movimentacaoDeProduto = new MovimentacaoDeProduto(
                id_produto, //from tb_produtos
                quantidade,
                LocalDateTime.now(),
                valorCusto, //valor de venda ou de custo total dos produtos usados na fabricação??? //todo decidir**********
                valorVenda, // from tb_produto
                "fabricado",
                validade,
                LocalDateTime.now(),
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
                valorCusto, // =primeira entrada desse produto que não tenha saído ainda
                valorVenda,// =tb_produtos
                "vendido",
                validade,// =primeira entrada desse produto que não tenha saído ainda
                fabricacao,// =primeira entrada desse produto que não tenha saído ainda
                fornecedor,// =primeira entrada desse produto que não tenha saído ainda
                lote // =primeira entrada desse produto que não tenha saído ainda
        );
        return movimentacaoDeProdutoRepository.save(movimentacaoDeProduto);
    }


    public MovimentacaoDeProduto consumido_materia_prima(Produto id_produto, BigDecimal quantidade) {
        //método que receba código ou descrição de um produto  e retorne o id de tb_produtos
        MovimentacaoDeProduto movimentacaoDeProduto = new MovimentacaoDeProduto(
                id_produto,// =tb_produtos
                quantidade,
                LocalDateTime.now(),
                valorCusto, // =primeira entrada desse produto que não tenha saído ainda
                valorVenda,// *********************************************************************
                "consumido_materia_prima",
                validade,// =primeira entrada desse produto que não tenha saído ainda
                fabricacao,// =primeira entrada desse produto que não tenha saído ainda
                fornecedor,// =primeira entrada desse produto que não tenha saído ainda
                lote // =primeira entrada desse produto que não tenha saído ainda
        );
        return movimentacaoDeProdutoRepository.save(movimentacaoDeProduto);
    }

    // primeira entrada desse produto que não tenha saído ainda

   /*** Métodos de saída devem copiar valor_custo e os 4 últimos atributos(validade, fabricacao, fornecedor, lote)
    da primeira entrada desse produto que não saiu ainda

     posição da primeira entrada desse produto que não saiu ainda  =
                        (soma das quantidades de saída(venda/consumido_materia_prima)desse produto +1 )


   No java seria:

    Parar=0 //variável que pausa o loop

    Do{
        If (quantidade de saída+1 < quantidade da i linha de entrada ){
            Copiamos informações da linha i de entrada pra essa saída
            parar=1;}
        Else {
            posição de (quantidade de saída+1) recebe ela mesma - quantidade dessa i linha de entrada;
            i++
        }
        While(parar=0)
                */


    public List<MovimentacaoDeProduto> listarCompras() {
        return movimentacaoDeProdutoRepository.findAll();
    }

    public MovimentacaoDeProduto listarCompra(Long id) {
        return movimentacaoDeProdutoRepository.findById(id).get();
    }
}
