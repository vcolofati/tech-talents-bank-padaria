package br.com.talentstechbank.padaria.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity(name = "tb_produto")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(name = "valor_custo")
    private BigDecimal valorDeCusto;

    @Column(name = "peso_unitario")
    private BigDecimal pesoUnitario;

    @Column(name = "unidade_medida_peso")
    private String unidadeMedidaPeso;

    @Column(name = "codigo_barras", unique = true, nullable = false)
    private String codigoDeBarras;

    @Column(name = "valor_venda")
    private BigDecimal valorVenda;
    
    @Column(name = "status")
    private Boolean ativo = true;

    public Produto(String descricao, BigDecimal valor_custo, BigDecimal peso_unitario, String unidade_medida_peso, String codigo_barras, BigDecimal valor_venda) {
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
        return unidadeMedidaPeso;
    }

    public void setUnidadeMedidaPeso(String unidadeMedidaPeso) {
        this.unidadeMedidaPeso = unidadeMedidaPeso;
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
}
