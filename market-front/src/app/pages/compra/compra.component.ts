import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Compra } from 'src/app/models/compra';
import { CompraService } from 'src/app/services/compra.service';
import { NotaFiscalService } from 'src/app/services/nota-fiscal.service';

@Component({
  selector: 'app-compra',
  templateUrl: './compra.component.html',
  styleUrls: ['./compra.component.css']
})
export class CompraComponent {

  colunas: string[] = ['dataCompra', 'codigoProduto', 'cpf', 'notaFiscal', 'acoes']
  compras: Compra[] = [];
  formCompra: FormGroup;
  isNovaCompra: boolean = false;

  constructor(private formBuilder: FormBuilder, private compraService: CompraService, private notaFiscalService: NotaFiscalService) {
    this.formCompra = this.formBuilder.group({
      cpfCliente: ['', [Validators.required]],
      codigoProduto: ['', [Validators.required]],
      dataCompra: ['', [Validators.required]],
      notaFiscal: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.compraService.buscaCompras().subscribe((data: Compra[]) => {
      this.compras = data;
    });
  }

  salvarCompra(): void {
    if(this.formCompra.valid) {
      this.compraService.salvaCompra(this.formCompra.value).subscribe((data: Compra) => {
        this.notaFiscalService.uploadNotaFiscal(this.formCompra.controls['notaFiscal'].value, data.id);
        this.compras = [...this.compras, data]
      });
    
      this.isNovaCompra = false;
    }
  }

  iniciaCadastro(): void {
    this.isNovaCompra = true;
    this.formCompra.reset();
  }

  handleFileInputChange(input: HTMLInputElement): void {
    const l = input.files;
    if (l?.length) {
      this.formCompra.controls['notaFiscal'].setValue(l[0]);
    } else {
      this.formCompra.controls['notaFiscal'].patchValue('');
    }
  }
  
}
