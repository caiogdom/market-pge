import { AfterViewInit, Component, OnInit, Query, QueryList, ViewChild, ViewChildren } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatTable } from '@angular/material/table';
import { Produto } from 'src/app/models/produto';
import { ProdutoService } from 'src/app/services/produto.service';

@Component({
  selector: 'app-produto',
  templateUrl: './produto.component.html',
  styleUrls: ['./produto.component.css']
})
export class ProdutoComponent implements OnInit {
  
  colunas: string[] = ['nome', 'acoes']
  produtoSelecionado!: Produto;
  produtos: Produto[] = [];
  formProduto: FormGroup;
  isNovoProduto: boolean = false;
  isEditaProduto: boolean = false;

  constructor(private formBuilder: FormBuilder, private produtoService: ProdutoService) {
    this.formProduto = this.formBuilder.group({
      codigo: ['', [Validators.required]],
      nome: ['', [Validators.required]],
      descricao: ['', [Validators.required]],
      preco: [0.0, [Validators.required]]
    });
  }

  ngOnInit(): void {
    this.produtoService.buscaProdutos().subscribe((data: Produto[]) => {
      this.produtos = data;
    });
  }

  salvarProduto(): void {
    if(this.formProduto.valid) {
      if(!this.isEditaProduto) {
        this.produtoService.salvaProduto(this.formProduto.value).subscribe((data: Produto) => {
          this.produtos = [...this.produtos, data];
        });
      } else {
        this.produtoService.atualizaProduto(this.formProduto.value).subscribe((data: Produto) => {
          this.produtos[this.produtos.indexOf(this.produtoSelecionado)] = data;
          this.produtos = [...this.produtos]
        });
      }
      this.isNovoProduto = false;
      this.isEditaProduto = false;
    }
  }

  iniciaCadastro(): void {
    this.isNovoProduto = true;
    this.isEditaProduto = false;
    this.formProduto.reset();
  }

  editaCadastro(produto: Produto): void {
    this.isNovoProduto = true;
    this.isEditaProduto = true;
    this.produtoSelecionado = produto;
    this.formProduto.setValue(produto);
  }

  deletaCadastro(produto: Produto): void {
    this.produtoService.deletaProduto(produto.codigo).subscribe(() => {
      this.produtos.splice(this.produtos.indexOf(produto), 1);
      this.produtos = [...this.produtos];
    });
    this.isNovoProduto = false;
    this.isEditaProduto = false;
  }

}
