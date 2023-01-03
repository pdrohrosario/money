import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TokenService } from '../authentication/token.service';
import {SelectDefault, SelectDefaults } from '../model/selectDefault';
import { Observable,  of, throwError } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TipoGastoService {

  constructor(private http: HttpClient, private tokenService: TokenService) {}

  list():Observable<SelectDefaults>{
    return this.http.get<SelectDefaults>(`http://localhost:8080/tipo-gasto/`);
  }

  create(tipoGasto:SelectDefault):Observable<any>{
    return this.http.post('http://localhost:8080/tipo-gasto/', tipoGasto, {
      observe:'response'
    }
    ).pipe(tap((response)=>{
      console.log(response);
    }))
  }

  delete(id : number):Observable<number>{
    return this.http.delete<number>(`http://localhost:8080/tipo-gasto/${id}`);
  }

  update(tipoGasto:SelectDefault):Observable<HttpResponse<any>>{
    return this.http.put(`http://localhost:8080/tipo-gasto/${tipoGasto.id}`,tipoGasto,  {
      observe:'response'
    }
    ).pipe(tap((response)=>{
    }))
  }
}
