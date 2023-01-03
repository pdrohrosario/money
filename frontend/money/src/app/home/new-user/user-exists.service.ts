import { Injectable } from '@angular/core';
import { NewUserService } from './new-user.service';
import { first, map, switchMap } from 'rxjs/operators';
import { AbstractControl } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class UserExistsService {

  constructor(private newUserService: NewUserService) { }

  userExists(){
    return (control: AbstractControl) => {
      return control.valueChanges.pipe(
        switchMap((userEmail) => this.newUserService.exists(userEmail))
      ),
      map((userAlreadyExists) => userAlreadyExists ? {userExists:true}: null),
      first()
    }
  }
}
