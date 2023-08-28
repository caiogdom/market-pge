package br.gov.ce.pge.compra;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class CompraQuery {

    private final CompraRepository repository;

    public List<Compra> listaCompras() {
        return repository.findAll();
    }

    public Compra buscaCompra(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Compra não encontrada."));
    }

}
