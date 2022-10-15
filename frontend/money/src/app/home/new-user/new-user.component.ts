import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NewUser } from './new-user';
import { NewUserService } from './new-user.service';
import { UserExistsService } from './user-exists.service';

@Component({
  selector: 'app-new-user',
  templateUrl: './new-user.component.html',
  styleUrls: ['./new-user.component.css'],
})
export class NewUserComponent implements OnInit {
  newUserForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private newUserService: NewUserService,
    private userExistsService: UserExistsService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.newUserForm = this.formBuilder.group({
      email:['',[
        Validators.required, Validators.email
      ]],
      userName:['',[Validators.required,Validators.minLength(7)]],
      name:['',[
        Validators.required, Validators.minLength(10)
      ]],
      password:['',[Validators.required, Validators.minLength(10)]]
    })
  }

  create(){
    const newUser = this.newUserForm.getRawValue() as NewUser;
    this.newUserService.create(newUser).subscribe(
      () => {
        this.router.navigate(['']);
      },
      (error) =>{
        console.log(error);
      }
    )
  }
}
