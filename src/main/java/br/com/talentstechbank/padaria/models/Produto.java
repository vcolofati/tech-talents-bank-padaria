package br.com.talentstechbank.padaria.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "tb_produto")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
    @Column()
    private String descricao;

    @Column(name = "valor_custo")
    private BigDecimal valorDeCusto;

    @Column(name = "peso_unitario")
    private BigDecimal pesoUnitario;

    @Column(name = "unidade_medida_vendida")
    private String unidadeMedidaVendida;
    
    @NotNull
    @Column(name = "codigo_barras", unique = true)
    private String codigoDeBarras;

    @Column(name = "valor_venda")
    private BigDecimal valorVenda;
    
    @Column(name = "status")
    private Boolean ativo = true;
    
    @OneToMany(mappedBy = "produto")
    private Set<MovimentacaoDeProduto> movimentacoes = new HashSet<>();
    
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private Set<ItemVenda> itemVendas = new HashSet<>();

    public Produto() {
	}
    
   	public Produto(String descricao, BigDecimal valorDeCusto, BigDecimal pesoUnitario, String unidadeMedidaPeso,
			String codigoDeBarras, BigDecimal valorVenda) {
		this.descricao = descricao;
		this.valorDeCusto = valorDeCusto;
		this.pesoUnitario = pesoUnitario;
		this.unidadeMedidaVendida = unidadeMedidaPeso;
		this.codigoDeBarras = codigoDeBarras;
		this.valorVenda = valorVenda;
	}

	public Long getId() {
        return id;
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

    public String getUnidadeMedidaPeso() {
        return unidadeMedidaVendida;
    }

    public void setUnidadeMedidaPeso(String unidadeMedidaPeso) {
        this.unidadeMedidaVendida = unidadeMedidaPeso;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
    	if (!codigoDeBarras.equals("")) {    		
    		this.codigoDeBarras = codigoDeBarras;
    	}
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }

	public Boolean getAtivo() {
		return ativo;
	}

	public void inativaProduto() {
		this.ativo = false;
	}
	
	public void ativaProduto() {
		this.ativo = true;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", descricao=" + descricao + ", valorDeCusto=" + valorDeCusto + ", pesoUnitario="
				+ pesoUnitario + ", unidadeMedidaPeso=" + unidadeMedidaVendida + ", codigoDeBarras=" + codigoDeBarras
				+ ", valorVenda=" + valorVenda + ", ativo=" + ativo + "]";
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
