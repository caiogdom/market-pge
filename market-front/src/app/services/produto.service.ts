import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Produto } from '../models/produto';
import { API_ENDPOINT } from '../shared/configs/constants';

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {

  constructor(private http: HttpClient) { }
  
  buscaProdutos(): Observable<Produto[]> {
    return this.http.get<Produto[]>(`${API_ENDPOINT}/produtos`);
  }

  buscaProduto(codigo: string): Observable<Produto> {
    return this.http.get<Produto>(`${API_ENDPOINT}/produtos/${codigo}`);
  }

  salvaProduto(produto: any): Observable<Produto> {
    return this.http.post<Produto>(`${API_ENDPOINT}/produtos`, produto);
  }

  atualizaProduto(produto: any): Observable<Produto> {
    return this.http.put<Produto>(`${API_ENDPOINT}/produtos/${produto.codigo}`, produto);
  }

  deletaProduto(codigo: string): Observable<any> {
    return this.http.delete<any>(`${API_ENDPOINT}/produtos/${codigo}`);
  }
}
