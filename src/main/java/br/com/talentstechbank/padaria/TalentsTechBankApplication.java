package br.com.talentstechbank.padaria;

import br.com.talentstechbank.padaria.exceptions.EntradaInvalidaException;
import br.com.talentstechbank.padaria.models.ItemVenda;
import br.com.talentstechbank.padaria.models.MovimentacaoDeProduto;
import br.com.talentstechbank.padaria.models.Produto;
import br.com.talentstechbank.padaria.models.Venda;

import br.com.talentstechbank.padaria.repositories.ItemVendaRepository;
import br.com.talentstechbank.padaria.repositories.MovimentacaoDeVendaRepository;
import br.com.talentstechbank.padaria.repositories.ProdutoRepository;
import br.com.talentstechbank.padaria.repositories.VendaRepository;

import br.com.talentstechbank.padaria.utils.ScannerUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String senhaAdmin = System.getenv("SENHA_ADMIN");
    String senha;

    Scanner in = new Scanner(System.in).useDelimiter("\n");

    public static void main(String[] args) {
            SpringApplication.run(TalentsTechBankApplication.class, args);
    }

    @Override
    public void run(String... args) {
        menuPrincipal();
        in.close();
    }

    //Menus
    public void menuPrincipal() {
        boolean loop = true;
        while (loop) {

            System.out.println("\n***Caixa Padaria Pão & cia ***");
            System.out.println("1- Realizar venda");
            System.out.println("2- Conferir preço de produto");
            System.out.println("3- Conferir quantidade de determinado produto em estoque");
            System.out.println("4- Inserir produção");
            System.out.println("8 - Acessar menu de Gerenciamento de Estoque"); // *
            //System.out.println("9 - Acessar menu de Controle de caixa"); // *

            System.out.println("____________________________\n");
            System.out.print("Opção: ");
            try {
                int opc = ScannerUtils.validaePegaInteiro(in);

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
            } catch (EntradaInvalidaException e) {
                System.out.println("Permitido apenas valores inteiros");
            }


        }
    }

    public void menuProdProp() {
        System.out.println("*** Inserir produção própria ***");
        System.out.println("1- Inserir receita avulsa em estoque");
        System.out.println("2- Inserir fornada de pão em estoque");
        System.out.println("3- Inserir bolo em estoque ");
        System.out.println("0- Sair");

        System.out.println("____________________________");
        System.out.println();
        System.out.print("Opção: ");
        try {
            int opc = ScannerUtils.validaePegaInteiro(in);

            switch (opc) {
                case 1: /* Inserir receita avulsa em estoque */
                    inserirReceitaAvulsa();
                    /* 4.1 */
                    break;
                case 2: /* Inserir fornada de pão em estoque */
                    inserirFornadaPaoFrances();
                    /* 4.2 */
                    break;
                case 3:/*Inserir bolo em estoque*/
                    inserirFornadaDeBolo();
    //              /* 4.3 *
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Por favor, digite uma opção válida!\n");
                    break;
            }
        } catch (EntradaInvalidaException e) {
            System.out.println("Permitido apenas valores inteiros");
        }
    }

    public void menuAdministrativo8() {
        System.out.print("Por favor, digite a senha administrativa: ");
        try {
            senha = ScannerUtils.validaePegaString(in);

            if (senha.equals(senhaAdmin)) {
                System.out.println("*** Gerenciamento de Estoque***"); /* senha */
                System.out.println("1- Inserir produto em estoque");
                System.out.println("2- Cadastrar produto inédito em estoque");
                System.out.println("3- Relação de todos os produtos ativos em estoque");
                System.out.println("4- Relação de todos os produtos inativos em estoque");
                System.out.println("5- Relação de todos os produtos em estoque");
                System.out.println("6- Desativar um produto");
                System.out.println("7- Ativar produto");
                System.out.println("0- Sair");

                System.out.println("____________________________");
                System.out.println();
                System.out.print("Opção: ");
                int opc = ScannerUtils.validaePegaInteiro(in);

                switch (opc) {
                    // comprar - movimentar produto em estoque
                    case 1: /* Inserir produtos em estoque */
                        inserirProdutoNoEstoque();
                        break;
                    case 2:/* Cadastrar produto inédito em estoque */
                        cadastrarProdutoIneditoEstoque();
                        break;
                    case 3: /* Relação de todos os produtos ativos em estoque */
                        filtrarProdutosAtivos();
                        break;
                    case 4: /*  */
                        filtrarProdutosInativos();
                        break;
                    case 5: /* Relação de todos os produtos ativos e inativos em estoque */
                        filtrarTodosProdutos();
                        break;
                    case 6: /*  */
                        desativarProduto();
                        break;
                    case 7: /*  */
                        ativarProduto();
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
        } catch (EntradaInvalidaException e) {
            System.out.println("Opção inválida ou não existente");
        }
    }

    public void menuAdmininstrativo9() {
        System.out.print("Por favor, digite a senha administrativa: ");
        try {
            senha = ScannerUtils.validaePegaString(in);

            if (senha.equals(senhaAdmin)) {

                System.out.println("*** Controle de caixa***"); /* senha */
                System.out.println("1- Checar vendas por período ");
                //System.out.println("2- Entradas de caixa do dia"); /*preços total de vendas*/
                //System.out.println("3- Lucro bruto do dia"); /*preços total de vendas - preço total de custo*/
                System.out.println("0- Sair");

                System.out.println("____________________________");
                System.out.println();
                System.out.print("Opção: ");
                int opc = ScannerUtils.validaePegaInteiro(in);

                switch (opc) {
                    case 1: /* TODO Checar vendas por período */
                        break;
                    case 2: /* TODO Entradas de caixa do dia*/
                        break;
                    case 3: /* TODO Lucro bruto do dia*/
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
        } catch (EntradaInvalidaException e) {
            System.out.println("Opção inválida ou inexistente");
        }
    }

    //Funções
    private void conferirQuantEmEstoque() {
        System.out.println("Insira o código de barras ou a descrição do produto para saber sua quantidade em estoque: ");
        codOuDesc = in.next();
        List<Produto> produtos = produtoRepository.listarProdutosPorDescricaoOuCod(codOuDesc);
        Produto produto = produtos.get(0);
        BigDecimal qtdeEntrada = movimentacaoDeVendaRepository.qtdeEntradaEmEstoque(produto.getId());
        BigDecimal qtdeSaida = movimentacaoDeVendaRepository.qtdeSaidaDoEstoque(produto.getId());
        if (qtdeEntrada == null) {
            qtdeEntrada = BigDecimal.valueOf(0);
        }
        if (qtdeSaida == null) {
            qtdeSaida = BigDecimal.valueOf(0);
        }
        BigDecimal qtde = qtdeEntrada.subtract(qtdeSaida);

        System.out.printf("Constam no estoque %.2f %s de %s, ", qtde, produto.getUnidadeMedidaVendida(),
                produto.getDescricao());
    }

    private void conferirPrecoProd() {
        System.out.println("Insira o código de barras ou a descrição do produto para saber seu preço: ");
        codOuDesc = in.next();

        List<Produto> produtos = produtoRepository.listarProdutosPorDescricaoOuCod(codOuDesc);
        Produto produto = produtos.get(0);
        System.out.printf("O preço do %s é R$ %.2f%n", produto.getDescricao(), produto.getValorVenda());
    }

    public void realizarVenda() {
        // Inicializei uma nova venda
        Venda venda = new Venda();
        vendaRepository.save(venda);
        int finalizar_compra = 0;
        List<MovimentacaoDeProduto> mp = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        BigDecimal total = new BigDecimal(0);
        try {
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
                        "vendido",
                        null, null, null, null));
                item.setValorTotal(total);
                String string = String.format("%.2f %s %s : R$ %.2f ,Total: R$ %.2f \n", quantidade,
                        produto.getUnidadeMedidaVendida(), produto.getDescricao(), produto.getValorVenda(),
                        produto.getValorVenda().multiply(quantidade));
                sb.append(string);
                itemVendaRepository.save(item);
                System.out.println(sb);
                System.out.printf("\nValor total carrinho: R$ %.2f%n", total);
                System.out.println("    Finalizar compra? S/N");
                char escolha = in.next().charAt(0);
                if (escolha == 's' || escolha == 'S') {
                    movimentacaoDeVendaRepository.saveAll(mp);

                    finalizar_compra++;
                }
            } while (finalizar_compra == 0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Produto não encontrado ou inativo");
        }
        venda.setDataHora(LocalDateTime.now());
        venda.setValor(total);
        vendaRepository.save(venda);
    }

    public void inserirReceitaAvulsa() {
        /* entrada */
        int finalizar_receita = 0;
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

        System.out.println("Insira o valor de venda do novo produto:");
        BigDecimal valor_venda = valueOf(in.nextDouble());

        System.out.println("Insira a data de  validade do novo produto:");
        LocalDate validade = LocalDate.parse(in.next());

        System.out.println("Insira o lote do novo produto:");
        String lote = in.next();
        List<MovimentacaoDeProduto> mp = new ArrayList<>();
        // Cadastro de novo produto
        do {
            System.out.println("Insira o código de barras ou a descrição do ingrediente: ");
            codOuDesc = in.next();

            System.out.println("Insira a quantidade usada desse ingrediente: ");
            quantidade = valueOf(in.nextDouble());

            // Busquei um produto por código de barras
            List<Produto> produtos = produtoRepository.listarProdutosPorDescricaoOuCod(codOuDesc);
            Produto produto = produtos.get(0);
            total = total.add(produto.getValorDeCusto().multiply(quantidade));
            // Criei um novo item venda
            mp.add(new MovimentacaoDeProduto(produto, quantidade,
                    LocalDateTime.now(),
                    "fabricado",
                    validade, LocalDate.now(), "Pão & Cia", lote));

            System.out.println("Finalizar receita? S/N");
            char escolha = in.next().charAt(0);
            if (escolha == 's' || escolha == 'S') {
                movimentacaoDeVendaRepository.saveAll(mp);
                finalizar_receita++;
            }
        } while (finalizar_receita == 0);

        Produto produto = new Produto(descricao, total, peso_unitario,
                unidade_medida_vendida, codigo_barras, valor_venda);

        produtoRepository.save(produto);
    }

    private void inserirFornadaPaoFrances() {

        // Criei um novo item venda
        LocalDate agora = LocalDate.now();

        //Receita
        String ingrediente1 = "farinha";
        BigDecimal quantidade1 = BigDecimal.valueOf(1);
        String ingrediente2 = "fermento";
        BigDecimal quantidade2 = BigDecimal.valueOf(0.5);
        String ingrediente3 = "ovo"; //cartela contém 30
        BigDecimal quantidade3 = BigDecimal.valueOf(0.2); //6 ovos
        BigDecimal qtdePaes = BigDecimal.valueOf(50);       /////rende 50 paes****


        //busca ingredentes em produtos
        List<Produto> produtos = produtoRepository.listarProdutosPorDescricaoOuCod("pão");
        Produto pao = produtos.get(0);
        produtos = produtoRepository.listarProdutosPorDescricaoOuCod(ingrediente1);
        Produto i1 = produtos.get(0);
        produtos = produtoRepository.listarProdutosPorDescricaoOuCod(ingrediente2);
        Produto i2 = produtos.get(0);
        produtos = produtoRepository.listarProdutosPorDescricaoOuCod(ingrediente3);
        Produto i3 = produtos.get(0);

        //registra a retirada dos ingredientes do estoque
        MovimentacaoDeProduto m1 = new MovimentacaoDeProduto(i1,
                quantidade1,
                LocalDateTime.now(),
                "consumido_materia_prima",
                null,
                null,
                null,
                null);
        MovimentacaoDeProduto m2 =new MovimentacaoDeProduto(i2,
                quantidade2,
                LocalDateTime.now(),
                "consumido_materia_prima",
                null,
                null,
                null,
                null);
        MovimentacaoDeProduto m3 =new MovimentacaoDeProduto(i3,
                quantidade3,
                LocalDateTime.now(),
                "consumido_materia_prima",
                null,
                null,
                null,
                null);

        //registra a entrada dos pães em estoque
        MovimentacaoDeProduto m4 = new MovimentacaoDeProduto(pao,qtdePaes,
                LocalDateTime.now(),
                "fabricado",
                agora.plus(3, ChronoUnit.DAYS), LocalDate.now(), "Pão & Cia", null);

        movimentacaoDeVendaRepository.saveAll(Arrays.asList(m1, m2, m3, m4));

    }

    private void inserirFornadaDeBolo() {

        LocalDate agora = LocalDate.now();

        //Receita
        String ingrediente1 = "farinha";
        BigDecimal quantidade1 = valueOf(1);
        String ingrediente2 = "fermento";
        BigDecimal quantidade2 = valueOf(0.5);
        String ingrediente3 = "ovos"; //cartela contém 30
        BigDecimal quantidade3 = valueOf(0.3); //6
        String ingrediente4 = "leite";
        BigDecimal quantidade4 = valueOf(2);
        String ingrediente5 = "margarina";
        BigDecimal quantidade5 = valueOf(1);
        BigDecimal qtdeBolos = valueOf(3); /////rende 3 bolos*****


        //busca ingredentes em produtos

        List<Produto> produtos = produtoRepository.listarProdutosPorDescricaoOuCod("bolo");
        Produto bolo = produtos.get(0);
        produtos = produtoRepository.listarProdutosPorDescricaoOuCod(ingrediente1);
        Produto i1 = produtos.get(0);
        produtos = produtoRepository.listarProdutosPorDescricaoOuCod(ingrediente2);
        Produto i2 = produtos.get(0);
        produtos = produtoRepository.listarProdutosPorDescricaoOuCod(ingrediente3);
        Produto i3 = produtos.get(0);
        produtos = produtoRepository.listarProdutosPorDescricaoOuCod(ingrediente4);
        Produto i4 = produtos.get(0);
        produtos = produtoRepository.listarProdutosPorDescricaoOuCod(ingrediente5);
        Produto i5 = produtos.get(0);

        //registra a retirada dos ingredientes do estoque
        MovimentacaoDeProduto m1 = new MovimentacaoDeProduto(i1,
                quantidade1,
                LocalDateTime.now(),
                "consumido_materia_prima",
                null,
                null,
                null,
                null);
        MovimentacaoDeProduto m2 =new MovimentacaoDeProduto(i2,
                quantidade2,
                LocalDateTime.now(),
                "consumido_materia_prima",
                null,
                null,
                null,
                null);
        MovimentacaoDeProduto m3 =new MovimentacaoDeProduto(i3,
                quantidade3,
                LocalDateTime.now(),
                "consumido_materia_prima",
                null,
                null,
                null,
                null);
        MovimentacaoDeProduto m4 =new MovimentacaoDeProduto(i4,
                quantidade4,
                LocalDateTime.now(),
                "consumido_materia_prima",
                null,
                null,
                null,
                null);
        MovimentacaoDeProduto m5 = new MovimentacaoDeProduto(i5,
                quantidade5,
                LocalDateTime.now(),
                "consumido_materia_prima",
                null,
                null,
                null,
                null);


        //registra a entrada dos bolos em estoque
        MovimentacaoDeProduto m6 = new MovimentacaoDeProduto(bolo, qtdeBolos,
                LocalDateTime.now(),
                "fabricado",
                agora.plus(3, ChronoUnit.DAYS), LocalDate.now(), "Pão & Cia", null);

        movimentacaoDeVendaRepository.saveAll(Arrays.asList(m1, m2, m3, m4, m5, m6));

    }

    public void inserirProdutoNoEstoque() {
        try {
            System.out.println("Insira o código de barras ou a descrição do produto que deseja inserir: ");
            codOuDesc = in.next();

            System.out.println("Insira a quantidade do produto que deseja inserir: ");
            quantidade = valueOf(in.nextDouble());

            System.out.println("Insira a data de fabricação do novo produto (DD/MM/AAAA):");
            String fabricacaoStr = in.next();
            LocalDate fabricacao = LocalDate.parse(fabricacaoStr, formatter);

            System.out.println("Insira a data de vencimento do novo produto: (DD/MM/AAAA)");
            String validadeStr = in.next();
            LocalDate validade = LocalDate.parse(validadeStr, formatter);

            System.out.println("Insira o nome de fornecedor do novo produto:");
            String fornecedor = in.next();

            System.out.println("Insira o lote do novo produto:");
            String lote = in.next();

            List<Produto> produtos = produtoRepository.listarProdutosPorDescricaoOuCod(codOuDesc);
            Produto produto = produtos.get(0);
            MovimentacaoDeProduto mp = new MovimentacaoDeProduto(produto, quantidade, LocalDateTime.now(), "comprado", validade,
                    fabricacao, fornecedor, lote);
            movimentacaoDeVendaRepository.save(mp);

            System.out.printf("%.2f%s de %s foram adicionados no estoque", quantidade, produto.getUnidadeMedidaVendida(),
                    produto.getDescricao());
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        // Cadastra um novo produto em tb_produtos
        produto = new Produto(descricao, valor_custo, peso_unitario, unidade_medida_peso, codigo_barras,
                valor_venda);

        produtoRepository.save(produto);
    }

    public void filtrarProdutosAtivos() {
        List<Produto> produtos = produtoRepository.listarProdutosAtivos();
        produtos.forEach(System.out::println);
    }

    public void filtrarProdutosInativos() {
        List<Produto> produtos = produtoRepository.listarProdutosInativos();
        if(produtos.isEmpty()) {
            System.out.println("Não há produtos inativos");
            return;
        }
        produtos.forEach(System.out::println);
    }

    public void filtrarTodosProdutos() {
        List<Produto> produtos = produtoRepository.listarTodosProdutos();
        produtos.forEach(System.out::println);
    }

    public void produtoEmEstoquePorPeriodo() {

    }

    private void ativarProduto() {
        System.out.println("Insira o código de barras ou a descrição do produto para ativar o produto");
        codOuDesc = in.next();
        List<Produto> produtos = produtoRepository.listarProdutosPorDescricaoOuCod(codOuDesc);
        Produto produto = produtos.get(0);
        if(!produto.getStatus()) {
            System.out.println("Produto já está ativo");
            return;
        }
        produto.setStatusAtivo();
        produtoRepository.save(produto);
    }

    private void desativarProduto() {
        System.out.println("Insira o código de barras ou a descrição do produto para ativar o produto");
        codOuDesc = in.next();
        List<Produto> produtos = produtoRepository.listarProdutosPorDescricaoOuCod(codOuDesc);
        Produto produto = produtos.get(0);
        if(!produto.getStatus()) {
            System.out.println("Produto já está desativado");
            return;
        }
        produto.setStatusInativo();
        produtoRepository.save(produto);
    }
}

