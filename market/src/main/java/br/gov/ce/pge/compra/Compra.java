package br.gov.ce.pge.compra;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Entity
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String cpfCliente;

    private String codigoProduto;

    private Timestamp dataCompra;
}
