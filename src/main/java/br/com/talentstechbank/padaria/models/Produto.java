package br.com.talentstechbank.padaria.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "tb_produto")
public class Produto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column
    private String descricao;

    @Column(name = "valor_custo")
    private BigDecimal valorDeCusto;

    @Column(name = "peso_unitario")
    private BigDecimal pesoUnitario;

    @Column(name = "unidade_medida_vendida")
    private String unidadeMedidaVendida;

    @Column(name = "codigo_barras", unique = true)
    private String codigoDeBarras;

    @Column(name = "valor_venda")
    private BigDecimal valorVenda;
    
    @Column
    private Boolean status = true;

    @OneToMany(mappedBy = "produto")
    private Set<MovimentacaoDeProduto> movimentacoes = new HashSet<>();
   
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private final Set<ItemVenda> itemVendas = new HashSet<>();

	public Produto() {
	}

	public Produto(String descricao, BigDecimal valorDeCusto, BigDecimal pesoUnitario,
				   String unidadeMedidaVendida, String codigoDeBarras, BigDecimal valorVenda) {
		this.descricao = descricao;
		this.valorDeCusto = valorDeCusto;
		this.pesoUnitario = pesoUnitario;
		this.unidadeMedidaVendida = unidadeMedidaVendida;
		this.codigoDeBarras = codigoDeBarras;
		this.valorVenda = valorVenda;
		this.status = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValorDeCusto() {
		return valorDeCusto;
	}

	public void setValorDeCusto(BigDecimal valorDeCusto) {
		this.valorDeCusto = valorDeCusto;
	}

	public BigDecimal getPesoUnitario() {
		return pesoUnitario;
	}

	public void setPesoUnitario(BigDecimal pesoUnitario) {
		this.pesoUnitario = pesoUnitario;
	}

	public String getUnidadeMedidaVendida() {
		return unidadeMedidaVendida;
	}

	public void setUnidadeMedidaVendida(String unidadeMedidaVendida) {
		this.unidadeMedidaVendida = unidadeMedidaVendida;
	}

	public String getCodigoDeBarras() {
		return codigoDeBarras;
	}

	public void setCodigoDeBarras(String codigoDeBarras) {
		this.codigoDeBarras = codigoDeBarras;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatusAtivo() {
		this.status = true;
	}
	
	public void setStatusInativo() {
		this.status = false;
	}

	public Set<MovimentacaoDeProduto> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(Set<MovimentacaoDeProduto> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

	public Set<ItemVenda> getItemVendas() {
		return itemVendas;
	}

	@Override
	public String toString() {
		return "Produto [descricao=" + descricao + ", codigoDeBarras=" + codigoDeBarras + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoDeBarras == null) ? 0 : codigoDeBarras.hashCode());
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
		Produto other = (Produto) obj;
		if (codigoDeBarras == null) {
			if (other.codigoDeBarras != null)
				return false;
		} else if (!codigoDeBarras.equals(other.codigoDeBarras))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
