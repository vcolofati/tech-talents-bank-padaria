package br.com.talentstechbank.padaria.config;

import br.com.talentstechbank.padaria.models.ItemVenda;
import br.com.talentstechbank.padaria.models.MovimentacaoDeProduto;
import br.com.talentstechbank.padaria.models.Produto;
import br.com.talentstechbank.padaria.models.Venda;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.talentstechbank.padaria.services.ItemVendaService;
import br.com.talentstechbank.padaria.services.MovimentacaoDeProdutoService;
import br.com.talentstechbank.padaria.services.ProdutoService;
import br.com.talentstechbank.padaria.services.VendaService;
import org.springframework.ui.Model;

@Configuration
public class Menu implements CommandLineRunner {
    @Autowired
    ProdutoService produtoService;

    @Autowired
    VendaService vendaService;

    @Autowired
    ItemVendaService itemVendaService;

    @Autowired
    MovimentacaoDeProdutoService movimentacaoDeProdutoService;

    @Override
    public void run(String... args) throws Exception {

        boolean loop = true;
        Scanner in = new Scanner(System.in).useDelimiter("\n");
        String senhaAdmin = "321"; /// passar senha para arquivo env que será acessado para validação de sub menus 8 e 9


        while (loop != false) {

            System.out.println("***Caixa Padaria Pão & cia ***");
            System.out.println("1- Realizar venda"); //todo saida
            System.out.println("2- Conferir preço de produto"); //falta método que volta id_produto ao receber descrição ou código todo id
            System.out.println("3- Conferir quantidade de determinado produto em estoque");//falta método de busca por descrição ou código + metodo que some linhas de entrada e some linhas de saida e as subtraia todo id
            System.out.println("4- Inserir produção"); //todo saida
            System.out.println("8 - Acessar menu de Gerenciamento de Estoque"); //*
            System.out.println("9 - Acessar menu de Controle de caixa"); //*


            System.out.println("____________________________");
            System.out.println();
            System.out.print("Opção: ");
            int opc = in.nextInt();


            switch (opc) {


                case 1: /*Realizar venda*/
                    // Inicializei uma nova venda
                    Venda v = vendaService.inicializarVenda();

                    int finalizar_compra = 0;

                    do {
                        System.out.println("Insira o código de barras ou a descrição do produto: ");
                        String codigo_barras = in.next();

                        System.out.println("Insira a quantidade de produtos: ");
                        int quantidade_de_produto = Integer.parseInt(in.next());

                        // Busquei um produto por código de barras
                        Produto produto = produtoService.listarProdutoPorCodBarra(codigo_barras);

                        // Criei um novo item venda
                        ItemVenda item = new ItemVenda();
                        item.setProduto(produto);
                        item.setQuantidade(BigDecimal.valueOf(quantidade_de_produto));

                        v.adicionarItemVenda(item);


                        System.out.println("Finalizar  compra? S/N ");
                        char escolha = in.next().charAt(0);
                        if (escolha == 's' || escolha == 'S') {
                            finalizar_compra++;
                        }
                    }
                    while (finalizar_compra == 0);


//                        v.concluirVenda(VendaDao);


                    break;

                case 2: /*Conferir preço de produto*/

                    System.out.println("Insira o código de barras ou a descrição do produto para saber seu preço: ");
                    String codigo_barras = in.next();


                    break;

                case 3: /*Conferir quantidade de determinado produto em estoque*/


                    break;

                case 4:

                    System.out.println("*** Inserir produção própria ***");
                    System.out.println("1- Inserir receita avulsa em estoque"); //todo saída falta método que volta id_produto ao receber descrição ou código todo id
                    System.out.println("2- Inserir fornada de pão em estoque");
                    //   System.out.println("3- Inserir bolo em estoque  ");
                    System.out.println("0- Sair");

                    System.out.println("____________________________");
                    System.out.println();
                    System.out.print("Opção: ");
                    int opcEmGerenciamentoDeProducaoPropria = in.nextInt();

                    switch (opcEmGerenciamentoDeProducaoPropria) {
                        case 1:                 /*Inserir receita avulsa em estoque*/

                            /*entrada*/
                            System.out.print("Insira a descrição do novo produto:");
                            String descricao = in.next();

                            System.out.println("Insira o peso unitario do novo produto:");
                            BigDecimal peso_unitario = BigDecimal.valueOf(in.nextDouble());

                            System.out.println("Insira a unidade de medida vendida do novo produto:");
                            BigDecimal unidade_medida_vendida = BigDecimal.valueOf(in.nextDouble());

                            System.out.println("Insira a quantidade do produto:");
                            String quantidade = in.next();

                            System.out.println("Insira o codigo de barras do novo produto:");
                             codigo_barras = in.next();

                            System.out.print("Insira o valor de custo do novo produto:");
                            BigDecimal valor_custo = BigDecimal.valueOf(in.nextDouble());

                            System.out.println("Insira o valor de venda do novo produto:");
                            BigDecimal valor_venda = BigDecimal.valueOf(in.nextDouble());

                            System.out.println("Insira a data de  validade do novo produto:");
                            BigDecimal validade = BigDecimal.valueOf(in.nextDouble());

                            System.out.println("Insira a data de  vencimento do novo produto:");
                            BigDecimal vencimento = BigDecimal.valueOf(in.nextDouble());

                            System.out.println("Insira o lote do novo produto:");
                            BigDecimal lote = BigDecimal.valueOf(in.nextDouble());

                            // Cadastro de novo produto
                            Produto produto = new Produto(descricao,
                                    valor_custo,
                                    peso_unitario,
                                    unidade_medida_vendida,
                                    codigo_barras,
                                    valor_venda
                            );

                            produto = produtoService.cadastrarProduto(produto);


                            movimentacaoDeProdutoService.fabricar(
                                    id_produto,
                                    quantidade,
                                    valor_custo,
                                    validade,
                                    vencimento,
                                    lote);


                            /* 4.1 */
                            break;

                        case 2:             /*Inserir fornada de pão em estoque*/
                            /* 4.2 */
                            break;

//                        case 3:       /*Inserir bolo em estoque*/
//                            /* 4.3 */
//                            break;


                        case 0:
                            break;

                        default:
                            System.out.println("Por favor, digite uma opção válida!\n");
                            break;
                    }


                case 8:
                    System.out.print("Por favor, digite a senha administrativa: ");
                    String senha = in.next();

                    if (senha.equals(senhaAdmin)) {
                        System.out.println("*** Gerenciamento de Estoque***"); /*senha*/
                        System.out.println("1- Inserir produto em estoque"); //falta método que volta id_produto ao receber descrição ou código todo id
                        System.out.println("2- Cadastrar produto inédito em estoque"); //ok
                        System.out.println("3- Relação de todos os produtos ativos em estoque");
                        System.out.println("4- Relação de todos os produtos ativos e inativos em estoque");
                        System.out.println("5- Relação de produtos em estoque por período");
                        System.out.println("6- Alterar status de produto");
//                        System.out.println("7- Modificar preço de venda de produto ");
                        System.out.println("0- Sair");

                        System.out.println("____________________________");
                        System.out.println();
                        System.out.print("Opção: ");
                        int opcEmGerenciamentoDeEstoque = in.nextInt();
                        Produto produto;
                        switch (opcEmGerenciamentoDeEstoque) {

                            case 1: /*Inserir produtos em estoque */

                                System.out.println("Insira o código de barras ou a descrição do produto que deseja inserir: ");
                                String codigo_barras = in.next();

                                System.out.println("Insira a quantidade do produto que deseja inserir: ");
                                String quantidade = in.next();

                                System.out.print("Insira o valor de custo do produto que deseja inserir:");
                                BigDecimal valor_custo = BigDecimal.valueOf(in.nextDouble());

                                System.out.println("Insira a data de  validade do novo produto:");
                                BigDecimal validade = BigDecimal.valueOf(in.nextDouble());

                                System.out.println("Insira a data de  vencimento do novo produto:");
                                BigDecimal vencimento = BigDecimal.valueOf(in.nextDouble());

                                System.out.println("Insira o lote do novo produto:");
                                BigDecimal lote = BigDecimal.valueOf(in.nextDouble());

                                movimentacaoDeProdutoService.compra(
                                        id_produto,
                                        quantidade,
                                        valor_custo,
                                        validade,
                                        vencimento,
                                        lote);



                                MovimentacaoDeProduto movimentacaoDeProduto = new MovimentacaoDeProduto(produto, BigDecimal.valueOf(5),
                                        LocalDateTime.now(), produto.getValorDeCusto(), produto.getValorVenda(), "Compra",
                                        LocalDateTime.parse("2020-07-25 14:55:58"), LocalDateTime.parse("2020-07-26 14:55:58"), "Ambev", "mlfdmk55");
                                movimentacaoDeProdutoService.compra(movimentacaoDeProduto);


                                System.out.println(movimentacaoDeProdutoService.listarCompras());
                                break;
                            case 2: /*Cadastrar produto inédito em estoque */

                                System.out.print("Insira a descrição do novo produto:");
                                String descricao = in.next();

                                System.out.println("Insira o peso unitario do novo produto:");
                                BigDecimal peso_unitario = BigDecimal.valueOf(in.nextDouble());

                                System.out.println("Insira a unidade medida peso do novo produto:");
                                String unidade_medida_peso = in.next();

                                System.out.println("Insira o codigo de barras do novo produto:");
                                String codigo_barras = in.next();

                                System.out.print("Insira o valor de custo do novo produto:");
                                BigDecimal valor_custo = BigDecimal.valueOf(in.nextDouble());

                                System.out.println("Insira o valor de venda do novo produto:");
                                BigDecimal valor_venda = BigDecimal.valueOf(in.nextDouble());

                                // Cadastrei um novo produto
                                produto = new Produto(descricao,
                                        valor_custo,
                                        peso_unitario,
                                        unidade_medida_peso,
                                        codigo_barras,
                                        valor_venda
                                );

                                produtoService.cadastrarProduto(produto);

//									p.cadastrarNovoProduto(ProdutoDao);
                                break;
                            case 3: /*Cadastrar produto inédito em estoque*/
                                break;

                            case 4: /*Relação de todos os produtos ativos e inativos em estoque*/
                                break;

                            case 5: /*Relação de produtos em estoque por período */
                                break;

                            case 6: /*Alterar status de produto. */
                                break;

//                                case 7: /*Modificar preço de venda de produto */
//                                break;


                            case 0:
                                break;
                            default:
                                System.out.println("Por favor, digite uma opção válida!\n");
                                break;
                        }

                    } else {
                        System.out.println("Senha administrativa incorreta");
                    }
                    break;
                case 9:
                    System.out.print("Por favor, digite a senha administrativa: ");
                    senha = in.next();

                    if (senha.equals(senhaAdmin)) {

                        System.out.println("*** Controle de caixa***"); /*senha*/
                        System.out.println("1- Checar vendas por período ");
//                        System.out.println("2- Entradas de caixa do dia"); /*preços total de vendas*/
//                        System.out.println("3- Lucro bruto do dia"); /*preços total de vendas - preço total de custo*/
                        System.out.println("0- Sair");

                        System.out.println("____________________________");
                        System.out.println();
                        System.out.print("Opção: ");
                        int opcEmControleDeCaixa = in.nextInt();

                        switch (opcEmControleDeCaixa) {
                            case 1: /*Checar vendas por período*/
                                break;

//                            case 2: /*Entradas de caixa do dia*/
//                                break;
//
//                            case 3: /*Lucro bruto do dia*/
//                                break;

                            case 0:
                                break;

                            default:
                                System.out.println("Por favor, digite uma opção válida!\n");
                                break;
                        }

                    } else {

                        System.out.println("Senha administrativa incorreta");
                    }
                    break;

                case 0:
                    loop = false;
                    break;
                default:
                    System.out.println("Por favor, digite uma opção válida!\n");
                    break;
            }
        }
        in.close();
    }
}

