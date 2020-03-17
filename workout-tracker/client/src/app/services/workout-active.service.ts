import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { WorkoutActive } from '../workout/new-workout/workout.model';
import { AppConstant } from '../app.constants';

type EntityResponseType = HttpResponse<WorkoutActive>;
type EntityArrayResponseType = HttpResponse<WorkoutActive[]>;

@Injectable({ providedIn: 'root' })
export class WorkoutActiveService {


  public resourceUrl = AppConstant.SERVER_API_URL + 'api/workoutactives';

  constructor(protected http: HttpClient) { }

  create(currency: WorkoutActive): Observable<EntityResponseType> {
    return this.http.post<WorkoutActive>(this.resourceUrl, currency, { observe: 'response' });
  }

  update(currency: WorkoutActive): Observable<EntityResponseType> {
    return this.http.put<WorkoutActive>(this.resourceUrl, currency, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<WorkoutActive>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = this.createRequestOption(req);
    return this.http.get<WorkoutActive[]>(this.resourceUrl, { params: options, observe: 'response' });
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
