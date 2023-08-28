package br.gov.ce.pge.compra;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class NotaFiscalCommand {

    private final NotaFiscalRepository repository;

    public NotaFiscal salvaNotaFiscal(Long compraId, MultipartFile notaFile) {
        try {
            return repository.save(NotaFiscal.of(compraId, fileToBlob(notaFile)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private Blob fileToBlob(MultipartFile file) throws IOException, SQLException {
        return new SerialBlob(file.getBytes());
    }
}
