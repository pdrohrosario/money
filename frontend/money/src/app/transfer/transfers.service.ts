import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable,  of, throwError } from 'rxjs';
import { tap } from 'rxjs/operators';
import {TokenService} from "../authentication/token.service";
import {Transfers, Transfer} from "./transfers";

@Injectable({
  providedIn: 'root',
})
export class TransfersService {

  constructor(private http: HttpClient, private tokenService: TokenService) {}

  list(userId : number):Observable<Transfers>{
    return this.http.get<Transfers>(`http://localhost:8080/transferencias/${userId}`)
  }

  findById(transferId:number):Observable<Transfer>{
    return this.http.get<Transfer>(`http://localhost:8080/transferencia/${transferId}`)
  }

  delete(id : number):Observable<number>{
    return this.http.delete<number>(`http://localhost:8080/transferencia/${id}`);
  }

  create(transfer:Transfer):Observable<any>{
    return this.http.post('http://localhost:8080/transferencia', transfer, {
      observe:'response'
    }
    ).pipe(tap((response)=>{
    }))
  }

  update(transfer:Transfer):Observable<HttpResponse<any>>{
    return this.http.put(`http://localhost:8080/transferencia/${transfer.id}`,transfer,  {
      observe:'response'
    }
    ).pipe(tap((response)=>{
    }))
  }
}
