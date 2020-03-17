import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Workout } from '../workout/new-workout/workout.model';
import { AppConstant } from '../app.constants';

type EntityResponseType = HttpResponse<Workout>;
type EntityArrayResponseType = HttpResponse<Workout[]>;

@Injectable({ providedIn: 'root' })
export class WorkoutService {



  public resourceUrl = AppConstant.SERVER_API_URL + 'api/workouts';

  constructor(protected http: HttpClient) { }

  create(currency: Workout): Observable<EntityResponseType> {
    return this.http.post<Workout>(this.resourceUrl, currency, { observe: 'response' });
  }

  update(currency: Workout): Observable<EntityResponseType> {
    return this.http.put<Workout>(this.resourceUrl, currency, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<Workout>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = this.createRequestOption(req);
    return this.http.get<Workout[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }


  createRequestOption(req?: any): HttpParams {
    let options: HttpParams = new HttpParams();
    if (req) {
      Object.keys(req).forEach(key => {
        if (key !== 'sort') {
          options = options.set(key, req[key]);
        }
      });
      if (req.sort) {
        req.sort.forEach(val => {
          options = options.append('sort', val);
        });
      }
    }
    return options;
  }

}
