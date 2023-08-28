package br.gov.ce.pge.compra.web;

import br.gov.ce.pge.compra.NotaFiscal;
import br.gov.ce.pge.compra.NotaFiscalCommand;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("notasFiscais")
public class NotaFiscalRestService {

    private final NotaFiscalCommand command;

    @PostMapping("/upload/{compraId}")
    public ResponseEntity<NotaFiscal> upload(@RequestParam("file") MultipartFile notaFile, @PathVariable("compraId") Long compraId) {
        return ResponseEntity.ok(command.salvaNotaFiscal(compraId, notaFile));
    }
}
