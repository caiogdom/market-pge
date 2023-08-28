package br.gov.ce.pge.compra;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Blob;

@Getter
@Entity
@ToString
@EqualsAndHashCode
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NotaFiscal {

    @Id
    private long compraId;

    private Blob nota;
}
