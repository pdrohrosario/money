import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenService } from './token.service';

@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {
  constructor(private tokenService: TokenService) {}

  intercept(
    request: HttpRequest<unknown>,
    next: HttpHandler
  ): Observable<HttpEvent<unknown>> {
    if (this.tokenService.existsToken() && request.url != 'http://localhost:8080/auth') {
      const token = this.tokenService.returnToken();
      const requestClone = request.clone({
        setHeaders: {
            Authorization: `Bearer ${token}`
        }
    });
    return next.handle(requestClone);

    }
    return next.handle(request);
  }
}
