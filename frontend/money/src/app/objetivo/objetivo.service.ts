import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable,  of, throwError } from 'rxjs';
import { tap } from 'rxjs/operators';
import {TokenService} from "../authentication/token.service";
import { Goal, Goals } from './objetivo';

@Injectable({
  providedIn: 'root',
})
export class GoalService {

  constructor(private http: HttpClient, private tokenService: TokenService) {}

  list(id : number):Observable<Goals>{
    return this.http.get<Goals>(`http://localhost:8080/objetivos/${id}`)
  }

  findById(objetivoId:number):Observable<Goal> {
    return this.http.get<Goal>(`http://localhost:8080/objetivo/${objetivoId}`)
  }

  delete(id : number):Observable<number>{
    return this.http.delete<number>(`http://localhost:8080/objetivo/${id}`);
  }

  update(goal: Goal):Observable<any> {
    return this.http.put(`http://localhost:8080/objetivo/${goal.id}`, goal, {
      observe:'response'
    }
    ).pipe(tap((response)=>{
      console.log(response);
    }))
  }
  create(goal: Goal):Observable<any> {
    return this.http.post('http://localhost:8080/objetivo', goal, {
      observe:'response'
    }
    ).pipe(tap((response)=>{
      console.log(response);
    }))
  }

}
