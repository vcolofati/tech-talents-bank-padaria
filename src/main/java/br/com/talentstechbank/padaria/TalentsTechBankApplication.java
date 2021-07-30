package br.com.talentstechbank.padaria;

import br.com.talentstechbank.padaria.models.ItemVenda;
import br.com.talentstechbank.padaria.models.MovimentacaoDeProduto;
import br.com.talentstechbank.padaria.models.Produto;
import br.com.talentstechbank.padaria.models.Venda;

import br.com.talentstechbank.padaria.repositories.ItemVendaRepository;
import br.com.talentstechbank.padaria.repositories.MovimentacaoDeVendaRepository;
import br.com.talentstechbank.padaria.repositories.ProdutoRepository;
import br.com.talentstechbank.padaria.repositories.VendaRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.math.BigDecimal.valueOf;

@SpringBootApplication
public class TalentsTechBankApplication implements CommandLineRunner {

    @Autowired
    ItemVendaRepository itemVendaRepository;

    @Autowired
    MovimentacaoDeVendaRepository movimentacaoDeVendaRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    VendaRepository vendaRepository;

    String codigo_barras;
    BigDecimal valor_custo;
    BigDecimal total = new BigDecimal(0);
    String codOuDesc;
    BigDecimal quantidade;
    String senhaAdmin = "321";
    String senha;

    Scanner in = new Scanner(System.in).useDelimiter("\n");

    public static void main(String[] args) {
        SpringApplication.run(TalentsTechBankApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        menuPrincipal();
        in.close();
    }

    //Menus
    public void menuPrincipal() {
        boolean loop = true;
        while (loop) {

            System.out.println("\n***Caixa Padaria Pão & cia ***");
            System.out.println("1- Realizar venda"); // todo saida
            System.out.println("2- Conferir preço de produto"); // falta método que volta id_produto ao receber descrição ou código todo id
            System.out.println("3- Conferir quantidade de determinado produto em estoque");// falta método de busca pordescrição ou código + metodo que some linhas de entrada e some linhas desaida e as subtraia todo id
            System.out.println("4- Inserir produção"); // todo saida
            System.out.println("8 - Acessar menu de Gerenciamento de Estoque"); // *
            System.out.println("9 - Acessar menu de Controle de caixa"); // *

            System.out.println("____________________________\n");
            System.out.print("Opção: ");
            int opc = in.nextInt();

            switch (opc) {
                case 1: /* Realizar venda */
                    realizarVenda();
                    break;
                case 2: /* Conferir preço de produto */
                    conferirPrecoProd();
                    break;
                case 3: /* Conferir quantidade de determinado produto em estoque */
                    conferirQuantEmEstoque();
                    break;
                case 4:
                    menuProdProp();
                    break;
                case 8:
                    menuAdministrativo8();
                    break;
                case 9:
                    menuAdmininstrativo9();
                    break;
                case 0:
                    loop = false;
                    break;
                default:
                    System.out.println("Por favor, digite uma opção válida!\n");
                    break;
            }
        }
    }

    public void menuProdProp() {
        System.out.println("*** Inserir produção própria ***");
        System.out.println("1- Inserir receita avulsa em estoque"); // todo saída falta método que volta id_produto ao receber descrição ou código todo id
        System.out.println("2- Inserir fornada de pão em estoque");
        // System.out.println("3- Inserir bolo em estoque ");
        System.out.println("0- Sair");

        System.out.println("____________________________");
        System.out.println();
        System.out.print("Opção: ");
        int opc = in.nextInt();

        switch (opc) {
            case 1: /* Inserir receita avulsa em estoque */
                inserirReceitaAvulsa();
                /* 4.1 */
                break;
            case 2: /* Inserir fornada de pão em estoque */
                /* 4.2 */
                break;
            case 3:/*Inserir bolo em estoque*/
//              /* 4.3 *
                break;
            case 0:
                break;
            default:
                System.out.println("Por favor, digite uma opção válida!\n");
                break;
        }
    }

    public void menuAdministrativo8() {
        System.out.print("Por favor, digite a senha administrativa: ");
        senha = in.next();

        if (senha.equals(senhaAdmin)) {
            System.out.println("*** Gerenciamento de Estoque***"); /* senha */
            System.out.println("1- Inserir produto em estoque"); // falta método que volta id_produto ao receber descrição ou código todo id
            System.out.println("2- Cadastrar produto inédito em estoque"); // ok
            System.out.println("3- Relação de todos os produtos ativos em estoque");
            System.out.println("4- Relação de todos os produtos ativos e inativos em estoque");
            System.out.println("5- Relação de produtos em estoque por período");
            System.out.println("6- Alterar status de produto");
//          System.out.println("7- Modificar preço de venda de produto ");
            System.out.println("0- Sair");

            System.out.println("____________________________");
            System.out.println();
            System.out.print("Opção: ");
            int opc = in.nextInt();

            switch (opc) {
                // comprar - movimentar produto em estoque
                case 1: /* Inserir produtos em estoque */
                    inserirProdutoNoEstoque();
                    break;
                case 2:/* Cadastrar produto inédito em estoque */
                    cadastrarProdutoIneditoEstoque();
                    break;
                case 3: /* Relação de todos os produtos ativos em estoque */
                    break;
                case 4: /* Relação de todos os produtos ativos e inativos em estoque */
                    break;
                case 5: /* Relação de produtos em estoque por período */
                    break;
                case 6: /* Alterar status de produto. */
                    break;
                case 7: /*Modificar preço de venda de produto */
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Por favor, digite uma opção válida!\n");
                    break;
            }
        } else {
            System.out.println("Senha administrativa incorreta");
        }
    }

    public void menuAdmininstrativo9() {
        System.out.print("Por favor, digite a senha administrativa: ");
        senha = in.next();

        if (senha.equals(senhaAdmin)) {

            System.out.println("*** Controle de caixa***"); /* senha */
            System.out.println("1- Checar vendas por período ");
            //System.out.println("2- Entradas de caixa do dia"); /*preços total de vendas*/
            //System.out.println("3- Lucro bruto do dia"); /*preços total de vendas - preço total de custo*/
            System.out.println("0- Sair");

            System.out.println("____________________________");
            System.out.println();
            System.out.print("Opção: ");
            int opcEmControleDeCaixa = in.nextInt();

            switch (opcEmControleDeCaixa) {
                case 1: /* Checar vendas por período */
                    break;
                case 2: /*Entradas de caixa do dia*/
                    break;
                case 3: /*Lucro bruto do dia*/
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Por favor, digite uma opção válida!\n");
                    break;
            }
        } else {
            System.out.println("Senha administrativa incorreta");
        }
    }

    //Funções
    private void conferirQuantEmEstoque() {
    }

    private void conferirPrecoProd() {
        System.out.println("Insira o código de barras ou a descrição do produto para saber seu preço: ");
        codOuDesc = in.next();

        List<Produto> produtos = produtoRepository.listarProdutosPorDescricaoOuCod(codOuDesc);
        Produto produto = produtos.get(0);
        System.out.printf("O preço do %s é %.2f%n", produto.getDescricao(), produto.getValorVenda());
    }

    public void realizarVenda() {
        // Inicializei uma nova venda
        Venda venda = new Venda();
        vendaRepository.save(venda);
        int finalizar_compra = 0;
        List<MovimentacaoDeProduto> mp = new ArrayList<>();;
        do {
            System.out.println("Insira o código de barras ou a descrição do produto: ");
            codOuDesc = in.next();

            System.out.println("Insira a quantidade de produtos: ");
            quantidade = valueOf(in.nextDouble());

            // Busquei um produto por código de barras
            List<Produto> produtos = produtoRepository.listarProdutosPorDescricaoOuCod(codOuDesc);
            Produto produto = produtos.get(0);
            total = total.add(produto.getValorVenda().multiply(quantidade));
            // Criei um novo item venda
            ItemVenda item = new ItemVenda(venda, produto, quantidade);
            mp.add(new MovimentacaoDeProduto(produto, quantidade,
                    LocalDateTime.now(),
                    "Vendido",
                    null, null, null, null));
            item.setValorTotal(total);
            System.out.println("Finalizar compra? S/N");
            char escolha = in.next().charAt(0);
            if (escolha == 's' || escolha == 'S') {
                movimentacaoDeVendaRepository.saveAll(mp);
                itemVendaRepository.save(item);
                finalizar_compra++;
            }
        } while (finalizar_compra == 0);
        venda.setDataHora(LocalDateTime.now());
        venda.setValor(total);
        vendaRepository.save(venda);
    }

    public void inserirReceitaAvulsa() {
        /* entrada */
        System.out.print("Insira a descrição do novo produto:");
        String descricao = in.next();

        System.out.println("Insira o peso unitario do novo produto:");
        BigDecimal peso_unitario = valueOf(in.nextDouble());

        System.out.println("Insira a unidade de medida vendida do novo produto:");
        String unidade_medida_vendida = in.next();

        System.out.println("Insira a quantidade do produto:");
        quantidade = valueOf(in.nextDouble());

        System.out.println("Insira o codigo de barras do novo produto:");
        codigo_barras = in.next();

        System.out.print("Insira o valor de custo do novo produto:");
        valor_custo = valueOf(in.nextDouble());

        System.out.println("Insira o valor de venda do novo produto:");
        BigDecimal valor_venda = valueOf(in.nextDouble());

        System.out.println("Insira a data de  fabricação do novo produto:");
        LocalDateTime fabricacao = LocalDateTime.parse(in.next());

        System.out.println("Insira a data de  validade do novo produto:");
        LocalDateTime validade = LocalDateTime.parse(in.next());

        System.out.println("Insira o lote do novo produto:");
        String lote = in.next();
        // TODO pegar valor de custo
        // Cadastro de novo produto
        // Produto produto = new Produto(descricao, valor_custo, peso_unitario,
        // unidade_medida_vendida, quantidade,
        // codigo_barras, valor_venda);

        // produto = produtoService.cadastrarProduto(produto);

        // movimentacaoDeProdutoService.fabricar(produto, quantidade, validade,
        // fabricacao, lote);
    }

    public void inserirProdutoNoEstoque() {
        System.out.println("Insira o código de barras ou a descrição do produto que deseja inserir: ");
        codOuDesc = in.next();

        System.out.println("Insira a quantidade do produto que deseja inserir: ");
        quantidade = valueOf(in.nextDouble());

        System.out.println("Insira a data de  fabricação do novo produto:");
        LocalDateTime fabricacao = LocalDateTime.parse(in.next());

        System.out.println("Insira a data de  vencimento do novo produto:");
        LocalDateTime validade = LocalDateTime.parse(in.next());

        System.out.println("Insira o nome de fornecedor do novo produto:");
        String fornecedor = in.next();

        System.out.println("Insira o lote do novo produto:");
        String lote = in.next();

        // produtoService.listarProdutosPorDescricaoOuCod(descricaoOuCod);
        // Long id_produto =
        // produtoService.listarProdutosPorDescricaoOuCod(descricaoOuCod).get(0).getId();

        // movimentacaoDeProdutoService.comprar(produto,
        // quantidade,validade,fabricacao,fornecedor, lote);

        // System.out.println(movimentacaoDeProdutoService.listarCompras());
    }

    public void cadastrarProdutoIneditoEstoque() {
        Produto produto;
        System.out.print("Insira a descrição do novo produto:");
        String descricao = in.next();

        System.out.println("Insira o peso unitario do novo produto:");
        BigDecimal peso_unitario = valueOf(in.nextDouble());

        System.out.println("Insira a unidade medida peso do novo produto:");
        String unidade_medida_peso = in.next();

        System.out.println("Insira o codigo de barras do novo produto:");
        codigo_barras = in.next();

        System.out.print("Insira o valor de custo do novo produto:");
        valor_custo = valueOf(in.nextDouble());

        System.out.println("Insira o valor de venda do novo produto:");
        BigDecimal valor_venda = valueOf(in.nextDouble());

        // Cadastrei um novo produto
        produto = new Produto(descricao, valor_custo, peso_unitario, unidade_medida_peso, codigo_barras,
                valor_venda);

        produtoRepository.save(produto);
    }
}

