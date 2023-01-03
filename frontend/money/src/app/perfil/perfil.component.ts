import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { AuthenticationService } from '../authentication/authentication.service';
import { User } from '../authentication/user/user';
import { UserService } from '../authentication/user/user.service';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit {
  private user!:User;

  isEditMode:boolean = false;
  perfilEditForm!: FormGroup;

  isUpdatePassword:boolean = false;

  constructor(
          private route: ActivatedRoute,
          private router: Router,
          private formBuilder: FormBuilder,
          private userService: UserService,

          private auth:AuthenticationService
          ) {
    this.userService.returnUser().subscribe((response) =>{
      this.user = response;
    })
  }

  ngOnInit(): void {
    this.perfilEditForm = this.formBuilder.group({
      userName:['',[Validators.required, Validators.minLength(7)]],
      name:['',[Validators.required, Validators.minLength(10)]],
      email:['',[Validators.required, Validators.email]],
      password:['',[Validators.minLength(10)]]
    });

    this.perfilEditForm.patchValue(this.user);
  }

  onSubmit(){
    const perfil : User = this.perfilEditForm.value as User;
    perfil.id = this.user.id;
    this.userService.update(perfil).subscribe(
            () => {
              this.userService.updateUser(this.user.id ?? 0)
              this.isEditMode = false;
              this.router.navigate(['/perfil'])
              alert("O perfil foi alterado com sucesso.");
              },
            (error) => {
              alert('Erro ao alterar o perfil, confira o valores dos campos!');
              console.log(error);
            }
            );
    this.reset();
  }

  excluirConta(){
    var confirmExclu = confirm("Ao excluir sua conta, seus dados registrados serão excluídos também !");
    if(confirmExclu)
      this.userService.delete(this.user.id ?? 0).subscribe((response :number) => {
        alert('Conta removida com sucesso !');
        this.userService.logout();
        this.router.navigate(['']);
      });
  }

  reset(){
    this.isEditMode = false;
    this.isUpdatePassword = false;
    this.perfilEditForm.get('password')?.setValue(null);
  }

}
