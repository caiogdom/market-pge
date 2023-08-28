import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NotaFiscal } from '../models/nota-fiscal';
import { API_ENDPOINT } from '../shared/configs/constants';

@Injectable({
  providedIn: 'root'
})
export class NotaFiscalService {

  constructor(private http: HttpClient) { }
  
  uploadNotaFiscal(file: File, compraId: string): Observable<NotaFiscal> {
    const formData: FormData = new FormData();
    formData.append("0", file, file.name);
    return this.http.post<NotaFiscal>(`${API_ENDPOINT}\\${compraId}`, formData);
  }
}
