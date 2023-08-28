package br.gov.ce.pge.cliente;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Timestamp;

@Data
@Entity
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Cliente {

    @Id
    private String cpf;

    private String nome;

    private Timestamp dataNascimento;
}
