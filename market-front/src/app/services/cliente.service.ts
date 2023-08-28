import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cliente } from '../models/cliente';
import { API_ENDPOINT } from '../shared/configs/constants';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor(private http: HttpClient) { }

  buscaClientes(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(`${API_ENDPOINT}/clientes`);
  }

  buscaCliente(cpf: string): Observable<Cliente> {
    return this.http.get<Cliente>(`${API_ENDPOINT}/clientes/${cpf}`);
  }

  salvaCliente(cliente: any): Observable<Cliente> {
    return this.http.post<Cliente>(`${API_ENDPOINT}/clientes`, cliente);
  }

  atualizaCliente(cliente: any): Observable<Cliente> {
    return this.http.put<Cliente>(`${API_ENDPOINT}/clientes/${cliente.cpf}`, cliente);
  }

  deletaCliente(cpf: string): Observable<any> {
    return this.http.delete<any>(`${API_ENDPOINT}/clientes/${cpf}`);
  }
}
