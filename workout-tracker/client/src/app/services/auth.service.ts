
import { AuthData } from './../models/auth-data.model';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { AppConstant } from '../app.constants';


@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private isAuthenticated = false;
  authChange = new Subject<boolean>();

  constructor(private router: Router, private http: HttpClient) { }

  login(authData: AuthData): Observable<any> {


    function authenticateSuccess(resp) {
      const bearerToken = resp.body.id_token;

      this.storeAuthenticationToken(bearerToken, false);
      return bearerToken;

    }

    return this.http.post(AppConstant.SERVER_API_URL + 'api/authenticate', authData, { observe: 'response' })
      .pipe(map(authenticateSuccess.bind(this)));
  }

  logout() {
    localStorage.removeItem('authenticationToken');
    sessionStorage.removeItem('authenticationToken');
  }

  storeAuthenticationToken(jwt, rememberMe) {
    if (rememberMe) {
      localStorage.setItem('authenticationToken', jwt);
    } else {
      sessionStorage.setItem('authenticationToken', jwt);
    }
  }

  isAuth(): boolean {
    return this.isAuthenticated;
  }
}
