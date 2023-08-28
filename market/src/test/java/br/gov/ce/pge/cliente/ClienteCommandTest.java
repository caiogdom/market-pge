package br.gov.ce.pge.cliente;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ClienteCommandTest {

    @Mock
    private ClienteRepository repository;

    private ClienteCommand command;

    private Cliente cliente;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        command = new ClienteCommand(repository);
        cliente = Cliente.of("12345678900", "Teste", Timestamp.from(Instant.now()));
    }

    @Test
    void should_save_cliente() {
        when(repository.findById(anyString())).thenReturn(Optional.empty());
        when(repository.save(any(Cliente.class))).thenReturn(cliente);

        assertEquals(cliente, command.salvaCliente(cliente));
    }

    @Test
    void should_throw_runtime_excpetion_when_save_cliente() {
        when(repository.findById(anyString())).thenReturn(Optional.of(cliente));

        assertThrows(RuntimeException.class, () -> command.salvaCliente(cliente));
    }

    @Test
    void should_update_cliente() {
        Cliente clienteAtualizado = Cliente.of("12345678900", "Teste 2", Timestamp.from(Instant.now()));
        when(repository.findById(anyString())).thenReturn(Optional.of(cliente));
        when(repository.save(any(Cliente.class))).thenReturn(cliente);

        assertEquals(clienteAtualizado.getNome(), command.atualizaCliente("12345678900", clienteAtualizado).getNome());
    }

    @Test
    void should_throw_runtime_excpetion_when_update_cliente() {
        when(repository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> command.atualizaCliente("12345678900", cliente));
    }

    @Test
    void should_delete_cliente() {
        when(repository.findById(anyString())).thenReturn(Optional.of(cliente));

        assertDoesNotThrow(() -> command.deletaCliente("12345678900"));
    }

    @Test
    void should_throw_runtime_excpetion_when_delete_cliente() {
        when(repository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> command.deletaCliente("12345678900"));
    }
}
