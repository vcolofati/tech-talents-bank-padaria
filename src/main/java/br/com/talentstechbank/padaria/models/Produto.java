package br.com.talentstechbank.padaria.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
public class Produto implements Serializable {

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

    @Column(name = "unidade_medida_venda")
    private String unidadeMedidaVenda;

    @Column(name = "codigo_barras", unique = true)
    private String codigoDeBarras;

    @Column(name = "valor_venda")
    private BigDecimal valorVenda;

    @Column
    private Boolean ativo = true;

    @OneToMany(mappedBy = "produto")
    private Set<MovimentacaoDeProduto> movimentacoes = new HashSet<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private final Set<ItemVenda> itemVendas = new HashSet<>();

    public void setAtivo() {
        this.ativo = true;
    }

    public void setInativo() {
        this.ativo = false;
    }

    @JsonIgnore
    public Set<MovimentacaoDeProduto> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(Set<MovimentacaoDeProduto> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    @JsonIgnore
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
            return other.id == null;
        } else return id.equals(other.id);
    }
}
