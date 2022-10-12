import { Injectable } from '@angular/core';
import { TokenService } from '../token.service';
import jwt_decode from 'jwt-decode';
import { User } from './user';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userSubject = new BehaviorSubject<User>({});

  constructor(private tokenService: TokenService) {
    if (this.tokenService.existsToken()) {
      this.decodeJWT();
    }
  }

  private decodeJWT() {
    const token = this.tokenService.returnToken();
    const usuario = jwt_decode(token) as User;
    this.userSubject.next(usuario);
  }

  retornaUsuario() {
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
}
