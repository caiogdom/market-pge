package br.gov.ce.pge.produto;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ProdutoQuery {
    
    private final ProdutoRepository repository;

    public List<Produto> listaProdutos() {
        return repository.findAll();
    }

    public Produto buscaProdutoPorCodigo(String codigo) {
        return repository.findById(codigo).orElseThrow(() -> new RuntimeException("Produto não encontrado."));
    }
}
