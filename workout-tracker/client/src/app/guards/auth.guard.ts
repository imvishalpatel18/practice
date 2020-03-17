import { AuthService } from './../services/auth.service';
import { CanActivate, Router, CanLoad } from '@angular/router';
import { of } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable()
export class AuthGuard implements CanActivate, CanLoad {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate() {
    if (this.authService.isAuth()) {
      return of(true);
    } else {
      this.router.navigate(['login']);
      return of(false);
    }
  }

  canLoad() {
    if (this.authService.isAuth()) {
      return of(true);
    } else {
      this.router.navigate(['login']);
      return of(false);
    }
  }
}
