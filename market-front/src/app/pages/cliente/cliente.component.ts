import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Cliente } from 'src/app/models/cliente';
import { ClienteService } from 'src/app/services/cliente.service';

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.css']
})
export class ClienteComponent implements OnInit {

  colunas: string[] = ['nome', 'acoes']
  clienteSelecionado!: Cliente;
  clientes: Cliente[] = [];
  formCliente: FormGroup;
  isNovoCliente: boolean = false;
  isEditaCliente: boolean = false;

  constructor(private formBuilder: FormBuilder, private clienteService: ClienteService) {
    this.formCliente = this.formBuilder.group({
      nome: ['', [Validators.required]],
      cpf: ['', [Validators.required]],
      dataNascimento: ['', [Validators.required]]
    });
  }

  ngOnInit(): void {
    this.clienteService.buscaClientes().subscribe((data: Cliente[]) => {
      this.clientes = data;
    });
  }

  salvarCliente(): void {
    if(this.formCliente.valid) {
      if(!this.isEditaCliente) {
        this.clienteService.salvaCliente(this.formCliente.value).subscribe((data: Cliente) => {
          this.clientes = [...this.clientes, data]
        });
      } else {
        this.formCliente.controls['cpf'].enable();
        this.clienteService.atualizaCliente(this.formCliente.value).subscribe((data: Cliente) => {
          this.clientes[this.clientes.indexOf(this.clienteSelecionado)] = data;
          this.clientes = [...this.clientes]
        });
      }
      this.isNovoCliente = false;
      this.isEditaCliente = false;
    }
  }

  iniciaCadastro(): void {
    this.isNovoCliente = true;
    this.isEditaCliente = false;
    this.formCliente.reset();
    this.formCliente.controls['cpf'].enable();
  }

  editaCadastro(cliente: Cliente): void {
    this.isNovoCliente = true;
    this.isEditaCliente = true;
    this.formCliente.controls['cpf'].disable();
    this.clienteSelecionado = cliente;
    this.formCliente.setValue(cliente);
  }

  deletaCadastro(cliente: Cliente): void {
    this.clienteService.deletaCliente(cliente.cpf).subscribe(() => {
      this.clientes.splice(this.clientes.indexOf(cliente), 1);
      this.clientes = [...this.clientes];
    });
    this.isNovoCliente = false;
    this.isEditaCliente = false;
  }

}
