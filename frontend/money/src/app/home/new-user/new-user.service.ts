import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NewUser } from './new-user';

@Injectable({
  providedIn: 'root'
})
export class NewUserService {

  constructor(private http:HttpClient) { }

  create(newUser: NewUser){
    return this.http.post('http://localhost:8080/user/signup', newUser);
  }

  exists(userEmail: String){
    return this.http.get(`http://localhost:8080/user/exists/${userEmail}`)
  }
}
