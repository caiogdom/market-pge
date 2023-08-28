package br.gov.ce.pge.produto;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ProdutoCommand {
    
    private final ProdutoRepository repository;

    public Produto salvaProduto(Produto produto) {
        repository.findById(produto.getCodigo()).ifPresent(c -> {
            throw new RuntimeException("Produto já cadastrado");
        });
        return repository.save(produto);
    }

    public Produto atualizaProduto(String codigo, Produto produto) {
        Produto produtoDb = repository.findById(codigo).orElseThrow(() -> new RuntimeException("Produto não encontrado."));
        BeanUtils.copyProperties(produto, produtoDb, "cpf");
        return repository.save(produtoDb);
    }

    public void deletaProduto(String codigo) {
        Produto produtoDb = repository.findById(codigo).orElseThrow(() -> new RuntimeException("Produto não encontrado."));
        repository.delete(produtoDb);
    }
}
