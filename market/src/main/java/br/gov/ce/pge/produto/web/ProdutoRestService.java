package br.gov.ce.pge.produto.web;

import br.gov.ce.pge.produto.Produto;
import br.gov.ce.pge.produto.ProdutoCommand;
import br.gov.ce.pge.produto.ProdutoQuery;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("/produtos")
public class ProdutoRestService {

    private final ProdutoQuery query;

    private final ProdutoCommand command;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Produto>> listaProdutos() {
        return ResponseEntity.ok(query.listaProdutos());
    }

    @GetMapping("/{codigo}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Produto> buscaProdutoPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(query.buscaProdutoPorCodigo(codigo));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Produto> salvaProduto(@RequestBody Produto produto) {
        return ResponseEntity.created(URI.create("")).body(command.salvaProduto(produto));
    }

    @PutMapping("/{codigo}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Produto> atualizaProduto(@PathVariable String codigo, @RequestBody Produto produto) {
        return ResponseEntity.ok(command.atualizaProduto(codigo, produto));
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletaProduto(@PathVariable String codigo) {
        command.deletaProduto(codigo);
    }
}
