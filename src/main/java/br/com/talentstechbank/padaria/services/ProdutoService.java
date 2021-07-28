package br.com.talentstechbank.padaria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.talentstechbank.padaria.models.Produto;
import br.com.talentstechbank.padaria.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	public List<Produto> listarProdutos() {
		return repository.listarProdutos();
	}
	
	public Produto listarProdutoPorId(Long id) {
		//TODO tratar erro listar produto que não existe
		return repository.listarProduto(id);
	}
	
	public Produto cadastrarProduto(Produto obj) {
		//TODO tratar erro inserir produto com mesmo código de barras
		//TODO tratar erro inserir produto com descrição nula
		return repository.save(obj);
	}
	
	public Produto alterarProduto(Produto obj, Long id) {
		//TODO tratar erro alterar produto que não existe
		Produto entidade = repository.findById(id).get();
		atualizaValores(entidade, obj);
		return repository.save(entidade);
	}
	
	public void inativarProduto(Long id) {
		//TODO tratar erro deletar produto que não existe
		Produto entidade = repository.findById(id).get();
		entidade.inativaProduto();
		repository.save(entidade);
	}
	
	public void ativarProduto(Long id) {
		//TODO tratar erro deletar produto que não existe
		Produto entidade = repository.findById(id).get();
		entidade.ativaProduto();
		repository.save(entidade);
	}
	
	public List<Produto> listarProdutosPorDescricao(String descricao) {
		return repository.listarProdutosPorDescricao(descricao);
	}
	
	public Produto listarProdutoPorCodBarra(String codigo) {
		return repository.listarProdutoPorCodBarra(codigo);
	}
	
	private void atualizaValores(Produto entidade, Produto obj) {
		entidade.setDescricao(obj.getDescricao());
		entidade.setValorDeCusto(obj.getValorDeCusto());
		entidade.setPesoUnitario(obj.getPesoUnitario());
		entidade.setUnidadeMedidaPeso(obj.getUnidadeMedidaPeso());
		entidade.setValorVenda(obj.getValorVenda());
	}
}
