import { Injectable } from '@angular/core';
import { TokenService } from '../token.service';
import jwt_decode from 'jwt-decode';
import { User } from './user';
import { BehaviorSubject, Observable } from 'rxjs';
import { TokenDecode } from './tokenDecode';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userSubject = new BehaviorSubject<User>({});

  constructor(private tokenService: TokenService, private http:HttpClient) {
    if (this.tokenService.existsToken()) {
      this.decodeJWT();
    }
  }

  private decodeJWT() {
    const token = this.tokenService.returnToken();
    const usuario = jwt_decode(token) as TokenDecode;
    this.findUserById(Number(usuario.sub)).subscribe((response)=>{
      const user = response as User;
      this.userSubject.next(user);
    },
      (error) => {
        alert('Usuário ou senha inválido');
        console.log(error);
      }
    )

  }

  returnUser() {
    return this.userSubject.asObservable();
  }

  saveToken(token: string) {
    this.tokenService.saveToken(token);
    this.decodeJWT();
  }

  logout() {
    this.tokenService.removeToken();
    this.userSubject.next({});
  }

  isLoggedIn() {
    return this.tokenService.existsToken();
  }

  findUserById(userId: number):Observable<User> {
    return this.http.get(`http://localhost:8080/user/find/${userId}`);
  }
}
