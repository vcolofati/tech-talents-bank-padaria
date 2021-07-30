package br.com.talentstechbank.padaria.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_movimentacao_de_produto")
public class MovimentacaoDeProduto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @Column
    private BigDecimal quantidade;

    @Column(name = "data_movimentacao")
    private LocalDateTime dataMovimentacao;

    @Column(name = "especie_da_movimentacao")
    String especieDaMovimentacao;

    @Column
    private LocalDate validade;

    @Column
    private LocalDate fabricacao;

    @Column
    private String fornecedor;

    @Column()
    private String lote;

	public MovimentacaoDeProduto() {
	}

	public MovimentacaoDeProduto(Produto produto, BigDecimal quantidade, LocalDateTime dataMovimentacao,
								 String especieDaMovimentacao, LocalDate validade,
								 LocalDate fabricacao, String fornecedor, String lote) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.dataMovimentacao = dataMovimentacao;
		this.especieDaMovimentacao = especieDaMovimentacao;
		this.validade = validade;
		this.fabricacao = fabricacao;
		this.fornecedor = fornecedor;
		this.lote = lote;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public LocalDateTime getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public String getEspecieDaMovimentacao() {
		return especieDaMovimentacao;
	}

	public void setEspecieDaMovimentacao(String especieDaMovimentacao) {
		this.especieDaMovimentacao = especieDaMovimentacao;
	}

	public LocalDate getValidade() {
		return validade;
	}

	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}

	public LocalDate getFabricacao() {
		return fabricacao;
	}

	public void setFabricacao(LocalDate fabricacao) {
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
		return "MovimentacaoDeProduto [id=" + id + ", produto=" + produto + ", dataMovimentacao=" + dataMovimentacao
				+ ", especieDaMovimentacao=" + especieDaMovimentacao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovimentacaoDeProduto other = (MovimentacaoDeProduto) obj;
		if (id == null) {
			return other.id == null;
		} else return id.equals(other.id);
	}
}
