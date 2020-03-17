import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppConstant } from '../../app.constants';

@Injectable({ providedIn: 'root' })
export class RegisterService {
  constructor(private http: HttpClient) {}

  save(account: any): Observable<any> {
    return this.http.post(AppConstant.SERVER_API_URL + 'api/register', account);
  }
}
