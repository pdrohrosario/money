import { Injectable } from '@angular/core';
import {
  CanLoad,
  Route,
  UrlSegment,
  UrlTree,
  Router,
} from '@angular/router';
import { Observable } from 'rxjs';
import {UserService} from "./user/user.service";

@Injectable({
  providedIn: 'root',
})
export class LoginGuard implements CanLoad {
  constructor(private usuarioService: UserService, private router: Router) {}

  canLoad(
    route: Route,
    segments: UrlSegment[]
    ):
  | Observable<boolean | UrlTree>
  | Promise<boolean | UrlTree>
  | boolean
  | UrlTree {
    if (this.usuarioService.isLoggedIn()) {
      this.router.navigate(['transfer']);
      return false;
    }

    return true;
  }
}
