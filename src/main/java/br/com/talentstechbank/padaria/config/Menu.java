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

//		// Como fazer o menu no spring
//		System.out.println();
//		System.out.println("----- Padaria ------");
//		Scanner in = new Scanner(System.in);
//		System.out.print("Digite a descricao do produto: ");
//		String descricao = in.next();
//		Produto produto = new Produto(descricao, BigDecimal.valueOf(2.00), BigDecimal.valueOf(0.5), "gr.", "2366565558", BigDecimal.valueOf(3.00));
//		produtoService.cadastrarProduto(produto);
//		System.out.println(produtoService.listarProdutos());
//		in.close();


//		public static void iniciar() {
			boolean loop = true;
			Scanner in = new Scanner(System.in).useDelimiter("\n");
			String senhaAdmin = "321"; /// passar senha para arquivo env que será acessado para validação de sub menus 8 e 9


			while (loop != false) {

				System.out.println("***Caixa Padaria Pão & cia ***");
				System.out.println("1- Realizar venda");
				System.out.println("2- Conferir preço de produto");
				System.out.println("3- Conferir quantidade de determinado produto em estoque");
				System.out.println("4- Inserir produção");
				System.out.println("8 - Acessar menu de Gerenciamento de Estoque"); //sublocar em outra classe+validação de senha em env
				System.out.println("9 - Acessar menu de Controle de caixa"); //sublocar em outra classe+validação de senha em env


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


						break;

					case 3: /*Conferir quantidade de determinado produto em estoque*/


						break;

					case 4:

						System.out.println("*** Inserir produção própria ***");
						System.out.println("1- Inserir receita avulsa em estoque");
						System.out.println("2- Inserir fornada de pão em estoque");
						//   System.out.println("3- Inserir bolo em estoque  ");
						System.out.println("0- Sair");

						System.out.println("____________________________");
						System.out.println();
						System.out.print("Opção: ");
						int opcEmGerenciamentoDeProducaoPropria = in.nextInt();

						switch (opcEmGerenciamentoDeProducaoPropria) {
							case 1:                 /*Inserir receita avulsa em estoque*/
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
								Produto produto = new Produto(descricao,
										valor_custo,
										peso_unitario,
										unidade_medida_peso,
										codigo_barras,
										valor_venda
								);

								produto = produtoService.cadastrarProduto(produto);
								
							//movimentacaoDeProdutoService.fabricar(produto, quantidade, valor_custo, valor_venda, null, null, codigo_barras);
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
							System.out.println("1- Inserir produto em estoque");
							System.out.println("2- Cadastrar produto inédito em estoque");
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
									produto = produtoService.listarProdutoPorCodBarra("98798794566565"); 
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

