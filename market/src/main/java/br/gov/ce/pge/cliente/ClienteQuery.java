package br.gov.ce.pge.cliente;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ClienteQuery {

    private final ClienteRepository repository;

    public List<Cliente> listaClientes() {
        return repository.findAll();
    }

    public Cliente buscaClientePorCpf(String cpf) {
        return repository.findById(cpf).orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
    }
}
