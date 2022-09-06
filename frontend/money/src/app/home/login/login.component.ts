import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/authentication/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  email = '';
  password = '';

  constructor(private authService: AuthenticationService) {}

  ngOnInit(): void {}

  login() {
    this.authService.authenticate(this.email, this.password).subscribe(
      () => {},
      (error) => {
        alert('Email ou Senha inválido');
        console.log(error);
      }
    );
  }
}
