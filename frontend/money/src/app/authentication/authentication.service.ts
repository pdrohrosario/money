import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserService } from './user/user.service';
import { tap } from 'rxjs/operators';
import { Token } from './token';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private httpClient:HttpClient, private userService:UserService) { }

  authenticate(userName:string, password:string): Observable<HttpResponse<any>> {
    return this.httpClient.post('http://localhost:8080/auth',{
      userName:userName,
      password:password
    },
    {
      observe:'response'
    }
    ).pipe(
      tap((response) =>{
        const authToken = response?.body  as Token;
        let auth  = authToken.token != null ? authToken.token : '';
        this.userService.saveToken(auth);
      })
    );
  }
}
