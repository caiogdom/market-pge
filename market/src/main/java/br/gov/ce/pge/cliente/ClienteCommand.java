package br.gov.ce.pge.cliente;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ClienteCommand {

    private final ClienteRepository repository;

    public Cliente salvaCliente(Cliente cliente) {
        repository.findById(cliente.getCpf()).ifPresent(c -> {
            throw new RuntimeException("Cliente já cadastrado");
        });
        return repository.save(cliente);
    }

    public Cliente atualizaCliente(String cpf, Cliente cliente) {
        Cliente clienteDb = repository.findById(cpf).orElseThrow(() -> new RuntimeException("CLiente não encontrado."));
        BeanUtils.copyProperties(cliente, clienteDb, "cpf");
        return repository.save(clienteDb);
    }

    public void deletaCliente(String cpf) {
        Cliente clienteDb = repository.findById(cpf).orElseThrow(() -> new RuntimeException("CLiente não encontrado."));
        repository.delete(clienteDb);
    }
}
