package br.gov.ce.pge.compra;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CompraCommand {

    private final CompraRepository repository;

    public Compra salvaCompra(Compra compra) {
        repository.findById(compra.getId()).ifPresent(c -> {
            throw new RuntimeException("Compra já cadastrado");
        });
        return repository.save(compra);
    }

    public Compra atualizaCompra(Long id, Compra compra) {
        Compra compraDb = repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
        BeanUtils.copyProperties(compra, compraDb);
        return repository.save(compraDb);
    }

    public void deletaCompra(Long id) {
        Compra compraDb = repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
        repository.delete(compraDb);
    }
}
