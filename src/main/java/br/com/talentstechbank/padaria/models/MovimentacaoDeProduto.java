package br.com.talentstechbank.padaria.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;

@Entity(name = "tb_movimentacao_de_produto")
public class MovimentacaoDeProduto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@ManyToOne()
	@JoinColumn(name= "id_produto")
	private Produto produto;
	
	@Column()
	private BigDecimal quantidade;
	
	@Column(name = "data_movimentação")
	private LocalDateTime dataMovimentação;
	
	@Column(name = "valor_custo")
	private BigDecimal valorCusto;
	
	@Column(name = "valor_venda")
	private BigDecimal valorVenda;
	
	@Column(name = "especie_da_movimentacao")
	String especieDaMovimentacao;
	
	@Column()
	private LocalDateTime validade;
	
	@Column()
	private LocalDateTime fabricacao;
	
	@Column()
	private String fornecedor;
	
	@Column()
	private String lote;

	public MovimentacaoDeProduto() {
	}

	public MovimentacaoDeProduto(Produto produto, BigDecimal quantidade, LocalDateTime dataMovimentação,
			BigDecimal valorCusto, BigDecimal valorVenda, String especieDaMovimentacao, LocalDateTime validade,
			LocalDateTime fabricacao, String fornecedor, String lote) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.dataMovimentação = dataMovimentação;
		this.valorCusto = valorCusto;
		this.valorVenda = valorVenda;
		this.especieDaMovimentacao = especieDaMovimentacao;
		this.validade = validade;
		this.fabricacao = fabricacao;
		this.fornecedor = fornecedor;
		this.lote = lote;
	}


	public String getEspecieDaMovimentacao() {
		return especieDaMovimentacao;
	}

	public void setEspecieDaMovimentacao(String especieDaMovimentacao) {
		this.especieDaMovimentacao = especieDaMovimentacao;
	}

	public Long getId() {
		return id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public LocalDateTime getDataMovimentação() {
		return dataMovimentação;
	}

	public void setDataMovimentação(LocalDateTime dataMovimentação) {
		this.dataMovimentação = dataMovimentação;
	}

	public BigDecimal getValorCusto() {
		return valorCusto;
	}

	public void setValorCusto(BigDecimal valorCusto) {
		this.valorCusto = valorCusto;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}

	public LocalDateTime getValidade() {
		return validade;
	}

	public void setValidade(LocalDateTime validade) {
		this.validade = validade;
	}

	public LocalDateTime getFabricacao() {
		return fabricacao;
	}

	public void setFabricacao(LocalDateTime fabricacao) {
		this.fabricacao = fabricacao;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	@Override
	public String toString() {
		return "MovimentacaoDeProduto [id=" + id + ", produto=" + produto + ", quantidade=" + quantidade
				+ ", dataMovimentação=" + dataMovimentação + ", valorCusto=" + valorCusto + ", valorVenda=" + valorVenda
				+ ", especieDaMovimentacao=" + especieDaMovimentacao + ", validade=" + validade + ", fabricacao="
				+ fabricacao + ", fornecedor=" + fornecedor + ", lote=" + lote + "]";
	}	
}
