import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppConstant } from '../../app.constants';
import { Category } from './category.model';

type EntityResponseType = HttpResponse<Category>;
type EntityArrayResponseType = HttpResponse<Category[]>;

@Injectable({ providedIn: 'root' })
export class CategoryService {



  public resourceUrl = AppConstant.SERVER_API_URL + 'api/categories';

  constructor(protected http: HttpClient) { }

  create(currency: Category): Observable<EntityResponseType> {
    return this.http.post<Category>(this.resourceUrl, currency, { observe: 'response' });
  }

  update(currency: Category): Observable<EntityResponseType> {
    return this.http.put<Category>(this.resourceUrl, currency, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<Category>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = this.createRequestOption(req);
    return this.http.get<Category[]>(this.resourceUrl, { params: options, observe: 'response' });
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
