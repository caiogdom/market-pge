package br.gov.ce.pge.compra.web;

import br.gov.ce.pge.compra.Compra;
import br.gov.ce.pge.compra.CompraCommand;
import br.gov.ce.pge.compra.CompraQuery;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("/compras")
public class CompraRestService {

    private final CompraQuery query;

    private final CompraCommand command;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Compra>> listaCompras() {
        return ResponseEntity.ok(query.listaCompras());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Compra> buscaCompra(@PathVariable Long id) {
        return ResponseEntity.ok(query.buscaCompra(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Compra> salvaCompra(@RequestBody Compra compra) {
        return ResponseEntity.created(URI.create("")).body(command.salvaCompra(compra));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Compra> atualizaCompra(@PathVariable Long id, @RequestBody Compra compra) {
        return ResponseEntity.ok(command.atualizaCompra(id, compra));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletaCompra(@PathVariable Long id) {
        command.deletaCompra(id);
    }
}
