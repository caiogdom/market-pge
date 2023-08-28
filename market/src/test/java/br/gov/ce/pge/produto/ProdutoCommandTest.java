package br.gov.ce.pge.produto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ProdutoCommandTest {

    @Mock
    private ProdutoRepository repository;

    private ProdutoCommand command;

    private Produto produto;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        command = new ProdutoCommand(repository);
        produto = Produto.of("123456", "Cadeira", 123.54d, "Cadeira de escritório");
    }

    @Test
    void should_save_produto() {
        when(repository.findById(anyString())).thenReturn(Optional.empty());
        when(repository.save(any(Produto.class))).thenReturn(produto);

        assertEquals(produto, command.salvaProduto(produto));
    }

    @Test
    void should_throw_runtime_excpetion_when_save_produto() {
        when(repository.findById(anyString())).thenReturn(Optional.of(produto));

        assertThrows(RuntimeException.class, () -> command.salvaProduto(produto));
    }

    @Test
    void should_update_produto() {
        Produto produtoAtualizado = Produto.of("123456", "Cadeira", 180.00d, "Cadeira de escritório");
        when(repository.findById(anyString())).thenReturn(Optional.of(produto));
        when(repository.save(any(Produto.class))).thenReturn(produto);

        assertEquals(produtoAtualizado.getPreco(), command.atualizaProduto("123456", produtoAtualizado).getPreco());
    }

    @Test
    void should_throw_runtime_excpetion_when_update_produto() {
        when(repository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> command.atualizaProduto("123456", produto));
    }

    @Test
    void should_delete_produto() {
        when(repository.findById(anyString())).thenReturn(Optional.of(produto));

        assertDoesNotThrow(() -> command.deletaProduto("123456"));
    }

    @Test
    void should_throw_runtime_excpetion_when_delete_produto() {
        when(repository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> command.deletaProduto("123456"));
    }
}
