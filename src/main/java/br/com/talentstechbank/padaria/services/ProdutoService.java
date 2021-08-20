package br.com.talentstechbank.padaria.services;

import br.com.talentstechbank.padaria.models.Produto;
import br.com.talentstechbank.padaria.repositories.ProdutoRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> listarTodosProdutos() {
        return repository.findAll();
    }

    public List<Produto> listarProdutosAtivos() {
        return repository.findProdutosByAtivoIsTrue();
    }

    public List<Produto> listarProdutosInativos() {
        return repository.findProdutosByAtivoIsFalse();
    }

    public Produto listarProdutoPorId(Long id) {
        //TODO tratar erro listar produto que não existe
        return repository.findProdutoByIdAndAtivoIsTrue(id);
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
        entidade.setInativo();
        repository.save(entidade);
    }

    public void ativarProduto(Long id) {
        //TODO tratar erro deletar produto que não existe
        Produto entidade = repository.findById(id).get();
        entidade.setAtivo();
        repository.save(entidade);
    }

    public List<Produto> listarProdutosPorDescricao(String descricao) {
        return repository.findProdutosByDescricaoAndAtivoIsTrue(descricao);
    }

    public Produto listarProdutoPorCodBarra(String codigo) {
        return repository.findProdutoByCodigoDeBarrasAndAtivoIsTrue(codigo);
    }

    private void atualizaValores(Produto entidade, Produto obj) {
        entidade.setDescricao(obj.getDescricao());
        entidade.setValorDeCusto(obj.getValorDeCusto());
        entidade.setPesoUnitario(obj.getPesoUnitario());
        entidade.setUnidadeMedidaVenda(obj.getUnidadeMedidaVenda());
        entidade.setValorVenda(obj.getValorVenda());
    }
}
