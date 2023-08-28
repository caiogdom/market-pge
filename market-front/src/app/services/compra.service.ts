import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Compra } from '../models/compra';
import { API_ENDPOINT } from '../shared/configs/constants';

@Injectable({
  providedIn: 'root'
})
export class CompraService {

  constructor(private http: HttpClient) { }
  
  buscaCompras(): Observable<Compra[]> {
    return this.http.get<Compra[]>(`${API_ENDPOINT}/compras`);
  }

  buscaCompra(id: number): Observable<Compra> {
    return this.http.get<Compra>(`${API_ENDPOINT}/compras/${id}`);
  }

  salvaCompra(compra: any): Observable<Compra> {
    return this.http.post<Compra>(`${API_ENDPOINT}/compras`, compra);
  }

  atualizaCompra(compra: any): Observable<Compra> {
    return this.http.put<Compra>(`${API_ENDPOINT}/compras/${compra.id}`, compra);
  }

  deletaCompra(id: number): Observable<any> {
    return this.http.delete<any>(`${API_ENDPOINT}/compras/${id}`);
  }
}
