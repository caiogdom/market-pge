package br.gov.ce.pge.cliente.web;

import br.gov.ce.pge.cliente.Cliente;
import br.gov.ce.pge.cliente.ClienteCommand;
import br.gov.ce.pge.cliente.ClienteQuery;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("/clientes")
public class ClienteRestService {

    private final ClienteQuery query;

    private final ClienteCommand command;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Cliente>> listaClientes() {
        return ResponseEntity.ok(query.listaClientes());
    }

    @GetMapping("/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Cliente> buscaClientePorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(query.buscaClientePorCpf(cpf));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Cliente> salvaCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.created(URI.create("")).body(command.salvaCliente(cliente));
    }

    @PutMapping("/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Cliente> atualizaCliente(@PathVariable String cpf, @RequestBody Cliente cliente) {
        return ResponseEntity.ok(command.atualizaCliente(cpf, cliente));
    }

    @DeleteMapping("/{cpf}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletaCliente(@PathVariable String cpf) {
        command.deletaCliente(cpf);
    }
}
