import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TokenService } from '../authentication/token.service';
import { SelectDefault, SelectDefaults} from '../model/selectDefault'
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class FormaPagamentoService {

  constructor(private http: HttpClient, private tokenService: TokenService) {}

  list():Observable<SelectDefaults>{
    return this.http.get<SelectDefaults>(`http://localhost:8080/forma-pagamento/`);
  }

  create(formaPagamento:SelectDefault):Observable<any>{
    return this.http.post('http://localhost:8080/forma-pagamento/', formaPagamento, {
      observe:'response'
    }
    ).pipe(tap((response)=>{
      console.log(response);
    }))
  }

  delete(id : number):Observable<number>{
    return this.http.delete<number>(`http://localhost:8080/forma-pagamento/${id}`);
  }

  update(formaPagamento:SelectDefault):Observable<HttpResponse<any>>{
    return this.http.put(`http://localhost:8080/forma-pagamento/${formaPagamento.id}`,formaPagamento,  {
      observe:'response'
    }
    ).pipe(tap((response)=>{
    }))
  }
}
